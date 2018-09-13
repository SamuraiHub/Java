/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Cordic;

import java.math.*;
import java.util.Random;

/**
 *
 * @author Aljarhi
 */
public class Para_Cordic {
    
    public static double error(int[] mar, boolean m)
    {
        double error = 0;
        
        for(int i = 0; i < mar.length; i++)
        {
                if(!m)
                {
                   error = error + Math.atan(Math.pow(2, -mar[i])); 
                }
                else
                {
                    if(i == 0)
                      error = error + atanh(Math.pow(2, -mar[i]));
                    else
                      error = error - atanh(Math.pow(2, -mar[i]));  
                }
        }
        
        return error;
    }
    
    public static double atanh(double a) 
    {  
       return 0.5 * Math.log((1 + a) / (1 - a));
    }
    
    public static double scaleFactor(int[][] mar, boolean m)
    {
        double k = 1.0;
        
        for(int i = m ? 1 : 0; i < mar.length; i++)
        {
            for(int j = 0; j < mar[i].length; j++)
            {
                if(!m)
                {
                   k = k * Math.sqrt(1+(Math.pow(2, (2 * -mar[i][j])))); 
                }
                else
                {
                   k = k * Math.sqrt(1-(Math.pow(2, (2 * -mar[i][j]))));  
                }
            }
        }
        
        for(int i = 7; i < 28; i++)
        {
            if(!m)
            {
                k = k * Math.sqrt(1+(Math.pow(2, (2 * -i))));
            }
            else
            {
                k = k * Math.sqrt(1-(Math.pow(2, (2 * -i))));
            }
        }

        return k;
    }
    
    public static void main(String[] args)
    {
        int[][] marc = new int[7][]; 
        marc[0] = new int[]{0, 3, 4, 6, 7, 8, 12, 13, 14, 15, 16, 17, 18};
        marc[1] = new int[]{1, 5, 8, 10, 13, 14, 15, 16};
        marc[2] = new int[]{2, 8, 10, 13, 16};
        marc[3] = new int[]{3,11,13,15,18};
        marc[4] = new int[]{4,14,16,18};
        marc[5] = new int[]{5,17,19};
        marc[6] = new int[]{6,20};
        
        int[][] marh = new int[7][]; 
        marh[1] = new int[]{1, 5, 6, 9, 12, 13, 14, 15, 17, 20};
        marh[2] = new int[]{2, 8, 10, 11, 15, 17, 19};
        marh[3] = new int[]{3,11,13,15,16};
        marh[4] = new int[]{4,14,16,18,20};
        marh[5] = new int[]{5,17,19};
        marh[6] = new int[]{6,20};

        
        for(int i = 7; i < 19; i++)
        {
            long E  = (long) ((Math.pow(2, -i) - Math.atan(Math.pow(2, -i))) * Math.pow(2, 61));
            String Eb = Long.toBinaryString(E); while(Eb.length() < 64) { Eb = '0' + Eb; }
            String I = Integer.toBinaryString(i); while(I.length() < 5) { I = '0' + I; }
            System.out.println("5'd"+i+": errorh = 64'b"+Eb+";");        }
        
        Random  r = new Random();
        
        double[] x = new double[2];
        double[] y = new double[2];
        double[] z = new double[2];
        
       z[0] = Math.log(2);
       //0.36939505782351084;//1.03822307498423;//9.157560027571627E-4;
       z[1] = z[0]; 
       
        String zb = Long.toBinaryString((long) (z[0] * Math.pow(2, 61)));
        while(zb.length() < 63) { zb = '0' + zb; }
        System.out.println(zb);
        String zl = zb.substring(0, 20);
        String z2 = zb.substring(20);
        
        int s = zb.charAt(0) == '0' ? 1 : -1;
        
        double k = scaleFactor(marc, false);
        System.out.println(1/k);
        
        x[0] = 1/k; y[0] = 0; double error = 0; boolean t = false;
        
        for(int i = 1; i < 55; i++)
        {   
            if(i < 7)
            {
             error = (error - (s * (Math.pow(2, -i) - error(marh[i], true))));
             z[1] = z[1] - (s * Math.pow(2, -i));
             
             for(int j = 0; j < marh[i].length; j++)
             {
               x[1] = x[0] + ((j == 0 ? s : -s) * y[0] * Math.pow(2, -marh[i][j]));
               y[1] = y[0] + ((j== 0 ? s : -s) * x[0] * Math.pow(2, -marh[i][j]));
               
               x[0] = x[1]; y[0] = y[1];
             }
            }
            else
            {
               if(i < 18 && !t)
               {
                   error = (error - (s * (Math.pow(2, -i) - atanh(Math.pow(2, -i)))));
                   z[1] = z[1] - (s * Math.pow(2, -i));
               }
               
               if(i == 28)
               {
                 x[1] = x[0] + (s * y[0] * Math.pow(2, -i));
                 y[1] = y[0] + (s * x[0] * Math.pow(2, -i));
               }
               else if(i > 28)
               {
                  x[1] = x[1] + (s * y[0] * Math.pow(2, -i));
                  y[1] = y[1] + (s * x[0] * Math.pow(2, -i)); 
               }
               else
               {
                  x[1] = x[0] + (s * y[0] * Math.pow(2, -i));
                  y[1] = y[0] + (s * x[0] * Math.pow(2, -i));
                  x[0] = x[1]; y[0] = y[1];
               }
            }
            
            if(i == 17 && !t)
            {
              double zt = (Long.parseLong(z2, 2) * Math.pow(2, -58)) - error;  
              z[1] = z[1] - error;
              System.out.println(error);
              System.out.println(z[1] + ", " + z[1]);
              zb = Long.toBinaryString((long) (z[1] * Math.pow(2, 61)));
              while(zb.length() < 63) { zb = '0' + zb; }
             if(zb.length() == 64) { zb = zb.substring(1); }
              System.out.println(zb);
              t = true; //i = i - 1;
              //z[1] = z[1] + error;
            }
            
            if(t && i > 17)
            {
                z[1] = z[1] - (s * Math.pow(2, -i));
                System.out.println(z[1]);
            }
            
               
            s = zb.charAt(i+1) == '0' ? -1 : 1; 
        }
        
        System.out.println("z: " + z[0]);
        System.out.println("x: " + x[1] + ", " + Math.cosh(z[0]));
        System.out.println("y: "+ y[1] + ", " + Math.sinh(z[0]));
        
    }
}
