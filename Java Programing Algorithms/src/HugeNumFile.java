
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.concurrent.CountDownLatch;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Muaz Aljarhi
 */
public class HugeNumFile {

    public static void WriteFile() throws IOException
    {
        SecureRandom r  = new SecureRandom();
        r.setSeed(System.currentTimeMillis());
        BufferedWriter numW = new BufferedWriter(new FileWriter("D:\\Data Processing and Analysis\\HugeNumFile"));
        
        for(long i = 0; i < ((long) 2) << 32; i++)
        {   
          long x = r.nextLong();
          numW.write(String.valueOf(x));
          numW.newLine();
        }
        numW.close();
    }
    
    public static byte[] longToBytes(long l) {
    byte[] result = new byte[8];
    for (int i = 7; i >= 0; i--) {
        result[i] = (byte)(l & 0xFF);
        l >>= 8;
    }
    return result;
}
    
public static long bytesToLong(byte[] b, int s) {
        long result = 0;
            for (int i = s; i < s+8; i++) 
            {
                result <<= 8;
                result |= (b[i] & 0xFF);
            }
            return result;
        }
    
    public static void sortFile() throws IOException, InterruptedException
    {
        long t1 = System.currentTimeMillis();
        
        BufferedReader numR = new BufferedReader(new FileReader("F:\\HugeNumFile"), 268435456);
        long[] nr = new long[536870912]; // array with max size (4g limit) for storing partial sorts
        String s = numR.readLine();
        int f = 0;
        int[] fs = new int[50];
        while(s != null)
        {
          int i;  
          for(i = 0; i < 536870912 && s != null; i++)
          {
              nr[i] = Long.parseLong(s);
              s = numR.readLine();
          }
          i = s == null ? i - 1 : i;
          Arrays.parallelSort(nr, 0, i);
          BufferedOutputStream sr = new BufferedOutputStream(new FileOutputStream("D:\\Data Processing and Analysis\\sortFileA"+f), 268435456);
          for(long l : nr)
          {
              sr.write(longToBytes(l));
          }
          sr.close();
          fs[f] = f;
          f++;
        }
        numR.close();
        long t2 = System.currentTimeMillis();
        System.out.println("Time1 (min): "+(t2-t1)/(1000.0*60));
        
        int sl = (int) Math.ceil(Math.log(f)/Math.log(2));
        int nt = 0; // number of threads currently running
        Thread[] T  = new Thread[50]; 
        String a = "A";
        String b = "B";
        boolean x = true;
        
        for(int k = 0; k < sl-1; k++)
        {
           CountDownLatch lock = new CountDownLatch(4);
           int j = 0;
           for(int i = 0; i < f-1; i+=2)
           {
              Thread t = new MyThread(a+fs[i],a+fs[i+1],b,lock,T[i],T[i+1]);
              t.start();
              T[j] = t;
              nt++;
              if(nt == 4)
              {
                 lock.await();
                 nt = 0;
                 lock = new CountDownLatch(4);
              }
              fs[j] = fs[i];
              j++;
           }
           fs[j] = fs[f-1];
           f = (int) Math.ceil(f/2.0);
           a = x? "B" : "A";
           b = x? "A" : "B";
           x = !x;
        }
        
                if (T[0] != null) 
                { 
                    try 
                    { 
                        T[0].join();
                        T[1].join();
                    } 
                    catch (InterruptedException e) 
                    { 
                        
                    } 
                }
               
                File F1 = new File("D:\\Data Processing and Analysis\\sortFile" + a+fs[0]);
                File F2 = new File("D:\\Data Processing and Analysis\\sortFile" + a+fs[1]);

                BufferedInputStream sf1 = new BufferedInputStream(new FileInputStream(F1), 33554432);
                BufferedInputStream sf2 = new BufferedInputStream(new FileInputStream(F2), 33554432);
                byte[] ch1 = new byte[33554432];
                byte[] ch2 = new byte[33554432];
                int cr1 = sf1.read(ch1);
                int cr2 = sf2.read(ch2);
                int i = 0, j = 0;
                BufferedWriter  sr = new BufferedWriter(new FileWriter("D:\\Data Processing and Analysis\\finalSortedFile"), 33554432);
                boolean eos = false; // end of merge sorting

                do {
                    long l1 = bytesToLong(ch1, i);
                    long l2 = bytesToLong(ch2, j);

                    if (l1 < l2) 
                    {
                        sr.write(""+l1);
                        sr.newLine();
                        i += 8;
                        if (i == cr1) 
                        {  
                            i = 0;
                            cr1 = sf1.read(ch1);
                            eos = cr1 == -1;
                        }
                    } 
                    else 
                    {
                        sr.write(""+l2);
                        sr.newLine();
                        j += 8;
                        if (j == cr2) 
                        {
                            j = 0;
                            cr2 = sf2.read(ch2);
                            eos = cr2 == -1;
                        }
                    }
                    
                } while (!eos);

                if (cr1 == -1) 
                {
                    do {
                        sr.write(""+bytesToLong(ch2, j));
                        sr.newLine();
                        j += 8;
                        if (j == cr2) 
                        {
                           j = 0; 
                           cr2 = sf2.read(ch2);
                        }
                    } while (cr2 != -1);
                } 
                else 
                {
                    do {
                        sr.write(""+bytesToLong(ch1, i));
                        sr.newLine();
                        i += 8;
                        if (i == cr1) 
                        {
                            i = 0;
                            cr1 = sf1.read(ch1);
                        }
                    } while (cr1 != -1);
                }

                sf1.close();
                sf2.close();
                sr.close();
                F1.delete();
                F2.delete();
            long t3 = System.currentTimeMillis();
            System.out.println("Time1 (min): "+(t3-t2)/(1000.0*60));
    }
    
