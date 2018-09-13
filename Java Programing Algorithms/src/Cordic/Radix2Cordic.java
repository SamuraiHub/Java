/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Cordic;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Random;

/**
 *
 * @author Aljarhi
 */
public class Radix2Cordic {

    /**
     * @param args the command line arguments
     */
    
    public static double atanh(double a) 
    {  
       return 0.5 * Math.log((1 + a) / (1 - a));
    }
    
    public static long scaleFactor(boolean m)
    {
        double k = 1; int i = 0; if(m) { i = 1; }
        
        
        
        for(; i <28; i++)
        {
           if(!m)
           {
             k = k/Math.sqrt(1+(Math.pow(2, -2*i)));
           }            
           else
           {
              k = k/Math.sqrt(1-(Math.pow(2, -2*i)));
              if(i == 4 || i == 13)
              {
               k = k/Math.sqrt(1-(Math.pow(2, -2*i)));   
              }
           }
           
        }
        
        System.out.println(k);
        
        return (long) (k*Math.pow(2, 50));
    }
    
    public static String toBinaryString(long bits) {
        String s = "";
        if(bits < 0)
        {
            bits = -bits;
            
           for (int i = 0; i < 63; i++) 
           {
               int j = (int) (bits % 2);
               bits = bits / 2;
               s = j + s;
           }
           
           s = '1' + s;
        }
        
        else
        {
           for (int i = 0; i < 63; i++) 
           {
               int j = (int) (bits % 2);
               bits = bits / 2;
               s = j + s;
           }
           
           s = '0' + s;
        }
        
        return s;
    }
    
    
    public static void main(String[] args) {
        // TODO code application logic here
        
        Random r = new Random();
        
        scaleFactor(true);
        
        double x = 1;
        double y = 0;
        double z = 1.0E-5;
        
        String xb = toBinaryString(Double.doubleToLongBits(x));
        String yb = toBinaryString(Double.doubleToLongBits(y));
        String zb = toBinaryString(Double.doubleToLongBits(z));
        
        
        int ez = Integer.parseInt(zb.substring(1, 12), 2);
        int ex = Integer.parseInt(xb.substring(1, 12), 2);
        int ey = Integer.parseInt(yb.substring(1, 12), 2);
        int Eref = ex >= ey ? ex : ey;
        int Erefx = ex < ey ? ey - ex : 0;
        int Erefy = ex > ey ? ex - ey : 0;
        System.out.println(Eref);
        BigInteger my = BigInteger.valueOf(Long.parseLong((y == 0 ? '0' : '1') + yb.substring(12, 64), 2) >> Erefy);
        BigInteger mx = BigInteger.valueOf(Long.parseLong((x == 0 ? '0' : '1') + xb.substring(12, 64), 2) >> Erefx);
        //my = my.subtract(BigInteger.ONE.shiftLeft(50 - (Eref - 1023)));
        //my = my.signum() == -1 ? my.negate() : my; 
        //mx = mx.add(BigInteger.ONE.shiftLeft(50 - (Eref - 1023)));
        BigInteger mz;
        
        if (ez > 1023) {
            mz = BigInteger.valueOf(Long.parseLong((z == 0 ? '0' : '1') + zb.substring(12, 64), 2) << ez - 1023);
        } else {
            mz = BigInteger.valueOf(Long.parseLong((z == 0 ? '0' : '1') + zb.substring(12, 64), 2) >> 1023 - ez);
        }
        System.out.println(mx.longValue()/(Math.pow(2, 52-(Eref-1023))));
        //mx = (mx.multiply(BigInteger.valueOf(scaleFactor(false)))).shiftRight(50);
        //my = (my.multiply(BigInteger.valueOf(scaleFactor(false)))).shiftRight(50);
        System.out.println(mx.longValue()/(Math.pow(2, 52-(Eref-1023))));
        System.out.println(my.longValue()/(Math.pow(2, 52-(Eref-1023))));
        //System.out.println(mz.longValue()/(Math.pow(2, 51)));
        mz = (mz.multiply(BigInteger.valueOf((long)(1/(Math.log(2)) * Math.pow(2, 54))))).shiftRight(45);
        int Q = (mz.shiftRight(61)).intValue();
        System.out.println(Q);
        zb = resize(mz.toString(2), 61);
        BigInteger R = new BigInteger(zb.substring(zb.length()-61, zb.length()), 2);
        //System.out.println(R.longValue()/Math.pow(2, 55));
        R = (R.multiply(BigInteger.valueOf((long)((Math.log(2)) * Math.pow(2, 54))))).shiftRight(54);
        System.out.println(R.longValue()/Math.pow(2, 61));
        BigInteger X, X1, Y, Y1;
        
        /*if(Q%4 == 1)
        {
            X = my.negate();
            Y = mx;
        }   
        else if(Q%4 == 2)
        {
            X = mx.negate();
            Y = my.negate();
        }
        else if(Q%4 == 3)
        {
            X = my;
            Y = mx.negate();
        }
        else
        {*/
            X1 = mx.subtract(my);
            //System.out.println(X1.longValue()/(Math.pow(2, 52-(Eref - 1023))));
            int Enew = (Long.numberOfLeadingZeros(X1.longValue()) - Long.numberOfLeadingZeros(mx.longValue())) == 1 ? 1 :
                    (Long.numberOfLeadingZeros(X1.longValue()) - Long.numberOfLeadingZeros(mx.longValue())) - 1;
            X1 = X1.shiftRight(2 * Q);
            System.out.println(X1);
            //System.out.println(X1.longValue()/(Math.pow(2, 52-(Eref - 1023))));
            X = mx;// (mx.add(my)).add(X1);
            Y = my;// (my.add(mx)).subtract(X1);
           
       // }
        boolean t = false, t1 = false, t2 = false;

        double k = 1;
        System.out.println(ey);
        int p1 = Integer.parseInt(resize(X.toString(2),53).substring(0, 8), 2) / 2;
        int p2 = Integer.parseInt(resize(X.toString(2),53).substring(0, 8), 2) + 
                (Integer.parseInt(resize(X.toString(2),53).substring(0, 8), 2) / 2);
        BigInteger u = Y;
        int us  = Integer.parseInt(resize(u.toString(2), 53).substring(0, 8), 2); 
        System.out.println(p1 + ", " + p2 +", " + us);
        //int seg =  Sel(us, p1, p2); //R = BigInteger.ZERO; 
        
        //System.out.println(Y.longValue()/Math.pow(2, 52));
        
        String Z = R.toString(2); while(Z.length() < 63) { Z = '0' + Z; }
        System.out.println(Z);
        int seg = sel(Z.substring(1, 4), 2, -1);
         
        for(int i = 0; i < 55; i = i+2)
        {
         System.out.println("R: " + R.longValue()/Math.pow(2, 61) +", X: " + X.shiftRight(15).longValue()/Math.pow(2, 37-(Eref-1023))
                +", Y: " + Y.shiftRight(15).longValue()/Math.pow(2, 37-(Eref-1023)) + ", seg: "+seg +", i:"+i);
         
         k = i < 20 ? k/(Math.sqrt(1+(Math.pow(seg, 2) * Math.pow(2, (2 * -i))))) : 1;
            
          if(seg < 0)  
          {  
             X1 = X.subtract(Y.multiply(BigInteger.valueOf(seg)).shiftRight(i));
             Y1 = Y.add(X.multiply(BigInteger.valueOf(seg)).shiftRight(i));
             X = X1; Y = Y1;
             R = R.subtract(BigDecimal.valueOf(Math.atan(seg * Math.pow(2, -i))).multiply(BigDecimal.valueOf(2).pow(61)).toBigInteger());
          }
          else if(seg > 0)
          {
            X1 = X.subtract(Y.multiply(BigInteger.valueOf(seg)).shiftRight(i));
            Y1 = Y.add(X.multiply(BigInteger.valueOf(seg)).shiftRight(i));
            X = X1; Y = Y1;
            R = R.subtract(BigDecimal.valueOf(Math.atan(seg * Math.pow(2, -i))).multiply(BigDecimal.valueOf(2).pow(61)).toBigInteger());
          }
          
          if(i == 8 || i == 18)
          {
              X = X.multiply(BigInteger.valueOf((long) (k*Math.pow(2, 50)))).shiftRight(50);
              Y = Y.multiply(BigInteger.valueOf((long) (k*Math.pow(2, 50)))).shiftRight(50);
              k = 1;
          }
           
                  
          p1 = Integer.parseInt(resize(X.toString(2),53).substring(0, 8), 2) / 2;
          p2 = Integer.parseInt(resize(X.toString(2),53).substring(0, 8), 2) + 
              (Integer.parseInt(resize(X.toString(2),53).substring(0, 8), 2) / 2);
          u = Y;//Y.shiftLeft(i+2).shiftRight(2); 
          String n = ""; if(u.signum() == -1) { u = u.negate(); n = "-"; }
          us  = Integer.parseInt(n+resize1(resize(u.toString(2), 53), 64).substring(i, i+10), 2);
          
          if(R.signum() == -1)
          {
            Z = invertNum(R.negate().toString(2));
            String lz = Z.indexOf('1') < 0 ? Z.substring(0, Z.length() - 1) : Z.substring(0, Z.indexOf('1')); 
            Z = Long.toBinaryString(Long.valueOf(Z, 2) + 1); 
            Z = lz + Z; while(Z.length() < 63) { Z = '1' + Z; }
            
          }
          else
          {
            Z = R.toString(2); while(Z.length() < 63) { Z = '0' + Z; }
          }
         System.out.println(Z);
         
         //if((i == 6 || i == 8 || i == 10 || i == 12 || i == 16 || i == 18 || i == 20 || i == 22 || i == 24 || i == 32) && !t)
         if((i == 6 || i == 10 || i == 12 || i == 14 || i == 18 || i == 26 || i == 30 || i == 34 || i == 40) && (!t))
         {
             //i-=2;
             //if(t1) { t2 = true; }
             //if(t) { t1 = true; }
             //t = true;
         }
         else
         {
             t = false;
             t1 = false;
             t2 = false;
         }
          
          seg = sel(Z.substring(i+1, i+4), i+2, -1);// Sel(us, p1, p2);
        }
        
        
        //System.out.println(Z);
        
        //seg = Z.charAt(19) == '0' ? 1 : -1;
        
        /*for(int i = 20; i < 55; i = i +2)
        {
          System.out.println("R: " + R.longValue()/Math.pow(2, 61) +", X: " + X.shiftRight(15).longValue()/Math.pow(2, 37-(Eref-1023))
                +", Y: " + Y.shiftRight(15).longValue()/Math.pow(2, 37-(Eref-1023)) + ", seg: "+seg);
          
          if(seg < 0)  
          {  
             X1 = X.subtract(Y.multiply(BigInteger.valueOf(seg)).shiftRight(i));
             Y1 = Y.add(X.multiply(BigInteger.valueOf(seg)).shiftRight(i));
             X = X1; Y = Y1;
             R = R.subtract(BigDecimal.valueOf((seg * Math.pow(2, -i))).multiply(BigDecimal.valueOf(2).pow(61)).toBigInteger());
          }
          else if(seg > 0)
          {
            X1 = X.subtract(Y.multiply(BigInteger.valueOf(seg)).shiftRight(i));
            Y1 = Y.add(X.multiply(BigInteger.valueOf(seg)).shiftRight(i));
            X = X1; Y = Y1;
            R = R.subtract(BigDecimal.valueOf((seg * Math.pow(2, -i))).multiply(BigDecimal.valueOf(2).pow(61)).toBigInteger());
          }
                  
          p1 = Integer.parseInt(resize(X.toString(2),53).substring(0, 8), 2) / 2;
          p2 = Integer.parseInt(resize(X.toString(2),53).substring(0, 8), 2) + 
              (Integer.parseInt(resize(X.toString(2),53).substring(0, 8), 2) / 2);
          u = Y;//Y.shiftLeft(i+2).shiftRight(2); 
          String n = ""; if(u.signum() == -1) { u = u.negate(); n = "-"; }
          us  = Integer.parseInt(n+resize1(resize(u.toString(2), 53), 64).substring(i, i+10), 2);
          
          if(R.signum() == -1)
          {
            Z = invertNum(R.negate().toString(2));
            String lz = Z.indexOf('1') < 0 ? Z.substring(0, Z.length() - 1) : Z.substring(0, Z.indexOf('1')); 
            Z = Long.toBinaryString(Long.valueOf(Z, 2) + 1); 
            Z = lz + Z; while(Z.length() < 63) { Z = '1' + Z; }
            
          }
          else
          {
            Z = R.toString(2); while(Z.length() < 63) { Z = '0' + Z; }
          }
          //System.out.println(Z);
          
          seg = seg = sel(Z.substring(i+2, i+5), i, 1);// Sel(us, p1, p2);
          
        }*/
        
        /*if(Enew % 2 == 1)
            {
              Eref = Eref - ((Enew + 1)/2);
              X = (X.multiply(BigInteger.valueOf((long)((1/Math.sqrt(2)) * Math.pow(2, 54))))).shiftRight(54);
            }
            else
            {
              Eref = Eref - ((Enew + 2)/2);  
            }
        
        R = R.add(BigInteger.valueOf((long)(Enew * Math.log(2) * Math.pow(2, 58))).shiftLeft(2));*/
        System.out.println(R.shiftRight(2));
        System.out.println(R.shiftRight(2).longValue());
        //System.out.println(Y.longValue()/Math.pow(2, 52));
        Long Xo = X.longValue(); Long Yo = Y.longValue(); Long Zo = (R).longValue();
        int lx =leadZero(resize(Long.toString(Xo < 0 ? -Xo: Xo, 2), 63)), 
                ly = leadZero(resize(Long.toString(Yo < 0 ? -Yo: Yo, 2), 63)),
                lz = leadZero(resize(Long.toString(Zo < 0 ? -Zo: Zo, 2), 63));
        
        //System.out.println("lx: "+lx);
        xb = (Xo < 0 ? '1' : '0') + resize((Integer.toBinaryString(Eref-lx+10)),11) + 
                resize1(Long.toString(Xo < 0 ? -Xo: Xo, 2),54).substring(1, 53);
        yb = (Yo < 0 ? '1' : '0') + resize((Integer.toBinaryString(Eref-ly+10)),11) + 
                resize1(Long.toString(Yo < 0 ? -Yo: Yo, 2), 54).substring(1, 53);
        zb = (Zo < 0 ? '1' : '0') + resize((Integer.toBinaryString(1024-lz)),11) + 
                resize1(Long.toString(Zo < 0 ? -Zo: Zo, 2), 54).substring(1, 53);
        
        //System.out.println(resize1(Long.toString(Yo, 2), 54).substring(0, 53));
        System.out.println("Angle: " + z);
        //System.out.println("X: " + x);
        //System.out.println("Y: " + y);
        System.out.println("Xo: "+toDouble(xb));
        System.out.println("Yo: "+toDouble(yb));
        //System.out.println("Zo: "+/*(toDouble(xb) + toDouble(yb))*/toDouble(zb));
        //System.out.println("sqrt(y2 + x2): "+Math.hypot(x, y));
        //System.out.println("arctan(z): " + Math.atan(y));
        //System.out.println("ln(z): " + Math.log(y));
        //System.out.println("sqrt(z): " + Math.sqrt(y));
        System.out.println("cos(z): " + Math.cos(z));
        System.out.println("sin(z): " + Math.sin(z));
        //System.out.println("Exp(z): " + Math.exp(z));
        System.out.println("cos error: "+ (Math.cos(z) - toDouble(xb)));
        System.out.println("sin error: "+ (Math.sin(z) - toDouble(yb)));
        //System.out.println("exp error: " + (Math.exp(z) - (toDouble(xb)+toDouble(yb))));
        //System.out.println("atan error: "+ (Math.atan(y) - toDouble(zb)));
       //System.out.println("ln error: "+ (Math.log(y) - toDouble(zb)));
        //System.out.println("sqrt error: "+ (Math.sqrt(y) - toDouble(xb)));
       //System.out.println("modulus error: "+ (Math.hypot(x, y) - toDouble(xb)));
        //System.out.println(Math.tan(1.0E-4));
        System.out.println(Math.pow(2, -50));
    }
    
    public static String resize(String s, int length) {
        while (s.length() < length) {
            s = '0' + s;
        }
        return s;
    }
    
    public static String resize1(String s, int length) {
        while (s.length() < length) {
            s = s + '0';
        }
        return s;
    }
    
    public static int leadZero(String S)
    {
        int o = 0;
        
        while(o < S.length() && S.charAt(o) != '1')
        {
            o++;
        }
        return o;
    }
    
    public static double toDouble(String binary) {
        double d = 1;

        int i, exp = 0;

        for (i = 1; i < 12; i++) {
            if (binary.charAt(i) == '1') {
                exp = (int) (exp + Math.pow(2, (11 - i)));
            }
        }

        exp = exp - 1023;

        for (; i < 64; i++) {
            if (binary.charAt(i) == '1') {
                d = d + (1 / (Math.pow(2, i - 11)));
            }
        }
        
        if (binary.charAt(0) == '1') 
        {
            d = -d;
        }
        
        return d * Math.pow(2, exp);
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
                return 0;
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
                return -1;
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
}
