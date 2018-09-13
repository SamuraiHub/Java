package UVA_ACM;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Muaz
 */
class Main1{

    /**
     * @param args the command line arguments
     */
    
    static String ReadLn (int maxLg)  // utility function to read from stdin
    {
        byte lin[] = new byte [maxLg];
        int lg = 0, car = -1;
        String line = "";

        try
        {
            while (lg < maxLg)
            {
                car = System.in.read();
                if ((car < 0) || (car == '\n')) break;
                lin [lg++] += car;
            }
        }
        catch (IOException e)
        {
            return (null);
        }

        if ((car < 0) && (lg == 0)) return (null);  // eof
        return (new String (lin, 0, lg));
    }

    
    public static void main(String[] args) throws IOException {
        try {
            // TODO code application logic here
         
            Main1 myWork = new Main1();  // the true entry point
            myWork.Begin();            // the true entry point
        } 
        catch (NullPointerException ex) 
        {
            ex.printStackTrace();
            Logger.getLogger(Main1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void Begin() throws IOException
    {
        int num, n = 0, j = 1; char c;
        
        while ((c = (char) System.in.read()) != ' ') 
        {
            n = (n * 10) + Character.digit(c, 10);
        }
        
        num = Integer.parseInt(Main.ReadLn(5));

        while (n != 0 && num != 0) 
        {
            int min = 60;   
                
                for (int i = 0; i < n; i++) 
                {
                   min = Math.min(min, Integer.parseInt(Main.ReadLn(5)));
                }
                for (int i = 0; i < num; i++) 
                {
                   Main.ReadLn(5);
                }
            
            
            if (n <= num) 
            {
               System.out.println("Case " + j + ": 0");
            }
            else
            {
                System.out.println("Case " + j + ": " + (n - num) + " " + min);
            }
            
             n = 0;
            
            while((c = (char) System.in.read()) != ' ')
            {
                n = (n * 10) + Character.digit(c, 10);
            }
            
            num = Integer.parseInt(Main.ReadLn(5));
            
            j++;
        }
    }
}