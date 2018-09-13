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
public class P_Cordic {

    /**
     * @param args the command line arguments
     */
    
    private static double[] thetaRef = new double[512];
    private static double[] Error = new double[512];
    private static int n = 0;
        
    private static double scaleFactor(boolean b)  
    {
        double k = 1.0;
        
        for(int i = b ? 1 : 0; i < 28; i++)
        {
            if(b)
            {
                k = k * Math.sqrt(1-(Math.pow(2, (2 * -i))));
                
                if(i == 4 || i == 13)
                {
                    k = k * Math.sqrt(1-(Math.pow(2, (2 * -i)))); 
                }
            }
            else
            {
                k = k * Math.sqrt(1+(Math.pow(2, (2 * -i))));
            }
        }
            
       return k; 
    }
    
    public static double atanh(double a) 
    {  
       return 0.5 * Math.log((1 + a) / (1 - a));
    }
    
    public static String resize(String s, int length) {
        while (s.length() < length) {
            s = '0' + s;
        }
        return s;
    }
    
    public static void thetaRef(String s)
    {
        if(s.length() == 9)
        {
            boolean t = false;
            double x = Integer.valueOf(s, 2) * Math.pow(2, -8);
            thetaRef[n] = x;
            //System.out.println("9'b" + resize(Integer.toBinaryString(n), 9) + ":  thetaRef = " + 
              //      resize(Long.toBinaryString((long) (x * Math.pow(2, 52))), 64) + ";");
            x = x - Math.atan(1);
            for(int i = 1; i < 10; i++)
            {
                if(x > 0)
                {
                   x = x - Math.atan(Math.pow(2, -i));
                   Error[n] = Error[n] + (Math.pow(2, -i) - Math.atan(Math.pow(2, -i)));
                }
                else
                {
                    x = x + Math.atan(Math.pow(2, -i));
                }
                
                if(i == 4 && !t)
                {
                    i--; t = true;
                }
            }
            
            System.out.println("9'b" + resize(Integer.toBinaryString(n), 9) + ":  error = " + 
                    resize(Long.toBinaryString((long) (Error[n] * Math.pow(2, 61))), 64) + ";");
                    
            n++;
        }
        
        else
        {
               for(int j = 0; j < 2; j++)
               {
                  thetaRef(s + j);   
               }
        }
    }

    public static void main(String[] args) {
        // TODO code application logic here
        Random  r = new Random();
        thetaRef("");
        double[] x = new double[2];
        double[] y = new double[2];
        double[] z = new double[2];
        
       z[0] = 1.4147224558987839;//r.nextDouble() * Math.PI/2 * Math.pow(10, -4);
       //0.36939505782351084;//1.03822307498423;//9.157560027571627E-4;
       z[1] = z[0]; 
       long Z = (long) (z[0] * Math.pow(2, 61));
        String zb = Long.toBinaryString(Z);
        while(zb.length() < 63) { zb = '0' + zb; }
        System.out.println(zb);
        String zl = zb.substring(0, 20);
        String z2 = zb.substring(20);
        
        double k = scaleFactor(true);
        System.out.println(1/k);
        
        x[0] = 1/k; y[0] = 0; double error = 0; boolean t = false;
        
        double c1 = 2.0; 
        
        for(int i = 0; i < 55; i++)
        {
            c1 = c1 - (Math.pow(2, -i) - Math.atan(Math.pow(2, -i)));
            
        }
        
        long d = (Z/2) + (long) (((c1/2) + (1 - Math.atan(1))) * Math.pow(2, 61)); 
         System.out.println("c1: " + Long.toBinaryString((long) (((c1/2) + 1 - Math.atan(1)) * Math.pow(2, 61))));
        double dr = 0;
        double X = 0; //2.483521181241566;
        z[1] = z[1] - Math.atan(1);
        for(int i = 1; i < 19; i++)
        { 
           if(z[1] > 0)
           {
            z[1] = z[1] - Math.atan(Math.pow(2, -i));
            dr = dr + (Math.pow(2, -i) - Math.atan(Math.pow(2, -i)));
            if(i > 8)
            {
             X = X + (Math.pow(2, -3*(i - 9)));   
            }
           }
           else
           {
            z[1] = z[1] + Math.atan(Math.pow(2, -i));
           }
           
           //System.out.println(z[1]);
        }
        X = (X * 2.483532549983162E-9) + Error[Integer.valueOf(zb.substring(0, 10), 2)];
        System.out.println("thetaRef: " + thetaRef[Integer.valueOf(zb.substring(0, 10), 2)]);
        System.out.println("sigma: " + Error[Integer.valueOf(zb.substring(0, 10), 2)]);
        System.out.println("X: " + X);
        System.out.println("dr: " + dr);
        d = d + (long) (X * Math.pow(2, 61));
        String D = Long.toBinaryString(d); while(D.length() < 63) D = '0' + D;
         
        System.out.println(D);
        z[1] = z[0]; 
        for(int i = 0, j = 1; j < 55; j++, i++)
        {
            int s = D.charAt(j) == '1' ? 1 : -1;
            
            x[1] = x[0] - (s * y[0] * Math.pow(2, -i));
            y[1] = y[0] + (s * x[0] * Math.pow(2, -i));
            z[1] = z[1] - (s * Math.atan(Math.pow(2, -i)));
             System.out.println(z[1]);       
            x[0] = x[1]; y[0] = y[1];
        }
        
        System.out.println(z[0]);
        System.out.println(x[0]);
        System.out.println(y[0]);
    }
}
