
import java.io.*;
import java.util.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Muaz Aljarhi
 */
public class ReadWriteALargeTextFile {
    
    public static void main(String[] args) throws FileNotFoundException, IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(new BufferedInputStream(new FileInputStream("F:\\Downloads\\Other\\words.txt"))));
        List<String> Words = new ArrayList(479200);
        String s;
        while((s = br.readLine()) != null)
        {
           if(!"".equals(s) && !s.contains("/") && !s.contains("\"") && !s.contains("&")) 
               Words.add(s);
        }
        int m = 1900;
        while(m < 2100)
        {
           Words.add(Integer.toString(m));
            m++;
        }
        br.close();
        Collections.shuffle(Words);
        
        Random R = new Random(); //766958446
        Random rw[] = new Random[3];
        rw[0] = new Random(); rw[1] = new Random(); rw[2] = new Random();
        
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new BufferedOutputStream(new FileOutputStream("F:\\Downloads\\Other\\PhrasesST.txt"))));
        int j = 95865446;//766958446;
        while(j > 0)
        {
          String sw = "";  
          for(int k = 0; k < 49; k++)
           {
            int n = R.nextInt(3)+1; // 0,1,2 +1 = 1,2,3
            j = j - n;
            for(m = 0; m < n; m++)
            {
              String wd = Words.get(rw[m].nextInt(Words.size()));  
              sw = sw + wd.substring(0, 1).toUpperCase() + wd.substring(1) +" "; 
            }
            sw = sw + "| ";
          }
          int n = R.nextInt(3)+1; // 0,1,2 +1 = 1,2,3
          j = j - n;
          for(m = 0; m < n; m++)
          {
              String wd = Words.get(rw[m].nextInt(Words.size()));  
              sw = sw + wd.substring(0, 1).toUpperCase() + wd.substring(1) +" ";  
          }
          bw.write(sw.substring(0, sw.length()-1));
          bw.newLine();
        }
        bw.close();
    }
}