    static class MyThread extends Thread implements Runnable {

        String f1, f2;
        String c;
        CountDownLatch lock;
        Thread pre1;
        Thread pre2;

        MyThread(String f1, String f2, String c, CountDownLatch lock, Thread pre1, Thread pre2) {
            this.f1 = f1;
            this.f2 = f2;
            this.c = c;
            this.lock = lock;
            this.pre1 = pre1;
            this.pre2 = pre2;
        }

        @Override
        public void run() {
            try {
                
                if (pre1 != null) 
                { 
                    try 
                    { 
                        pre1.join();
                        pre2.join();
                    } 
                    catch (InterruptedException e) 
                    { 
                        
                    } 
                }
               
                File F1 = new File("D:\\Data Processing and Analysis\\sortFile" + f1);
                File F2 = new File("D:\\Data Processing and Analysis\\sortFile" + f2);

                BufferedInputStream sf1 = new BufferedInputStream(new FileInputStream(F1), 33554432);
                BufferedInputStream sf2 = new BufferedInputStream(new FileInputStream(F2), 33554432);
                byte[] ch1 = new byte[33554432];
                byte[] ch2 = new byte[33554432];
                int cr1 = sf1.read(ch1);
                int cr2 = sf2.read(ch2);
                int i = 0, j = 0;
                BufferedOutputStream  sr = new BufferedOutputStream(new FileOutputStream("D:\\Data Processing and Analysis\\sortFile" + c + f1.substring(1)), 33554432);
                boolean eos = false; // end of merge sorting

                do {
                    long l1 = bytesToLong(ch1, i);
                    long l2 = bytesToLong(ch2, j);

                    if (l1 < l2) 
                    {
                        sr.write(ch1, i, 8);
                        i += 8;
                        if (i == cr1) 
                        {  
                            i = 0;
                            cr1 = sf1.read(ch1);
                            eos = cr1 == -1;
                        }
                    } 
                    else 
                    {
                        sr.write(ch2, j, 8);
                        j += 8;
                        if (j == cr2) 
                        {
                            j = 0;
                            cr2 = sf2.read(ch2);
                            eos = cr2 == -1;
                        }
                    }
                    
                } while (!eos);

                if (cr1 == -1) 
                {
                    do {
                        sr.write(ch2, j, 8);
                        j += 8;
                        if (j == cr2) 
                        {
                           j = 0; 
                           cr2 = sf2.read(ch2);
                        }
                    } while (cr2 != -1);
                } 
                else 
                {
                    do {
                        sr.write(ch1, i, 8);
                        i += 8;
                        if (i == cr1) 
                        {
                            i = 0;
                            cr1 = sf1.read(ch1);
                        }
                    } while (cr1 != -1);
                }

                sf1.close();
                sf2.close();
                sr.close();
                F1.delete();
                F2.delete();
                lock.countDown();
            } 
            catch (IOException ex) 
            {
                Logger.getLogger(HugeNumFile.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
   
    public static void main(String args[]) throws IOException, InterruptedException
    {
        //WriteFile();
        sortFile();
    }
}
