/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Cordic;

import java.util.Random;

/**
 *
 * @author Aljarhi
 */
public class Radix4WCSF {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        Random R = new Random();
        
        double[] Z = new double[2];
        
        Z[1] = 1.99; 
        System.out.println(Z[1]);
        
        String mz = Long.toBinaryString((long) (Z[1] * Math.pow(2, 61))); while(mz.length() < 63) { mz = '0' + mz; }
        System.out.println(mz);
        
        for(int i = 0; i < 55; i = i + 2)
        {
            int s = Math.abs(Math.abs(Z[1]) - Math.pow(2, -i+1)) <= Math.abs(Math.abs(Z[1]) - Math.pow(2, -i)) ? 2 
                    : 1;
                    
            s = Z[1] >= 0 ? s : -s;
            
            System.out.println(s);
            
           Z[1] = Z[1] - (s * Math.pow(2, -i));
          
            System.out.println(Z[1]);
            
            if(Z[1] < 0)
          { 
            mz = invertNum(Long.toBinaryString((long) (-Z[1] * Math.pow(2, 61))));
            String lz = mz.indexOf('1') < 0 ? mz.substring(0, mz.length() - 1) : mz.substring(0, mz.indexOf('1')); 
            mz = Long.toBinaryString(Long.valueOf(mz, 2) + 1); 
            mz = lz + mz; while(mz.length() < 63) { mz = '1' + mz; } 
            while(mz.length() < 63) { mz = '0' + mz; }
          }
          
          else
          {
            mz = Long.toBinaryString((long) (Z[1] * Math.pow(2, 61))); while(mz.length() < 63) { mz = '0' + mz; }  
          }
            
            System.out.println(mz);
        }
    }
    
    public static String invertNum(String num)
    {
        String nn = "";
        
        for(int i = 0; i < num.length(); i++)
        {
           if(num.charAt(i) == '0')
           {
               nn = nn + '1';
           }
                 
           else
           {
              nn = nn + '0'; 
           }
         
        }        
        
        return nn;
    }
    
     public static int sel(String s, int i, int m)
    {
        if(i == 0 && m == 1 || i == 2 && m == -1)
        {
          if ("000".equals(s)) 
            {
                return 1;
            }
            if ("001".equals(s)) 
            {
                return 1;
            }
            if ("010".equals(s)) 
            {
                return 2;
            }
            if ("011".equals(s)) 
            {
                return 2;
            }
            if ("100".equals(s)) 
            {
                return -2;
            }
            if ("101".equals(s)) 
            {
                return -2;
            }
            if ("110".equals(s)) 
            {
                return -2;
            }
            if ("111".equals(s)) 
            {
                return 0;
            }  
        }
        else
        {
            if ("000".equals(s)) 
            {
                return 0;
            }
            if ("001".equals(s)) 
            {
                return 1;
            }
            if ("010".equals(s)) 
            {
                return 1;
            }
            if ("011".equals(s)) 
            {
                return 2;
            }
            if ("100".equals(s)) 
            {
                return -2;
            }
            if ("101".equals(s)) 
            {
                return -1;
            }
            if ("110".equals(s)) 
            {
                return -1;
            }
            if ("111".equals(s)) 
            {
                return 0;
            }
        }
        
        return 0;
    }
}
