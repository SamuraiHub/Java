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
public class Radix8Cordic {

    /**
     * @param args the command line arguments
     */
    
    public static int sel(String s, int i)
    {
        if(i == 0)
        {
          if ("0000".equals(s)) 
            {
                return 0;
            }
            if ("0001".equals(s)) 
            {
                return 1;
            }
            if ("0010".equals(s)) 
            {
                return 2;
            }
            if ("0011".equals(s)) 
            {
                return 2;
            } 
        }
        else
        {
            if ("0000".equals(s)) 
            {
                return 0;
            }
            if ("0001".equals(s)) 
            {
                return 1;
            }
            if ("0010".equals(s)) 
            {
                return 1;
            }
            if ("0011".equals(s)) 
            {
                return 2;
            }
            if ("0100".equals(s)) 
            {
                return 2;
            }
            if ("0101".equals(s)) 
            {
                return 3;
            }
            if ("0110".equals(s)) 
            {
                return 3;
            }
            if ("0111".equals(s)) 
            {
                return 4;
            }
            if ("1000".equals(s)) 
            {
                return -4;
            }
            if ("1001".equals(s)) 
            {
                return -3;
            }
            if ("1010".equals(s)) 
            {
                return -3;
            }
            if ("1011".equals(s)) 
            {
                return -2;
            }
            if ("1100".equals(s)) 
            {
                return -2;
            }
            if ("1101".equals(s)) 
            {
                return -1;
            }
            if ("1110".equals(s)) 
            {
                return -1;
            }
            if ("1111".equals(s)) 
            {
                return 0;
            }
        }
        
        return 0; 
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
    
    public static void main(String[] args) {
        // TODO code application logic 
        Random r = new Random();
        
        double[] x = new double[2];
        double[] y = new double[2];
        double[] z = new double[2];
        
        x[0] = 1; y[0] = 0; z[0] = 0.4322594166874873;//r.nextDouble() * Math.PI/2; 
        z[1] = z[0];
        
        String Z = Long.toBinaryString((long) (z[0] * Math.pow(2, 61)));
        while(Z.length() < 64) {Z = '0' + Z;}
        
        for(int i = 0; i < 54; i = i+3)
        {
            System.out.println(z[1]);
            System.out.println(Z);
            int s = sel(Z.substring(i, i+4), i);
            System.out.println(s);
            
            double k = 1.0/(Math.sqrt(1+(Math.pow(s, 2) * Math.pow(2, (2 * -i)))));
            
            x[1] = k*(x[0] - (s * y[0] * Math.pow(2, -i)));
            y[1] = k*(y[0] + (s * x[0] * Math.pow(2, -i)));
            z[1] = z[1] - Math.atan(s * Math.pow(2, -i));

            x[0] = x[1]; y[0] = y[1];
            
            if(z[1] < 0)
          { 
            Z = invertNum(Long.toBinaryString((long) (-z[1] * Math.pow(2, 61))));
            String lz = Z.indexOf('1') < 0 ? Z.substring(0, Z.length() - 1) : Z.substring(0, Z.indexOf('1')); 
            Z = Long.toBinaryString(Long.valueOf(Z, 2) + 1); 
            Z = lz + Z; while(Z.length() < 64) { Z = '1' + Z; } 
            
          }
          
          else
          {
            Z = Long.toBinaryString((long) (z[1] * Math.pow(2, 61))); while(Z.length() < 64) { Z = '0' + Z; }  
          }
        }
        
        System.out.println("z: "+z[0]);
        System.out.println("x: "+x[1]);
        System.out.println("y: "+y[1]);
    }
}
