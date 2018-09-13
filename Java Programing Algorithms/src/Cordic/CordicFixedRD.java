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
public class CordicFixedRD {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        Random r = new Random();
        
        double[] X = new double[2];
        double[] Y = new double[2];
        long[] Z = new long[2];
        
        X[0] = 1; Y[0] = 0; Z[0] = (long) (Math.PI * 0.5 * r.nextDouble() * Math.pow(10, 18));
        
        System.out.println("X: " + X[0] + ", Y: " + Y[0] + ", Z: " + (Z[0] * Math.pow(10, -18)));
        
        for(int i = 0; i < 17; i = i + 1)
        {
            long zt = Z[0]; int s = 0; Z[1] = Z[0];
        
        while(Math.abs(zt) <= Math.abs(Z[1]))
        {
            s++; Z[1] = zt; zt = Math.abs(Z[0]);  
            
            zt = zt - (long) (Math.atan(s * Math.pow(10, -i)) * Math.pow(10, 18));
        }
        
        s = Z[0] < 0 ? -s+1 : s-1;
        
        System.out.println("s: " + s);
            
            double k = i < 9 ? 1/(Math.sqrt(1+(Math.pow(s, 2) * Math.pow(10, (2 * -i))))) : 1;
          
          X[1] = k * (X[0] - (Y[0] * s * Math.pow(10, -i)));
          Y[1] = k * (Y[0] + (X[0] * s * Math.pow(10, -i)));
          
          X[0] = X[1]; Y[0] = Y[1];
         
          Z[0] = Z[0] - (long) (Math.atan(s * Math.pow(10, -i)) * Math.pow(10, 18));
          
        }
        
        System.out.println("Angle Z: " + (Z[0] * Math.pow(10, -18)));
        
        System.out.println(X[0] + ", " + Y[0]);
    }
}
