/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cordic;

import java.util.Random;

/**
 *
 * @author Muaz Aljarhi
 */
public class Radix10 
{
    public static boolean[] convertDToB(int d)
    {
        boolean[] S = new boolean[4];
        
        switch (d) {
            case 0:
                S[0] = false;
                S[1] = false;
                S[2] = false;
                S[3] = false;
                break;
            case 1:
                 S[0] = false;
                 S[1] = false;
                 S[2] = false;
                 S[3] = true;
                break;
            case 2:
                 S[0] = false;
                 S[1] = false;
                 S[2] = true;
                 S[3] = false;
                break;
            case 3:
                 S[0] = false;
                 S[1] = false;
                 S[2] = true;
                 S[3] = true;
                break;
            case 4:
                 S[0] = false;
                 S[1] = true;
                 S[2] = false;
                 S[3] = false;
                break;
            case 5:
                 S[0] = false;
                 S[1] = true;
                 S[2] = false;
                 S[3] = true;
                break;
            case 6:
                 S[0] = false;
                 S[1] = true;
                 S[2] = true;
                 S[3] = false;
                break;
            case 7:
                 S[0] = false;
                 S[1] = true;
                 S[2] = true;
                 S[3] = true;
                break;
            case 8:
                 S[0] = true;
                 S[1] = false;
                 S[2] = false;
                 S[3] = false;
                break;
            default:
                 S[0] = true;
                 S[1] = false;
                 S[2] = false;
                 S[3] = true;
                break;
        }
        
      return S;
    }
    
    public static boolean[] Addd(boolean[] a, boolean[] b)
    {
        boolean[] sum = new boolean[5]; 
        boolean c = false;
        
        for(int i = 3; i >= 0; i--)
        {
            sum[i+1] = a[i] ^ b[i] ^ c;
            c = (a[i] & b[i]) | (c & (a[i] | b[i]));         
        }
        sum[0] = c;
        return sum; 
    }
    
    public static boolean[][] Addn(boolean[][] a, boolean[][] b)
    {
        boolean[][] sum = new boolean[6][4]; 
        boolean c = false;
        
        for(int i = 5; i > 0; i--)
        {
           boolean[] ps = Addd(a[i], b[i]);
           sum[i][3] = ps[4] ^ c;

           //sum[i][2] = (!ps[2] && (ps[0] == true || (ps[1] == true && (ps[2] == true || ps[3] == true)))) || 
             //          (ps[2] && ps[0] == false && (ps[1] == false || (ps[2] == false && ps[3] == false)));
           
           if(ps[0] == true || (ps[1] == true && (ps[2] == true || ps[3] == true)))
           {
               sum[i][2] = !ps[3] ^ (ps[4] && c);
               sum[i][1] = (ps[3] == ps[2]) ^ (!ps[3] && (ps[4] && c));
               sum[i][0] = ps[0] & ps[3];
               c = true;
           }
           else
           {
              sum[i][2] = ps[3] ^ (ps[4] && c);
              sum[i][1] = ps[2] ^ (ps[3] && (ps[4] && c));
              sum[i][0] = ps[1];
              c = false;
           }
           
        }
        sum[0][3] = c;
        sum[0][2] = false;
        sum[0][1] = false;
        sum[0][0] = false;        
        
        return sum; 
    }
    
    public static int convertBTD(boolean[] b)
    {
        int k = 0;
            
            for(int j = 3; j >=0; --j)
            {
                k = k + (b[j] == true ? (int) Math.pow(2, 3-j) : 0);
            }
            
            return k;
    }
    
    public static int[] convertBSTDS(boolean[][] bs)
    {
        int[] ds = new int[6];
        
        for(int i = 0; i < 6; ++i)
        {
            int k = 0;
            
            for(int j = 3; j >=0; --j)
            {
                k = k + (bs[i][j] == true ? (int) Math.pow(2, 3-j) : 0);
            }
            ds[i] = k;
        }
        
        return ds;
    }
    public static int convertDSToI(int[] ds)
    {
        int a = 0;
        for(int i = 5; i >= 0; i--)
        {
            a = a + ((int) Math.pow(10, 5-i)) * ds[i];
        }
        return a; 
    }
    
    public static boolean[][] shiftBy2(boolean[][] a)
    {
        boolean[][] sa = new boolean[6][4];
        
        boolean b = false, d = false;
        
        for(int i = 0; i < 6; i++)
        {
            boolean b1 = sa[i][3], d1 = sa[i][2];
            sa[i][3] = b; sa[i][2] = d;
            b = sa[i][1]; d = sa[i][0];
            sa[i][1] = b1; sa[i][0] = d1;
        }
        for(int i = 0; i < 6; i++)
        {
            
        }
        
        return sa;
    }
    
    
    public static void main(String[] Args)
    {
        Random R1 = new Random(System.currentTimeMillis());
        Random R2 = new Random();
        int[] a  = new int[6]; a[0] = 0;
        int[] b = new int[6]; b[0] = 0;
        
        for(int i = 1; i < 6; i++)
        {
          a[i] = R1.nextInt(10);
          b[i] = R2.nextInt(10);
        }
        
        a = new int[]{0, 6, 8, 9, 3, 9};
        b = new int[]{0, 3, 3, 8, 8, 7};
        
        boolean[][] a1 = new boolean[6][4], b1 = new boolean[6][4];
        
        for(int i = 0; i < 6; i++)
        {
            a1[i] = convertDToB(a[i]);
            b1[i] = convertDToB(b[i]);
        }
        
        int[] sum = convertBSTDS(Addn(a1, b1));
        
        System.out.println("a: "+a[1]+", "+a[2]+", "+a[3]+", "+a[4]+", "+a[5]);
        System.out.println("b: "+b[1]+", "+b[2]+", "+b[3]+", "+b[4]+", "+b[5]);
        System.out.println("True a: "+convertDSToI(a));
        System.out.println("True b: "+convertDSToI(b));
        System.out.println("psum: "+sum[0]+", "+sum[1]+", "+sum[2]+", "+sum[3]+", "+sum[4]+", "+sum[5]);
        System.out.println("sum: "+convertDSToI(sum));
        System.out.println("True sum: "+(convertDSToI(a)+convertDSToI(b)));   
    }
}
