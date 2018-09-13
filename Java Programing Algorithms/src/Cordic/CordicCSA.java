/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Cordic;

import java.util.ArrayList;

/**
 *
 * @author Aljarhi
 */
public class CordicCSA {

    public static int j = 0;
    public static int[] S = new int[6];
    
    public static double[] cordiceqs(int i, boolean t, int[] s, int k)
    {
        double[] f = new double[2];
        
        if(i == k)
        {
            if(t)
            {
              f[0] = 1.0;
              f[1] = -1.0 * s[i-k];
            }
            else
            {
              f[0] = 1.0;
              f[1] = 1.0 * s[i-k];
            }
                
            return f;
        }
        
        if(t)
        {
            double [] f1, f2;

             f1 = cordiceqs(i-1, true, s, k);
             f2 = cordiceqs(i-1, false, s, k);
             
             f2[0] = - Math.pow(2,-i) * f2[0] * s[i-k];
             f2[1] = - Math.pow(2,-i) * f2[1] * s[i-k];
             
             f[1] = f1[0] + f2[1];
             f[0] = f1[1] + f2[0];
             
             return f;
        }
        
        else
        {
             double [] f1, f2;

             f1 = cordiceqs(i-1, false, s, k);
             f2 = cordiceqs(i-1, true, s, k);
             
             f2[0] = Math.pow(2,-i) * f2[0] * s[i-k];
             f2[1] = Math.pow(2,-i) * f2[1] * s[i-k];
             
             f[0] = f1[1] + f2[0];
             f[1] = f1[0] + f2[1];
             
             return f;
        }
    }
    
    public static void Print(int [] s)
    {
        String S = "";
        
        for(int i = 0; i < s.length; i++)
        {
            S = S + (s[i] == 1 ? "1" : "0");
        }
        System.out.println("6'b"+S + ": begin");
    }
    
    public static String toS(double f)
    {
        String s = "";
        
        int a = (int) (f * Math.pow(2, 31));
        
        s = Integer.toBinaryString(a); while(s.length() < 33) { s = ( f < 0 ? '1' : '0') + s; } 
       
        
        return "33'b"+s;
    }
    
    public static void RD(int i)
    {
        if(i == 6)
        {
          double[] f = cordiceqs(5, true, S, 0);
          Print(S);
          System.out.println("x1: ((" + (f[0]) + " * xi) >>> 32) + ((" + (f[1]) + " * yi) >>> 32);");
          System.out.println("y1: ((" + (f[0]) + " * yi) >>> 32) - ((" + (f[1]) + " * xi) >>> 32);");
          System.out.println("end");
        }
        else
        {
      S[i] = 1;
      i++; RD(i); i--;
      S[i] = -1;
      i++; RD(i);
        }
    }
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        for(int i = 1; i < 55-28; i++)
        {
            System.out.println(
                    
                    "x2["+(i)+"] <= m == 1 ? (z[28]["+(i+35)+"] == 1 ? -x["+(28)+"] >>> "+(i+27)+" : x["+(28)+"] >>> "+(i+27)+") :" +
                    " m == 2 ? (z[28]["+(i+35)+"] == 1 ? x["+(28)+"] >>> "+(i+27)+" : -x["+(28)+"] >>> "+(i+27)+") : x["+(28)+"]" + ";\r\n" + 
                    "y2["+(i)+"] <= z[28]["+(i+35)+"] == 1 ? y["+(28)+"] >>> "+(i+27)+" : -y["+(28)+"] >>> "+(i+27)+";"                    
                    + "\r\n"
           
                    );
        }
        
    }
}
