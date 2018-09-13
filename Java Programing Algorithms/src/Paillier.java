
import java.io.*;
import java.util.*;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Aljarhi
 */

public class Paillier 
{


public Paillier()
{


}

public static void main(String[] args) throws IOException
{
  
  BufferedReader gr = new BufferedReader(new FileReader("G:/Stuff/Downloads/GRE/Word Lists/GRE Word List.txt"));
     
     String[] words = new String[10952]; String r; 
     
     for(int i = 0; (r = gr.readLine()) != null; i++)
     {
         words[i] = r; gr.readLine();
     }
     
     gr.close(); Arrays.sort(words); 
    
     List<String> Words = new ArrayList(Arrays.asList(words));
     
     BufferedReader reader = new BufferedReader(new FileReader("G:/Stuff/Downloads/GRE/Word Lists/wordlist.txt"));
     
     while((r = reader.readLine()) != null) 
     {
         if(!"".equals(r))
         {
         r = Character.toUpperCase(r.charAt(0)) + r.substring(1);
         int t = Collections.binarySearch(Words, r/*.substring(0, r.indexOf(' ')) */); 
         if(t < 0)
         {
             Words.add(-t-1,  r/*.substring(0, r.indexOf(' ')) */);
         }
         }
     }
     
          reader.close(); 
          
          BufferedWriter writer = new BufferedWriter(new FileWriter("G:/Stuff/Downloads/GRE/Word Lists/GRE Word List.txt"));
     
     for(int i = 0; i<Words.size(); i++)
     {
         writer.write(Words.get(i) + "\r\n\r\n");
     }
  
     writer.close(); 
    
 /* BufferedReader reader = new BufferedReader(new FileReader("G:/Stuff/Downloads/GRE/Word Lists/wordlist.txt"));
    
     String r; boolean dot = false; List<String> Words = new ArrayList(4500);
    
     while((r = reader.readLine()) != null)
     {
         if(!"".equals(r))
         {
             if(Character.isDigit(r.charAt(0)))
             {
                int i = r.indexOf(' '); 
                System.out.println(r);
                Words.add(r.substring(i+1, r.indexOf(' ', i+1)));
             }
         }
     }
          reader.close();
    
    BufferedWriter writer = new BufferedWriter(new FileWriter("G:/Stuff/Downloads/GRE/Word Lists/wordlist.txt"));
    
        for(int i = 0; i<Words.size(); i++)
     {
         writer.write(Words.get(i) + "\r\n\r\n");
     }
  
     writer.close();  */
    
} 

}
