/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package UVA_ACM;

import java.io.IOException;
import java.util.*;

/**
 *
 * @author Muaz
 */
class Main2 {

    /**
     * @param args the command line arguments
     */
    
    static String ReadLn (int maxLg)  // utility function to read from stdin
    {
        byte lin[] = new byte [maxLg];
        int lg = 0, car = -1;
      
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
        // TODO code application logic here
        Main2 myWork = new Main2();  // create a dinamic instance
        myWork.Begin();            // the true entry point
    }

    void Begin() throws IOException
    {
        int num, n = 0, j = 1; char c;

        while ((c = (char) System.in.read()) != ' ') 
        {
            n = (n * 10) + (Character.digit(c, 10));
        }
        
        num = Integer.parseInt(Main.ReadLn(5));
        
        while (n != 0 && num != 0) 
        {
            int k = 1, m = num;

            ArrayDeque<Integer> e = new ArrayDeque<Integer>();
            
            System.out.println("Case " + j + ":"); 
            
             for(int i = 0; i < num; i++)
             {
               c = (char) System.in.read();
               
               if(i >= m){ System.in.read(); continue; }

               if(c == 'N')
               { 
                 /* if(k <= n /*&& !e.contains(k)) 
                  { 
                      e.addFirst(k);
                      System.out.println(e.getFirst());
                  }
                  else if(k >= n) 
                  { 
                      e.addFirst(e.removeLast());
                      System.out.println(e.getFirst()); 
                  } */
                 //else
                // {
                   //  m++;
                // } 
                  
                  System.in.read();
                  k++;
               }
               else
               {
                   System.in.read();
                   int a= Integer.parseInt(Main.ReadLn(5));
                   System.out.println(a);
                   e.remove(a);
                   e.addFirst(a);
                   m--; 
               }         
             }
        
             n = 0;
            
            while((c = (char) System.in.read()) != ' ')
            {
                n = (n * 10) + Character.digit(c, 10);
            }

            //num = Integer.parseInt(Main.ReadLn(5)); 
            
            j++;  
        }
    }
}
