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
public class Low_latency_Cordic {
    
    public static double Booth3Sel(double x, String BS, int i)
    {
        int op = Character.digit(BS.charAt(0), 2) + 
                Character.digit(BS.charAt(1), 2) + 
                (Character.digit(BS.charAt(2), 2) << 1) - 
                (Character.digit(BS.charAt(3), 2) << 2);
          
  double Sel;
        
  switch(op) {
      case  1: Sel = x  * Math.pow(2, -i); break;
      case  2: Sel = x * Math.pow(2, 1-i); break;
      case  3: Sel = 3 * x * Math.pow(2, -i); break;
      case  4: Sel = x * Math.pow(2, 2-i); break;
      case -1: Sel = -(x * Math.pow(2, -i)); break;
      case -2: Sel = -(x * Math.pow(2, 1-i)); break;
      case -3: Sel = -(3 * x * Math.pow(2, -i)); break;
      case -4: Sel = -(x * Math.pow(2, 2-i)); break;
      default: Sel = 0;
  }
  
    return Sel; 
    }
    
    public static String reversestring(String s)
    {
        String r = "";
        
        for(int i = s.length() - 1; i >=0; i--)
        {
            r = r + s.charAt(i);
        }
        
        return r;
    }
    
    public static void main(String[] args)
    {
        Random r = new Random();
        
        double[] X = new double[2];
        double[] Y = new double[2];
        int[] Z = new int[2];

        X[0] = 95.31848996383394;//r.nextDouble() * 100;//1/1.646760258121065;//1/0.7171848151519904;
        Y[0] = 0;//r.nextDouble() * 100;
        Z[0] = 564378486;//81.5253351224385;//r.nextDouble() * Math.log(2);
        
        
        String mz = Integer.toBinaryString(Z[0]); while(mz.length() < 32) { mz = '0' + mz; }
        
        X[0] = X[0] * Math.pow(2, mz.length() - 1); 
        
        double x = Booth3Sel(X[0], reversestring(mz.substring(0, 3)) + '0', 0);
        
        Y[0] = Y[0] + x;
        
        for(int i = 3; i < 30; i = i+3)
        {
            x = Booth3Sel(X[0], reversestring(mz.substring(i - 1, i + 3)), i);
            
                Y[0] = Y[0] + x;
        }
        
       Y[0] = Y[0] + Booth3Sel(X[0], '0' + reversestring(mz.substring(29, 32)), 0);
        
        
        System.out.println("M: " + Y[0]);
    
    }
}
