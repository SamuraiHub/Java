/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Project_Euler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Aljarhi
 */
public class Smallest_multiple {

    /**
     * @param args the command line arguments
     */
    
    public static long GetsmallestMultiple(int a)
    {
        if(a == 1)
        return 1;
        
        int array1[] = {4, 2, 4, 6, 2, 6, 4, 2, 4, 6, 6, 2, 6, 4, 2, 6, 4, 6, 8, 4, 2, 4, 2, 4, 14, 4, 6, 2, 10, 2, 6, 6, 4, 2, 4, 6, 2, 10, 2, 4, 2, 12, 10, 2, 4, 2, 4, 6, 2, 6, 4, 6, 6, 6, 2, 6, 4, 2, 6, 4, 6, 8, 4, 2, 4, 6, 8, 6, 10, 2, 4, 6, 2, 6, 6, 4, 2, 4, 6, 2, 6, 4, 2, 6, 10, 2, 10, 2, 4, 2, 4, 6, 8, 4, 2, 4, 12, 2, 6, 4, 2, 6, 4, 6, 12, 2, 4, 2, 4, 8, 6, 4, 6, 2, 4, 6, 2, 6, 10, 2, 4, 6, 2, 6, 4, 2, 4, 2, 10, 2, 10, 2, 4, 6, 6, 2, 6, 6, 4, 6, 6, 2, 6, 4, 2, 6, 4, 6, 8, 4, 2, 6, 4, 8, 6, 4, 6, 2, 4, 6, 8, 6, 4, 2, 10, 2, 6, 4, 2, 4, 2, 10, 2, 10, 2, 4, 2, 4, 8, 6, 4, 2, 4, 6, 6, 2, 6, 4, 8, 4, 6, 8, 4, 2, 4, 2, 4, 8, 6, 4, 6, 6, 6, 2, 6, 6, 4, 2, 4, 6, 2, 6, 4, 2, 4, 2, 10, 2, 10, 2, 6, 4, 6, 2, 6, 4, 2, 4, 6, 6, 8, 4, 2, 6, 10, 8, 4, 2, 4, 2, 4, 8, 10, 6, 2, 4, 8, 6, 6, 4, 2, 4, 6, 2, 6, 4, 6, 2, 10, 2, 10, 2, 4, 2, 4, 6, 2, 6, 4, 2, 4, 6, 6, 2, 6, 6, 6, 4, 6, 8, 4, 2, 4, 2, 4, 8, 6, 4, 8, 4, 6, 2, 6, 6, 4, 2, 4, 6, 8, 4, 2, 4, 2, 10, 2, 10, 2, 4, 2, 4, 6, 2, 10, 2, 4, 6, 8, 6, 4, 2, 6, 4, 6, 8, 4, 6, 2, 4, 8, 6, 4, 6, 2, 4, 6, 2, 6, 6, 4, 6, 6, 2, 6, 6, 4, 2, 10, 2, 10, 2, 4, 2, 4, 6, 2, 6, 4, 2, 10, 6, 2, 6, 4, 2, 6, 4, 6, 8, 4, 2, 4, 2, 12, 6, 4, 6, 2, 4, 6, 2, 12, 4, 2, 4, 8, 6, 4, 2, 4, 2, 10, 2, 10, 6, 2, 4, 6, 2, 6, 4, 2, 4, 6, 6, 2, 6, 4, 2, 10, 6, 8, 6, 4, 2, 4, 8, 6, 4, 6, 2, 4, 6, 2, 6, 6, 6, 4, 6, 2, 6, 4, 2, 4, 2, 10, 12, 2, 4, 2, 10, 2, 6, 4, 2, 4, 6, 6, 2, 10, 2, 6, 4, 14, 4, 2, 4, 2, 4, 8, 6, 4, 6, 2, 4, 6, 2, 6, 6, 4, 2, 4, 6, 2, 6, 4, 2, 4, 12, 2, 12};
        
        List<Integer> b = new ArrayList<Integer>(a/2);
        b.addAll(Arrays.asList(2, 3, 5, 7, 11));        
        
        for(int i = 13, m = 0; b.size() < 10001; i+=array1[m], m++)
        {
            boolean t = false;
            
            int k = 13;  int r = (int) Math.sqrt(i); 
            
            if(i == 169)
            {
                t = false;
            }
            
            for(int j = 0; k <= r; j++)
            {
                if (i % k == 0) 
                {
                  t = true;
                  break;
                } 
                
                k+=array1[j];
                if(j == (array1.length-1)) { j = -1; }
            }

            if(!t) { b.add(i); } 
            
            if(m == (array1.length)) { m = 0; }
        }
        
            System.out.print(b.get(10000));

        long p = 1;
        
        for(int i = 0; i  < 20; i++)
        {            
            int j = 1;
            
            while(j <= a)
            {
                j = j*b.get(i);
            }
            
            p = p*j/b.get(i);
        }
        
        System.out.println();

        return p;
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
        
        Long Start = System.currentTimeMillis();
        
        long sm = 0; 
         
        //sm = GetsmallestMultiple(20);
       
        for(int i = 0; i < 1000; i++)
        {
            for(int j = 1000-i; j >= 0; j--)
            {
                double k = Math.sqrt(Math.pow(i, 2) + Math.pow(j, 2));
                
                if(i+j+k == 1000)
                {
                   System.out.println(i*j*k); 
                }
            }
        }
        
        System.out.println(sm);
       
       Long Finish = System.currentTimeMillis();
       
       System.out.println((Finish-Start)/1000.0);
    }
}
