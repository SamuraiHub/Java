/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Cordic;

import static Cordic.Para_Cordic.atanh;
import static Cordic.Para_Cordic.error;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Random;

/**
 *
 * @author Aljarhi
 */
public class Radix4Cordic {
    
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
    
    public static int sel(String s, int i, int m)
    {
        if(i == 0 && m == 1 || i == 2 && m == -1)
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
    
    
    public static int Sel(int y, int p1, int p2)
    {
        if(y > p2)
            return -2;
        else if((y > p1) && (y <= p2))
            return -1;
        else if((y > -p1) && (y <= p1))
            return 0;
        else if((y > -p2) && (y <= -p1))
            return 1;
        else
            return 2;      
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
    
    public static int leadZero(long x, int len) {
        String b = Long.toBinaryString(x);
        int O = 0;
        if(b.charAt(0) == '-') { b = b.substring(1); }
        int l = b.length();
        if (l < len) {
            for (int i = 0; i < len - l; i++) {
                b = '0' + b;
            }
        }

        for (int j = 0; j < b.length() && b.charAt(j) != '1'; j++) {
            O = O + 1;
        }

        return O;
    }
    
    public static String resize(String s, int length) {
        while (s.length() < length) {
            s = '0' + s;
        }
        return s;
    }
    
    public static void Arctan_Table() 
    {
        for (int i = 1; i < 28; i++) 
        {
            //System.out.println(i);
            long p2 = (long) Math.pow(4, i);
            String s; 
            double b = atanh(Math.pow(4, -i));

            BigInteger a = 
                    BigDecimal.valueOf(b).multiply(BigDecimal.valueOf(2).pow(61)).toBigInteger();
            s = "5'b" + resize(Integer.toBinaryString(i), 5) + ": arctan = 64'b" + 
                    resize(a.toString(2), 64) + ";" + " //  1/" + p2;
            
            //if((i - 1) % 3 == 0)
              //  s = s + "\r\n6'b" + resize(Integer.toBinaryString(++j), 6) + ": S = 6'd" + i + ";";
            
           System.out.println(s);
        }
    }
    
    public static double atanh(double a) 
    {  
       return 0.5 * Math.log((1 + a) / (1 - a));
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
        
        Random r = new Random();
        
        long[] X = new long[2];
        long[] Y = new long[2];
        long[] Z = new long[2];
        //long a = Long.valueOf("6707442435666264"); 
        X[0] =  BigInteger.valueOf(2).pow(61).longValueExact();//r.nextDouble();//(1.4893514056849053)/1;//0.02273417880433881 * 2;///0.8557464949571278;
        Y[0] = 0;//(1.4037900766332436)/1;//0.8557464558571278;//0.25616183074669985;   
        //Z[1] = (long) (1.9925058010981933E-17 * Math.pow(2, 61));
        
        //while(Math.abs(Z[1] *  Math.pow(2, -61)) < 1.9925058010981933E-7)
        //{
        //Z[0] = (long) (1.184437451329622 * Math.pow(2, 61));//(long) (Math.PI * r.nextDouble() * 0.5 * Math.pow(2, 61));
        //Z[1] = Z[0];
        
        String mz;
        String mz1;
        
        /*while(X[0] < Y[0])
        {
           X[0] = r.nextDouble();
           Y[0] = r.nextDouble();
        } */
        double a = r.nextDouble();
        Z[0] = (long)(a *  Math.pow(2, 61));//(long) (D *  Math.pow(2, 61));
        int Q = (int) (a * (2/(Math.PI)));
        double R = (a * (2/Math.PI)) - ((int) (a * (2/Math.PI)));
        double D = R * Math.PI/2;
        System.out.println(Math.sin(D));
        Z[1] = (long)(D *  Math.pow(2, 61));
        
        switch(Q%4)
        {
            case 1:
                X[1] = -Y[0]; Y[1] = X[0];
            case 2:
                X[1] = -X[0]; Y[1] = -Y[0];
            case 3:
                X[1] = Y[0]; Y[1] = -X[0];
            default:
                X[1] = X[0]; Y[1] = Y[0];
        }
        X[0] = X[1]; Y[0] = Y[1];
        // System.out.println("Q: " + Q + " Z: " + (Z[0] *  Math.pow(2, -61)));
        
       //  X[1] = (X[0] + Y[0]) + ((X[0] - Y[0]) * Math.pow(2, - 2 * Q));
         
       //  Y[1] = (X[0] + Y[0]) - ((X[0] - Y[0]) * Math.pow(2, - 2 * Q));
         
       // X[0] = X[1]; Y[0] = Y[1];
        
        //System.out.println("sqrt: " + (Math.sqrt(Math.pow(X[0], 2) - Math.pow(Y[0], 2))));
        
        System.out.println("X: " + X[0]);//((X[0] - Y[0])  * Math.pow(2, 52)));
        
        System.out.println("Z: " + Z[0]);//((Y[0] + X[0])) * Math.pow(2, 52));
        
        int Enew = 0;
        
        long x = (long) (X[0] * Math.pow(2, 61)), y = (long) (Y[0] * Math.pow(2, 61));
        
        /*if(X[0] < (2 * Y[0]))
        {
            int L = leadZero(x - y, Long.toBinaryString((long) (X[0] * Math.pow(2, 61))).length());
            Enew = L == 1 ? 1 : (L - 1);
            System.out.println("Enew: " + Enew);
            if(Enew % 2 == 1) 
            { 
               //X[0] = X[0] / 2; 
               //Y[0] = Y[0] / 2; 
                
                X[1] = (X[0] + Y[0]) + ((X[0] - Y[0]) * Math.pow(2, (Enew + 0)));
                Y[0] = (X[0] + Y[0]) - ((X[0] - Y[0]) * Math.pow(2, (Enew + 0)));
                X[0] = X[1];
              //  System.out.println(X[0]);
            } 
                    
            else
            {
               X[1] = (X[0] + Y[0]) + ((X[0] - Y[0]) * Math.pow(2, Enew));
               Y[0] = (X[0] + Y[0]) - ((X[0] - Y[0]) * Math.pow(2, Enew));
               X[0] = X[1];
            ///}
            //x[0] = x[0].shiftRight(((int)Enew / 2) + 1);
            //y[0] = y[0].shiftRight(((int)Enew / 2) + 1);
            }
        }
        else
        {
             Enew = 0;
        }*/
        
        
        //System.out.println("X: " + ((X[0] + Y[0]) ));
        
        //System.out.println("Y: " + ((X[0] - Y[0])) * Math.pow(2, 48));
        
        
        int p1 = 0, p2 = 0;
        
          if(Z[1] < 0)
          { 
            mz = invertNum(Long.toBinaryString((-Z[1])));
            String lz = mz.indexOf('1') < 0 ? mz.substring(0, mz.length() - 1) : mz.substring(0, mz.indexOf('1')); 
            mz = Long.toBinaryString(Long.valueOf(mz, 2) + 1); 
            mz = lz + mz; while(mz.length() < 63) { mz = '1' + mz; }
          }
          
          else
          {
            mz = Long.toBinaryString((long) Z[1]); while(mz.length() < 63) { mz = '0' + mz; }
          }
          
          if(X[0] < 0)
          { 
            mz1 = invertNum(Long.toBinaryString((long) (-X[0] * Math.pow(2, 61))));
            String lz = mz1.indexOf('1') < 0 ? mz1.substring(0, mz1.length() - 1) : mz1.substring(0, mz1.indexOf('1')); 
            mz1 = Long.toBinaryString(Long.valueOf(mz1, 2) + 1); 
            mz1 = lz + mz1; while(mz1.length() < 63) { mz1 = '1' + mz1; }
          }
          
          else
          {
            mz1 = Long.toBinaryString((long) (X[0] * Math.pow(2, 61))); while(mz1.length() < 63) { mz1 = '0' + mz1; }
            p1 = Integer.parseInt(mz1.substring(0, 8), 2) / 2;
            p2 = Integer.parseInt(mz1.substring(0, 8), 2) * 3 / 2;
             //System.out.println("p1: " + p1);
             //System.out.println("p2: " + p2);
          }
        
        double tan = (Y[0]/X[0]);
       /* int s =  sel("0" + mz.substring(0, 2)); System.out.println(s); 
        double k = 1/(Math.sqrt(1+(Math.pow(s, 2) * Math.pow(4, (0)))));
          
          X[1] = k * (X[0] - (Y[0] * s * Math.pow(4, (0))));
          Y[1] = k * (Y[0] + (X[0] * s * Math.pow(4, (0))));
          
          X[0] = X[1]; Y[0] = Y[1];
          
          System.out.println("Angle Z: " + (Z[0] * Math.pow(2, -61)));
          
          Z[0] = Z[0] - (long) (Math.atan(s * Math.pow(4, (0))) * Math.pow(2, 61));
          
          if(Z[0] < 0)
          {
            mz = invertNum(Long.toBinaryString(-Z[0]));
            Z[1] = Long.valueOf(mz, 2) + 1;
            mz = Long.toBinaryString(Z[1]); while(mz.length() < 63) { mz = '1' + mz; }
          }
          
          else
          {
            mz = Long.toBinaryString(Z[0]); while(mz.length() < 63) { mz = '0' + mz; }  
          } */
        
        int n = 0; double k1 = 1.0; long k2 = BigInteger.valueOf(2).pow(61).longValueExact(); long error = 0;
        
        for(int i = 0; i < 56; i = i + 2)
        { 
            
          System.out.println(mz);
          //System.out.println(mz1);
          
          /*if(Y[0] < 0)
          {
            mz1 = invertNum(Long.toBinaryString((long) (-Y[0] * Math.pow(2, 53))));
            //System.out.println(mz);
            String lz = mz1.indexOf('1') < 0 ? mz.substring(0, mz1.length() - 1) : mz.substring(0, mz1.indexOf('1'));
            int m1 = mz1.indexOf('1') - 1;
            mz1 = Long.toBinaryString(Long.valueOf(mz1, 2) + 1);
            if( m1 >= 0 && mz1.charAt(m1) == '1')
                lz = lz.substring(0, lz.length() - 1);
                
            mz1 = lz + mz1; while(mz1.length() < 63) { mz1 = '1' + mz1; }
            System.out.println(mz1);
          }
          else
          {
            System.out.println(resize(Long.toBinaryString((long) (Y[0] * Math.pow(2, 61))), 63));
          }*/
          //System.out.println(i);
          //System.out.println(Z[1] * Math.pow(2, -61));
          //System.out.println(Y[0]);
          
          
          
          /*int s = Y[0] < 0 ? 
                  i == 60 ? Sel(-Integer.valueOf(mz.substring(i-2, i+3) + "00000", 2), p1, p2) :
                  i == 58 ? Sel(-Integer.valueOf(mz.substring(i-2, i+5) + "000", 2), p1, p2) :
                  i == 56 ? Sel(-Integer.valueOf(mz.substring(i-2, i+7) + "0", 2), p1, p2) :
                            Sel(-Integer.valueOf(mz.substring(i-2, i+8), 2), p1, p2) :
                  
                  i == 60 ? Sel(Integer.valueOf(mz.substring(i-2, i+3) + "00000", 2), p1, p2) :
                  i == 58 ? Sel(Integer.valueOf(mz.substring(i-2, i+5) + "000", 2), p1, p2) :
                  i == 56 ? Sel(Integer.valueOf(mz.substring(i-2, i+7) + "0", 2), p1, p2) :
                            Sel(Integer.valueOf(mz.substring(i-2, i+8), 2), p1, p2);*/
                   //Sel(mz.substring(i, i+3), mz1.substring(0, 3));
                  //X[0] < 0 ? sel(mz.substring(i, i+3)) : -sel(mz.substring(i, i+3));
          //System.out.println("u: " + (Y[0] < 0 ? -Integer.valueOf(mz.substring(i, i+8), 2)                                  // : Integer.valueOf(mz.substring(i, i+8), 2)));
          int s = sel(mz.substring(i, i+3), i, 1) * 1;
          
          System.out.println(s);
          
          if(i < 8)
            {
             error = error + ((long) (s * (Math.pow(2, -i) - error(marc[i], false)) * Math.pow(2, 61)));
            
             for(int j = 0; j < marc[i].length; j++)
             {
               X[1] = (BigInteger.valueOf(X[0]).subtract((BigInteger.valueOf(s).multiply(BigInteger.valueOf(Y[0])).shiftRight(marc[i][j])))).longValue();
               Y[1] = (BigInteger.valueOf(Y[0]).add((BigInteger.valueOf(s).multiply(BigInteger.valueOf(X[0])).shiftRight(marc[i][j])))).longValue();
               
               X[0] = X[1]; Y[0] = Y[1];
               
               k2 = (BigDecimal.valueOf(k2).multiply(BigDecimal.valueOf(Math.sqrt(1+(Math.pow(s, 2) * Math.pow(2, (2 * -marc[i][j]))))))).longValue();
             }
            }
          
          if(i >= 8 && i < 28)
              k2 = (BigDecimal.valueOf(k2).multiply(BigDecimal.valueOf(Math.sqrt(1+(Math.pow(s, 2) * Math.pow(2, (2 * -i))))))).longValue();

          if(i == 28)
          {
             X[1] = (BigInteger.valueOf(X[0]).subtract((BigInteger.valueOf(s).multiply(BigInteger.valueOf(Y[0])).shiftRight(i)))).longValue();
             Y[1] = (BigInteger.valueOf(Y[0]).add((BigInteger.valueOf(s).multiply(BigInteger.valueOf(X[0])).shiftRight(i)))).longValue();
             //System.out.println("k1: "+k1);
          }
          else if(i > 28)
          {
             X[1] = (BigInteger.valueOf(X[1]).subtract((BigInteger.valueOf(s).multiply(BigInteger.valueOf(Y[0])).shiftRight(i)))).longValue();
             Y[1] = (BigInteger.valueOf(Y[1]).add((BigInteger.valueOf(s).multiply(BigInteger.valueOf(X[0])).shiftRight(i)))).longValue();  
          }
          else if(i >= 8)
          {
             X[1] = (BigInteger.valueOf(X[0]).subtract((BigInteger.valueOf(s).multiply(BigInteger.valueOf(Y[0])).shiftRight(i)))).longValue();
             Y[1] = (BigInteger.valueOf(Y[0]).add((BigInteger.valueOf(s).multiply(BigInteger.valueOf(X[0])).shiftRight(i)))).longValue();
             X[0] = X[1]; Y[0] = Y[1];
          }
          
          //System.out.println("Z1: " + Z[1]);
          Z[1] = Z[1] - ((long) ((s * Math.pow(2, -i)) * Math.pow(2, 61)));
          //System.out.println("Z1: " + (long) ((s * Math.pow(2, -i)) * Math.pow(2, 62)));
          //System.out.println(resize(Long.toBinaryString((long) (Math.atan(s * Math.pow(2, -i)) * Math.pow(2, 61))), 63));
          
          if(i >= 8 && i < 18)
          {
              error = error + ((long) (s * (Math.pow(2, -i) - Math.atan(Math.pow(2, -i))) * Math.pow(2, 61)));
          }
          
          if(i == 16)
          {
            Z[1] = Z[1] + error;      
          }
          
          if(Z[1] < 0)
          { 
            mz = invertNum(Long.toBinaryString((long) (-Z[1])));
            String lz = mz.indexOf('1') < 0 ? mz.substring(0, mz.length() - 1) : mz.substring(0, mz.indexOf('1')); 
            mz = Long.toBinaryString(Long.valueOf(mz, 2) + 1); 
            mz = lz + mz; while(mz.length() < 63) { mz = '1' + mz; } 
            while(mz.length() < 63) { mz = '0' + mz; }
          }
          
          else
          {
            mz = Long.toBinaryString((long) (Z[1])); while(mz.length() < 63) { mz = '0' + mz; }  
          }
          
          if(X[0] < 0)
          { 
            mz1 = invertNum(Long.toBinaryString((long) (-X[0] * Math.pow(2, 61))));
            String lz = mz1.indexOf('1') < 0 ? mz1.substring(0, mz1.length() - 1) : mz1.substring(0, mz1.indexOf('1')); 
            mz1 = Long.toBinaryString(Long.valueOf(mz1, 2) + 1); 
            mz1 = lz + mz1; while(mz1.length() < 63) { mz1 = '1' + mz1; }
          }
          
          else
          {
            mz1 = Long.toBinaryString((long) (X[0] * Math.pow(2, 61))); while(mz1.length() < 63) { mz1 = '0' + mz1; }
            p1 = Integer.parseInt(mz1.substring(0, 8), 2) / 2;
            p2 = Integer.parseInt(mz1.substring(0, 8), 2) * 3 / 2;
            //System.out.println("p1: " + p1);
            //System.out.println("p2: " + p2);
          }
          
          if(n < 1 && ( i > 1 && ((i == 2) )))
          {
          // n++;
           //i -= 1;
          }
          
          else
          {
             n = 0;
          }
          
        } 
        
        /*s =  sel(String.valueOf(mz.charAt(61)) + mz.charAt(62) + '0');
        k = 1/(Math.sqrt(1+(Math.pow(s, 2) * Math.pow(4, -61))));
          
          X[1] = k * (X[0] - (Y[0] * s * Math.pow(4, -61)));
          Y[1] = k * (Y[0] + (X[0] * s * Math.pow(4, -61)));
          
          X[0] = X[1]; Y[0] = Y[1];
          
          System.out.println("Angle Z: " + (Z[0] * Math.pow(2, -61)));*/
        //System.out.println("Angle Z: " + (Z[0] * Math.pow(2, -61)));
        //System.out.println("tan Z: " + tan);
        //System.out.println("Angle Z: " + (Z[1] * Math.pow(2, -61)));
        //System.out.println("Angle Z: " +  ((Z[1] * Math.pow(2, -61)) + (0.5 * Enew * Math.log(2))));
        //System.out.println("Cos Z: " + (X[0] * Math.pow(2, Q - 1)));
        //System.out.println("Sin Z: " + (Y[0] * Math.pow(2, Q - 1)));
        //System.out.println(Math.sinh(64.2782296917)/4);
        //System.out.println(X[0]/*/(Math.pow(2, (Enew + 2)/2.0))*/);
        //Arctan_Table();
        System.out.println("cos(a): " + Math.cos(a));
        System.out.println("sin(a): " + Math.sin(a));
        System.out.println(Y[0]/(double)k2);
        System.out.println(X[0]/(double)k2);
        //}
        //System.out.println("Angle Z: " + (Z[0] * Math.pow(2, -61)));
        //System.out.println("Angle Z: " + (Z[1] * Math.pow(2, -61)));
        //System.out.println(resize(BigDecimal.valueOf(Math.atan(2.0))
          //    .multiply(BigDecimal.valueOf(2).pow(61)).toBigInteger().toString(2), 64));
    }
}