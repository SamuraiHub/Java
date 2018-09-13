package Project_Euler;

import java.util.ArrayList;
import java.util.List;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Aljarhi
 */
public class Largest_Prime_Factor {

    /**
     * @param args the command line arguments
     */
    
    public static long getLargestPrimeFactor(long l, int[] x)
    {
        long p = l, n = 0, m = 0;
       
        if(l%2 == 0)
        {
          n = l/2;
          m = 2;
        }
        
        else if(l%3 == 0)
        {
          n = l/3;
          m = 3;
        }
        
        else if(l%5 == 0)
        {
          n = l/5; 
          m = 5;
        }
        
        else if(l%7 == 0)
        {
           n = l/7; 
           m = 7;
        }
        
        else if(l%11 == 0)
        {
          n = l/11; 
          m = 11;
        }
        
        else
        {
            long i = 13, r = (long) Math.sqrt(l); 
            
            for(int j = 0; i <= r; j++)
            {
                if (l % i == 0) 
                {
                  n = l/i;
                  m = i;
                  break;
                } 
                
                i+=x[j];
                if(j == (x.length-1)) { j = -1; }
            }
        }
        
        if(n != 0)
        {
           long y = getLargestPrimeFactor(n, x);
           long z = getLargestPrimeFactor(m, x);
           
           p = Math.max(y, z);
        }
        
        return p;
    }
    
    public static void primeStepSeq()
    {
        for(int i = 17, j = 17; i < 50000; i++)
        {
            if(i%2 != 0 && i%3 != 0 && i%5 != 0 && i%7 != 0 && i%11 != 0 && i%13 != 0)
            {
                System.out.print((i-j) + ", ");
                j = i;
            }
        }
    }
    
    
    
    public static void main(String[] args) 
    {
        // TODO code application logic here
        
        int array1[] = {4, 2, 4, 6, 2, 6, 4, 2, 4, 6, 6, 2, 6, 4, 2, 6, 4, 6, 8, 4, 2, 4, 2, 4, 14, 4, 6, 2, 10, 2, 6, 6, 4, 2, 4, 6, 2, 10, 2, 4, 2, 12, 10, 2, 4, 2, 4, 6, 2, 6, 4, 6, 6, 6, 2, 6, 4, 2, 6, 4, 6, 8, 4, 2, 4, 6, 8, 6, 10, 2, 4, 6, 2, 6, 6, 4, 2, 4, 6, 2, 6, 4, 2, 6, 10, 2, 10, 2, 4, 2, 4, 6, 8, 4, 2, 4, 12, 2, 6, 4, 2, 6, 4, 6, 12, 2, 4, 2, 4, 8, 6, 4, 6, 2, 4, 6, 2, 6, 10, 2, 4, 6, 2, 6, 4, 2, 4, 2, 10, 2, 10, 2, 4, 6, 6, 2, 6, 6, 4, 6, 6, 2, 6, 4, 2, 6, 4, 6, 8, 4, 2, 6, 4, 8, 6, 4, 6, 2, 4, 6, 8, 6, 4, 2, 10, 2, 6, 4, 2, 4, 2, 10, 2, 10, 2, 4, 2, 4, 8, 6, 4, 2, 4, 6, 6, 2, 6, 4, 8, 4, 6, 8, 4, 2, 4, 2, 4, 8, 6, 4, 6, 6, 6, 2, 6, 6, 4, 2, 4, 6, 2, 6, 4, 2, 4, 2, 10, 2, 10, 2, 6, 4, 6, 2, 6, 4, 2, 4, 6, 6, 8, 4, 2, 6, 10, 8, 4, 2, 4, 2, 4, 8, 10, 6, 2, 4, 8, 6, 6, 4, 2, 4, 6, 2, 6, 4, 6, 2, 10, 2, 10, 2, 4, 2, 4, 6, 2, 6, 4, 2, 4, 6, 6, 2, 6, 6, 6, 4, 6, 8, 4, 2, 4, 2, 4, 8, 6, 4, 8, 4, 6, 2, 6, 6, 4, 2, 4, 6, 8, 4, 2, 4, 2, 10, 2, 10, 2, 4, 2, 4, 6, 2, 10, 2, 4, 6, 8, 6, 4, 2, 6, 4, 6, 8, 4, 6, 2, 4, 8, 6, 4, 6, 2, 4, 6, 2, 6, 6, 4, 6, 6, 2, 6, 6, 4, 2, 10, 2, 10, 2, 4, 2, 4, 6, 2, 6, 4, 2, 10, 6, 2, 6, 4, 2, 6, 4, 6, 8, 4, 2, 4, 2, 12, 6, 4, 6, 2, 4, 6, 2, 12, 4, 2, 4, 8, 6, 4, 2, 4, 2, 10, 2, 10, 6, 2, 4, 6, 2, 6, 4, 2, 4, 6, 6, 2, 6, 4, 2, 10, 6, 8, 6, 4, 2, 4, 8, 6, 4, 6, 2, 4, 6, 2, 6, 6, 6, 4, 6, 2, 6, 4, 2, 4, 2, 10, 12, 2, 4, 2, 10, 2, 6, 4, 2, 4, 6, 6, 2, 10, 2, 6, 4, 14, 4, 2, 4, 2, 4, 8, 6, 4, 6, 2, 4, 6, 2, 6, 6, 4, 2, 4, 6, 2, 6, 4, 2, 4, 12, 2, 12};
        
        long sum = 0;
        
        Long Start = System.currentTimeMillis();
        
       sum = getLargestPrimeFactor(600851475143L, array1);
        
        //primeStepSeq();
        
       System.out.println(sum);
        
       Long Finish = System.currentTimeMillis();
       
       System.out.println((Finish-Start)/1000.0);

    }
}
