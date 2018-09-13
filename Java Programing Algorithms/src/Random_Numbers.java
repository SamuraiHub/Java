
import java.io.*;
import java.util.Random;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Al-Jarhi
 */
public class Random_Numbers {

    public static int[] rand1()
    {
        int[] rn = new int[10];

        for(int i=0; i<10; i++)
        {
           rn[i] = (int) ((Math.random()*9)+1);
        }
       return rn;
    }

    public static int[] rand2()
    {
        int[] rn = new int[10];

       Random r = new Random(System.currentTimeMillis());

          for(int i=0; i<10; i++)
        {
           rn[i] = r.nextInt(10) + 1;
        }

       return rn;
    }


    public static void main(String[] args) throws FileNotFoundException, IOException
    {
        long start = System.nanoTime();
        
        RandomAccessFile RW = new RandomAccessFile("G:/Stuff/Muaz/Programming/noob.txt", "rwd");
        
        long a = ("Hi ya noob                           \r\n\r\n"
                + "how you doing?                  \r\n\r\n").length();
        
        RW.seek(a); RW.writeBytes("Are you not fine?"); RW.close();
        
        long end = System.nanoTime();
        
        System.out.println((end - start)/1000);
        
        start = System.nanoTime();
        
        File f = new File("G:/Stuff/Muaz/Programming/noob1.txt"); 
        
       BufferedReader r = new BufferedReader(new FileReader(f));
       
       File f1 = new File("G:/Stuff/Muaz/Programming/noob1.tmp");
       
       BufferedWriter w = new BufferedWriter(new FileWriter(f1));
       
       String s = r.readLine(); w.write(s + "\r\n"); 
              s = r.readLine(); w.write(s + "\r\n");
              s = r.readLine(); w.write(s + "\r\n"); 
              s = r.readLine(); w.write(s + "\r\n");
              s = r.readLine(); w.write("Are you not fine?" + "\r\n");
              s = r.readLine(); w.write(s + "\r\n"); 
              s = r.readLine(); w.write(s);
              
              r.close(); w.close(); f.delete(); f1.renameTo(new File("G:/Stuff/Muaz/Programming/noob1.txt"));
              
             end = System.nanoTime(); 
             
              System.out.println((end - start)/1000);
              
    }



}
