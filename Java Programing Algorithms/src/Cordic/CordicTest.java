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
public class CordicTest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
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
        
        double[] X = new double[2]; 
        double[] Y = new double[2]; 
        double[] z = new double[2];
        X[0] = X[0]; Y[0] = Y[0];
        
        Random r = new Random();

       z[0] = Math.PI/6;//r.nextDouble() * Math.PI * 0.5;
       //0.36939505782351084;//1.03822307498423;//9.157560027571627E-4;
       
       int Q = (int) (2 * z[0]/Math.PI); System.out.println(Q);
       
       z[1] = ((2 * z[0]/Math.PI) - Q) * Math.PI/2;
               
       if(Q%4 == 1)
        {
            X[0] = -0;
            Y[0] = 0.600990630520388;
        }   
        else if(Q%4 == 2)
        {
            X[0] = -0.600990630520388;
            Y[0] = -0;
        }
        else if(Q%4 == 3)
        {
            X[0] = 0;
            Y[0] = -0.600990630520388;
        }
        else
        {
           X[0] = 0.600990630520388;
           Y[0] = 0;
        }
       System.out.println(X[0]);
       System.out.println(Y[0]);
       
       String zb = Long.toBinaryString((long) (z[1] * Math.pow(2, 61)));
        
        while(zb.length() < 63) { zb = '0' + zb; }
        String zb1 = zb;
       int s = zb.charAt(0) == '0' ? 1 : -1; 
       System.out.println(zb);
       
       for(int i = 0; i < 7; i++)
       {
         X[1] = X[0] - (s*Y[0]*Math.pow(2, -marc[i][0]));
         Y[1] = Y[0] + (s*X[0]*Math.pow(2, -marc[i][0]));
         X[0] = X[1]; Y[0] = Y[1];
           
           for(int j = 1; j < marc[i].length; j++)
           {
               X[1] = X[0] - (s*Y[0]*Math.pow(2, -marc[i][j]));
               Y[1] = Y[0] + (s*X[0]*Math.pow(2, -marc[i][j]));
               X[0] = X[1]; Y[0] = Y[1];
           }
           
           z[1] = z[1] - (s * error(marc[i], false));
           
           if(z[1] < 0)
        { 
            zb = invertNum(Long.toBinaryString((long) (-z[1] * Math.pow(2, 61))));
            String lz = zb.indexOf('1') < 0 ? zb.substring(0, zb.length() - 1) : zb.substring(0, zb.indexOf('1')); 
            zb = Long.toBinaryString(Long.valueOf(zb, 2) + 1); 
            zb = lz + zb; while(zb.length() < 63) { zb = '1' + zb; } 
        }
        else
        {
           zb = Long.toBinaryString((long) (z[1] * Math.pow(2, 61)));
           while(zb.length() < 63) { zb = '0' + zb; }
        }
         System.out.println(zb);
         
           //s = z[1] > 0 ? 1 : -1;          
           s = zb1.charAt(i+1) == '0' ? -1 : 1;   
       }
              
       for(int i = 7; i < 19; i++)
       {
         X[1] = X[0] - (s*Y[0]*Math.pow(2, -i));
         Y[1] = Y[0] + (s*X[0]*Math.pow(2, -i));
         X[0] = X[1]; Y[0] = Y[1];
         
         z[1] = z[1] - (s * Math.atan(Math.pow(2, -i)));
         
         if(z[1] < 0)
        { 
            zb = invertNum(Long.toBinaryString((long) (-z[1] * Math.pow(2, 61))));
            String lz = zb.indexOf('1') < 0 ? zb.substring(0, zb.length() - 1) : zb.substring(0, zb.indexOf('1')); 
            zb = Long.toBinaryString(Long.valueOf(zb, 2) + 1); 
            zb = lz + zb; while(zb.length() < 63) { zb = '1' + zb; } 
        }
        else
        {
           zb = Long.toBinaryString((long) (z[1] * Math.pow(2, 61)));
           while(zb.length() < 63) { zb = '0' + zb; }
        }
         System.out.println(zb);
         
         s = zb1.charAt(i+1) == '0' ? -1 : 1;
         // s = z[1] >= 0 ? 1 : -1;
       }
       
       s = zb.charAt(19) == '0' ? 1 : -1;
       
       for(int i = 19; i < 27; i++)
       {
         X[1] = X[0] - (s*Y[0]*Math.pow(2, -i));
         Y[1] = Y[0] + (s*X[0]*Math.pow(2, -i));
         X[0] = X[1]; Y[0] = Y[1];
         
         z[1] = z[1] - (s * Math.pow(2, -i));
         
         s = zb.charAt(i+1) == '0' ? -1 : 1;;
         //s = z[1] >= 0 ? 1 : -1;
       }
       
       for(int i = 27; i < 55; i++)
       {  
         X[1] = X[1] - (s*Y[0]*Math.pow(2, -i));
         Y[1] = Y[1] + (s*X[0]*Math.pow(2, -i));
         
         z[1] = z[1] - (s * Math.pow(2, -i));
         
         s = zb.charAt(i+1) == '0' ? -1 : 1;
       }
       
       X[0] = X[1]; Y[0] = Y[1];
       System.out.println("z: "+z[1]);
       System.out.println("z: "+z[0]);
       System.out.println(X[0]);
       System.out.println(Math.cos(z[0]));
       System.out.println(Math.cos(z[0]) - ((X[0])));
       System.out.println(Y[0]);
       System.out.println(Math.sin(z[0]));
       System.out.println(Math.sin(z[0]) - ((Y[0])));
    }
    
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
}
