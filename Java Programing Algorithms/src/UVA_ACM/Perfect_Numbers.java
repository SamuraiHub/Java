package UVA_ACM;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.*;
import java.util.*;

/**
 *
 * @author Muaz
 */
class Main{

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

    
    public static void main(String[] args) {
        // TODO code application logic here
    Main myWork = new Main();  // create a dinamic instance
        myWork.Begin();            // the true entry point
    }

    void Begin()
    {
        String input;
        String[] idata;
        int num, n, pn;
        
        int array1[] = {4, 2, 4, 6, 2, 6, 4, 2, 4, 6, 6, 2, 6, 4, 2, 6, 4, 6, 8, 4, 2, 4, 2, 4, 14, 4, 6, 2, 10, 2, 6, 6, 4, 2, 4, 6, 2, 10, 2, 4, 2, 12, 10, 2, 4, 2, 4, 6, 2, 6, 4, 6, 6, 6, 2, 6, 4, 2, 6, 4, 6, 8, 4, 2, 4, 6, 8, 6, 10, 2, 4, 6, 2, 6, 6, 4, 2, 4, 6, 2, 6, 4, 2, 6, 10, 2, 10, 2, 4, 2, 4, 6, 8, 4, 2, 4, 12, 2, 6, 4, 2, 6, 4, 6, 12, 2, 4, 2, 4, 8, 6, 4, 6, 2, 4, 6, 2, 6, 10, 2, 4, 6, 2, 6, 4, 2, 4, 2, 10, 2, 10, 2, 4, 6, 6, 2, 6, 6, 4, 6, 6, 2, 6, 4, 2, 6, 4, 6, 8, 4, 2, 6, 4, 8, 6, 4, 6, 2, 4, 6, 8, 6, 4, 2, 10, 2, 6, 4, 2, 4, 2, 10, 2, 10, 2, 4, 2, 4, 8, 6, 4, 2, 4, 6, 6, 2, 6, 4, 8, 4, 6, 8, 4, 2, 4, 2, 4, 8, 6, 4, 6, 6, 6, 2, 6, 6, 4, 2, 4, 6, 2, 6, 4, 2, 4, 2, 10, 2, 10, 2, 6, 4, 6, 2, 6, 4, 2, 4, 6, 6, 8, 4, 2, 6, 10, 8, 4, 2, 4, 2, 4, 8, 10, 6, 2, 4, 8, 6, 6, 4, 2, 4, 6, 2, 6, 4, 6, 2, 10, 2, 10, 2, 4, 2, 4, 6, 2, 6, 4, 2, 4, 6, 6, 2, 6, 6, 6, 4, 6, 8, 4, 2, 4, 2, 4, 8, 6, 4, 8, 4, 6, 2, 6, 6, 4, 2, 4, 6, 8, 4, 2, 4, 2, 10, 2, 10, 2, 4, 2, 4, 6, 2, 10, 2, 4, 6, 8, 6, 4, 2, 6, 4, 6, 8, 4, 6, 2, 4, 8, 6, 4, 6, 2, 4, 6, 2, 6, 6, 4, 6, 6, 2, 6, 6, 4, 2, 10, 2, 10, 2, 4, 2, 4, 6, 2, 6, 4, 2, 10, 6, 2, 6, 4, 2, 6, 4, 6, 8, 4, 2, 4, 2, 12, 6, 4, 6, 2, 4, 6, 2, 12, 4, 2, 4, 8, 6, 4, 2, 4, 2, 10, 2, 10, 6, 2, 4, 6, 2, 6, 4, 2, 4, 6, 6, 2, 6, 4, 2, 10, 6, 8, 6, 4, 2, 4, 8, 6, 4, 6, 2, 4, 6, 2, 6, 6, 6, 4, 6, 2, 6, 4, 2, 4, 2, 10, 12, 2, 4, 2, 10, 2, 6, 4, 2, 4, 6, 6, 2, 10, 2, 6, 4, 14, 4, 2, 4, 2, 4, 8, 6, 4, 6, 2, 4, 6, 2, 6, 6, 4, 2, 4, 6, 2, 6, 4, 2, 4, 12, 2, 12};

        n = Integer.parseInt(Main.ReadLn(10));
        input = Main.ReadLn(n*33);
        idata = input.split(",");
        for(int i = 0; i < idata.length; i++)
        {
            num = Integer.parseInt(idata[i]);
            
            pn = (int) (Math.pow(2, num) - 1);
        
        if(num == 1)
        {
          System.out.println("No");
        }    
            
        else if(num == 2)
        {
          System.out.println("Yes");
        }
        
        else if(num == 3)
        {
          System.out.println("Yes");
        }
        
        else if(num == 5)
        {
          System.out.println("Yes");
        }
        
        else if(num == 7)
        {
          System.out.println("Yes");
        }
                
        else if(num%2 == 0 || pn%2 == 0)
        {
          System.out.println("No");
        }
        
        else if(num%3 == 0 || pn%3 == 0)
        {
          System.out.println("No");
        }
        
        else if(num%5 == 0 || pn%5 == 0)
        {
          System.out.println("No");
        }
        
        else if(num%7 == 0 || pn%7 == 0)
        {
          System.out.println("No");
        }
        
        else if(num%11 == 0 || pn%11 == 0)
        {
          System.out.println("No");
        }
        
        else
        {
            int k = 13, r1 = (int) Math.sqrt(num), r2 = (int) Math.sqrt(pn); 
            boolean t = true;
            
            for(int j = 0; k <= r2; j++)
            {
                if((k <= r1 && num % k == 0) || pn % k == 0) 
                {
                  t = false;  
                  System.out.println("No");
                  break;
                } 
                
                k+=array1[j];
                if(j == (array1.length-1)) { j = -1; }
            }
            
            if(t)
            {  
                int pnt = 0, j = 0;
                
                for(; j < num-1; j++)
                {
                    pnt = (int) (pnt + Math.pow(2, j) + (pn * Math.pow(2, j)));
                }
                
                pnt = (int) (pnt + Math.pow(2, j));
                
                pn =  (int) (Math.pow(2, j) * pn);
                
                if(pnt == pn)
                {
                    System.out.println("Yes");
                }
                
                else
                {
                    System.out.println("No");
                }          
            }
        }
        }
    }
}