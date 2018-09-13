

import java.io.*;







/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Al-Jarhi
 */
public class SPARQL_Query {

public static void main(String[] args) throws IOException
{
   /* BufferedReader r = new BufferedReader(new FileReader("F:/Stuff/downloads/GRE/GRE Word List With Meanings-Sentence-Mnemonic.txt"));
    
    String s;
    
    while((s = r.readLine()) != null)
    {
        
        
        String def = r.readLine();
        String ex = r.readLine();
        r.readLine();
        if(ex.equals("Examples"))
            System.out.println(s);
        if(s.equals("Zealous"))
            System.out.println(ex);
    } */
   
   /* BufferedReader r = new BufferedReader(new FileReader("F:/Stuff/downloads/GRE/WM.txt"));
    BufferedWriter w = new BufferedWriter(new FileWriter("F:/Stuff/downloads/GRE/WMC.txt"));
    String s;
    while((s = r.readLine()) != null)
    {
        String me = r.readLine();
        w.write(s + "," + me + "\r\n");
    }
    r.close();
    w.close(); */
    
    BufferedReader gr = new BufferedReader(new FileReader("F:/Stuff/Downloads/GRE/Word Lists/GRE Word List.txt"));
        
           String[] words = new String[10967]; String r; 
     int i;
     for(i = 0; (r = gr.readLine()) != null; i++)
     {
         words[i] = r; gr.readLine();
     }
     
     gr.close();
     
     for(i = 0; i <100; i++)
    {
        BufferedWriter w = new BufferedWriter(new FileWriter("F:/Stuff/downloads/GRE/Word Lists/List "+(i+1)+".wl"));
        int j;
        for(j = 0; (i*110)+j < 10966 && j < 109; j++)
        {
            w.write(words[(i*110)+j]+"\r\n\r\n");
        }
        w.write(words[(i*110)+j]);
        w.close();
    }
}

}
