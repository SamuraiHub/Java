package Cordic;


import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.util.Random;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Aljarhi
 */
public class Cordic_Intermediate_Calc {

    /**
     * @param args the command line arguments
     */
    
    private static String Zn;
    private static BigInteger DP = BigInteger.ZERO;
    private static BigInteger DM = BigInteger.ZERO;
    
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

    public static double toDecimal(String binary) {
        double d = 0; int j = 0;

        for (int i = 0; i < binary.length(); i++) {
            if (binary.charAt(i) == '1') {
                j = i +1;
                d = d + (1 / (Math.pow(2, j)));
            }
        }
        return d;
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

    public static String resize(String s, int length) {
        while (s.length() < length) {
            s = '0' + s;
        }
        return s;
    }

    public static String addSpace(int length) {
        String s = "";

        for (int k = 0; k < length; k++) {
            s = s + ' ';
        }
        return s;
    }

    public static void Arctan_Table() {
        for (int i = 1, j = 0; j < 64; i++, j++) {
            double p2 = Math.pow(2, i);
            String s; 
            //double b = atanh(Math.pow(2, -i));

            //BigInteger a = 
                  //  BigDecimal.valueOf(b).multiply(BigDecimal.valueOf(2).pow(61)).toBigInteger();
            s = "6'b" + resize(Integer.toBinaryString(j), 6) + ": S = 6'd" + i + ";";
            
            if((i - 1) % 3 == 0)
                s = s + "\r\n6'b" + resize(Integer.toBinaryString(++j), 6) + ": S = 6'd" + i + ";";
            
           System.out.println(s /*+ " //  1/" + (long) p2*/);
        }
    }

    public static int S(int m, int i) {
        if (m == -1) {
            if (i > 41) {
                return i - 2;
            } else if (i > 13) {
                return i - 1;
            } else if (i > 3) {
                return i;
            } else {
                return i + 1;
            }
        } else {
            return i;
        }
    }

    public static double KM(int m, boolean om) {
        double km = 0;
        int rd = 0;
        if (om) {
            rd = 1;
        }

        km = Math.sqrt(1 + (m * Math.pow(rd, 2) * 1 / Math.pow(2, 2 * S(m, 0))));

        for (int i = 1; i < 50; i++) {
            km = km * Math.sqrt(1 + (m * Math.pow(rd, 2) * 1 / Math.pow(2, 2 * S(m, i))));
        }
        return km;
    }

    public static double atanh(double a) 
    {  
       return 0.5 * Math.log((1 + a) / (1 - a));
    }
    
    public static BigInteger ALPHA(int m, int i) 
    {  
       if(m == 1) 
           return BigDecimal.valueOf(Math.atan(Math.pow(2, -i))).multiply(BigDecimal.valueOf(2).pow(61)).toBigInteger();
      
       else if(m == 0)
           return BigInteger.valueOf(2).pow(61 - i);
       
       else
         return BigDecimal.valueOf(atanh(Math.pow(2, -i))).multiply(BigDecimal.valueOf(2).pow(61)).toBigInteger();  
    }

    public static void cordic(BigInteger[] X, BigInteger Y[], BigInteger Z[], int m, int seg, BigInteger Alpha, int i, boolean vm) 
    {
        if (i == 54) 
        {
            if(vm) { Z[0] = (Z[0].add(DM)).subtract(DP); }
            return;
        }
        
        System.out.println("Iteration: " + i + ", " + X[0] + ", " + Y[0] + ", " + Z[0] + "," + seg + "," + Alpha);
        //System.out.println("Iteration: " + i + ", " + Z[0] + ", " + seg);
        
        if(seg == -1)
        {
          X[1] = X[0].subtract(Y[0].multiply(BigInteger.valueOf(m)).negate().shiftRight(i));
          Y[1] = Y[0].add(X[0].negate().shiftRight(i));
          if(i < 18)
          Z[1] = Z[0].subtract(Alpha.negate());
        }
        
        else
        {
          X[1] = X[0].subtract(Y[0].multiply(BigInteger.valueOf(m)).shiftRight(i));
          Y[1] = Y[0].add(X[0].shiftRight(i));
          if(i < 18)
          Z[1] = Z[0].subtract(Alpha);
        }

        X[0] = X[1];
        Y[0] = Y[1];
        Z[0] = Z[1];

        if ((vm && Y[0].compareTo(BigInteger.ZERO) < 0) || (!vm && Z[0].compareTo(BigInteger.ZERO) >= 0 /*&& i < 18*/)) {
            seg = 1;
        } else {
            seg = -1;
        }
        
        if(m == -1 && i > 1 && (i - 1) % 3 == 0)
        {
            if(seg == -1)
           {
             X[1] = X[0].subtract(Y[0].multiply(BigInteger.valueOf(m)).negate().shiftRight(i));
             Y[1] = Y[0].add(X[0].negate().shiftRight(i));
             //if(i < 18)
             Z[1] = Z[0].subtract(Alpha.negate());
           }
        
           else
           {
             X[1] = X[0].subtract(Y[0].multiply(BigInteger.valueOf(m)).shiftRight(i));
             Y[1] = Y[0].add(X[0].shiftRight(i));
             //if(i < 18)
             Z[1] = Z[0].subtract(Alpha);
           }

           X[0] = X[1];
           Y[0] = Y[1];
           Z[0] = Z[1];

           if ((vm && Y[0].compareTo(BigInteger.ZERO) < 0) || (!vm && Z[0].compareTo(BigInteger.ZERO) >= 0 /*&& i < 18*/)) {
               seg = 1;
           } else {
               seg = -1;
           }
        }
        
        if(!vm && i == 18) 
        { 
           Zn = Z[0].toString(2); while(Zn.length() < 63) { Zn = '0' + Zn; }
           seg = 1 - ((Character.digit(Zn.charAt(i), 2)) << 1);
        }
        
        else if(!vm && i > 18)
        {
            seg = ((Character.digit(Zn.charAt(i), 2)) << 1) - 1;
        } 
        
        if(vm && i >= 18)
        {
           if(seg == -1)
           {
               DM = DM.add(BigInteger.valueOf(2).pow(61 - i));
           }
           
           else
           {
              DP = DP.add(BigInteger.valueOf(2).pow(61 - i)); 
           }
        } 
        
        //if( i < 18)
        Alpha = ALPHA(m, i + 1);

        cordic(X, Y, Z, m, seg, Alpha, i + 1, vm);
    }
    
    public static double ALPHA(int m, long i) 
    {  
            if (m == 1) return Math.atan(Math.pow(2, -i));
        
       else if (m == 0) return Math.pow(2, -i);
               
                   else return atanh(Math.pow(2, -i));
    }

    public static void cordic(double X[], double Y[], double Z[], int m, int seg, double Alpha, int i, boolean vm) 
    {
        if (i == 52) {
            return;
        }
       
          X[1] = X[0] - (Y[0] * m * seg * Math.pow(2, -i));
          Y[1] = Y[0] + (X[0]* seg * Math.pow(2, -i));
          Z[1] = Z[0] - (seg * Alpha);

        X[0] = X[1];
        Y[0] = Y[1];
        Z[0] = Z[1];

        if ((vm && Y[0] < 0) || (!vm && Z[0] >= 0)) {
            seg = 1;
        } else {
            seg = -1;
        }

        if(m == -1 && (i - 1) % 3 == 0)
        {
           X[1] = X[0] - (m * seg * Math.pow(2, -i)* Y[0]);
           Y[1] = Y[0] + (seg * Math.pow(2, -i) * X[0]);
           Z[1] = Z[0] - (seg * Alpha/*Math.pow(2, -S(m, i))*/);

           X[0] = X[1];
           Y[0] = Y[1];
           Z[0] = Z[1];

           if ((vm && Y[0] < 0) || (!vm && Z[0] >= 0)) {
               seg = 1;
           } else {
              seg = -1;
           }
        }

        Alpha = ALPHA(m, (long)(i + 1));

        cordic(X, Y, Z, m, seg, Alpha, i + 1, vm);
    }
    
    public static long multiply_by_pi_div_2(Long X) {
        long w1 = X;
        long w4 = w1 << 2;
        long w3 = w4 - w1;
        long w8 = w1 << 3;
        long w11 = w3 + w8;
        long w24 = w3 << 3;
        long w25 = w1 + w24;
        long w16 = w1 << 4;
        long w17 = w1 + w16;
        long w2176 = w17 << 7;
        long w2175 = w2176 - w1;
        long w409600 = w25 << 14;
        long w411775 = w2175 + w409600;
        long w26353600 = w411775 << 6;
        long w26353589 = w26353600 - w11;
        long w52707178 = w26353589 << 1;

        String b = Long.toBinaryString(w52707178);
        int l = b.length();
        if (l < 58) {
            for (int i = 0; i < 58 - l; i++) {
                b = '0' + b;
            }
        }

        return Long.parseLong(b.substring(25, 58), 2);
    }

    public static long multiply_by_2_div_pi(Long X) {
        long w1 = X;
        long w4 = w1 << 2;
        long w3 = w4 - w1;
        long w128 = w1 << 7;
        long w125 = w128 - w3;
        long w8 = w1 << 3;
        long w9 = w1 + w8;
        long w72 = w9 << 3;
        long w81 = w9 + w72;
        long w20736 = w81 << 8;
        long w20861 = w125 + w20736;
        long w10680832 = w20861 << 9;
        long w10680707 = w10680832 - w125;
        long w21361414 = w10680707 << 1;

        String b = Long.toBinaryString(w21361414);
        int l = b.length();
        if (l < 57) {
            for (int i = 0; i < 57 - l; i++) {
                b = '0' + b;
            }
        }

        return Long.parseLong(b.substring(25, 57), 2);
    }
    
public static long multiply_by_1_div_ln2(long X) {
  long w1 = X;
  long w128 = w1 << 7;
  long w129 = w1 + w128;
  long w2113536 = w129 << 14;
  long w2113407 = w2113536 - w129;
  long w8453628 = w2113407 << 2;
  long w8453627 = w8453628 - w1;
  long w1024 = w1 << 10;
  long w8452603 = w8453627 - w1024;
  long w4194304 = w1 << 22;
  long w12646907 = w8452603 + w4194304;
  long w528384 = w129 << 12;
  long w12118523 = w12646907 - w528384;
  long w256 = w1 << 8;
  long w255 = w256 - w1;
  long w16320 = w255 << 6;
  long w12102203 = w12118523 - w16320;
  long w48408812 = w12102203 << 2;
  
  String b = Long.toBinaryString(w48408812);
        int l = b.length();
        if (l < 57) {
            for (int i = 0; i < 57 - l; i++) {
                b = '0' + b;
            }
        }

        return Long.parseLong(b.substring(25, 57), 2);
}

    public static long multiply_by_ln2(long X) {
        long w1 = X;
        long w4 = w1 << 2;
        long w5 = w1 + w4;
        long w16 = w1 << 4;
        long w21 = w5 + w16;
        long w64 = w1 << 6;
        long w63 = w64 - w1;
        long w8192 = w1 << 13;
        long w8129 = w8192 - w63;
        long w1376256 = w21 << 16;
        long w1368127 = w1376256 - w8129;
        long w21890032 = w1368127 << 4;
        long w23258159 = w1368127 + w21890032;

        String b = Long.toBinaryString(w23258159);
        int l = b.length();
        if (l < 57) {
            for (int i = 0; i < 57 - l; i++) {
                b = '0' + b;
            }
        }

        return Long.parseLong(b.substring(25, 57), 2);
    }

    public static long multiply_by_kmc(Long X) {
        long w1 = X;
        long w4 = w1 << 2;
        long w5 = w1 + w4;
        long w80 = w5 << 4;
        long w79 = w80 - w1;
        long w160 = w5 << 5;
        long w155 = w160 - w5;
        long w323584 = w79 << 12;
        long w323429 = w323584 - w155;
        long w20699456 = w323429 << 6;
        long w20376027 = w20699456 - w323429;

        String b = Long.toBinaryString(w20376027);
        int l = b.length();
        if (l < 57) {
            for (int i = 0; i < 57 - l; i++) {
                b = '0' + b;
            }
        }

        return Long.parseLong(b.substring(25, 57), 2);
    }
    
    public static long multiply_by_kmh(Long X) {
        long w1 = X;
        long w4 = w1 << 2;
        long w3 = w4 - w1;
        long w24 = w3 << 3;
        long w25 = w1 + w24;
        long w64 = w1 << 6;
        long w89 = w25 + w64;
        long w8 = w1 << 3;
        long w7 = w8 - w1;
        long w131072 = w1 << 17;
        long w131079 = w7 + w131072;
        long w6400 = w25 << 8;
        long w124679 = w131079 - w6400;
        long w46661632 = w89 << 19;
        long w46786311 = w124679 + w46661632;

        String b = Long.toBinaryString(w46786311);
        int l = b.length();
        if (l < 57) {
            for (int i = 0; i < 57 - l; i++) {
                b = '0' + b;
            }
        }

        return Long.parseLong(b.substring(25, 57), 2);
    }

    public static long leadZero(BigInteger x, int len) {
        String b = x.toString(2);
        long O = 0;
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

    public static long Mzn(long X, long Y, long Z, int ez) {
        if (X < 0) {
            if (Y > 0) {
                Z = (long) (Math.PI * Math.pow(2, ez)) - Z;
            } else {
                Z = Z - (long) (Math.PI * Math.pow(2, ez));
            }
        } else {
            if (Y < 0) {
                Z = -Z;
            }
        }

        return Z;
    }

    public static long Baker(long Z, int it, int i) {
        String b = Long.toBinaryString(Z);

        int l = b.length();
        if (l < 64) {
            for (int j = 0; j < 64 - l; j++) {
                b = '0' + b;
            }
        }

        if (it == i) {
            return 1 - (Integer.parseInt("" + b.charAt(i), 2) << 1);
        } else {
            return (Integer.parseInt("" + b.charAt(i), 2) << 1) - 1;
        }
    }
    
    public static double shift(double a, int sh)
    {
        if(sh >= 0)
        {
           for(int i = 0; i < sh; i++)
           {
             a = a * 2;   
           }   
        }
        
        if(sh < 0)
        {
           for(int i = 0; i < sh; i++)
           {
             a = a / 2;   
           }   
        }
        
        return a;
    }

    public static void main(String[] args) {
        // TODO code application logic here
        //System.out.println("km = " + toBinaryString(Double.doubleToLongBits(KM(1, false))));
        //System.out.println(toBinaryString(Double.doubleToLongBits(Math.PI)));

        Random r = new Random();
        
       /*for(int i = 0; i < 100; i++)
        {
        double x = r.nextDouble() * 100; double y = r.nextDouble() * 100; double z =  r.nextDouble() * 100;
        
        while(x <= y)
        x = r.nextDouble() * 100;
        
        //x = r.nextBoolean() == true ? -x : x; y = r.nextBoolean() == true ? -y : y; z = r.nextBoolean() == true ? -z : z;
        
        System.out.println("x[" + i + "]" + " <= 64'b" + 
        (r.nextBoolean() == true ? "1" + toBinaryString(Double.doubleToLongBits(x)).substring(1) : toBinaryString(Double.doubleToLongBits(x))) + ";  "
                         + "y[" + i + "]" + " <= 64'b" + 
        (r.nextBoolean() == true ? "1" + toBinaryString(Double.doubleToLongBits(y)).substring(1) : toBinaryString(Double.doubleToLongBits(y))) + ";  "
                         + "z[" + i + "]" + " <= 64'b" +
        (r.nextBoolean() == true ? "1" + toBinaryString(Double.doubleToLongBits(z)).substring(1) : toBinaryString(Double.doubleToLongBits(z))) + ";");
        
        //x[0] <= 17'd32768;  y[0] <= 17'd0;      z[0] <= 17'd0;   
        } */
        
        double[] X = new double[2];
        double[] Y = new double[2];
        double[] Z = new double[2];

        X[0] = 95.31848996383394;//r.nextDouble() * 100;//1/1.646760258121065;//1/0.7171848151519904;
        Y[0] = 0;//r.nextDouble() * 100;
        Z[0] = 81.5253351224385;//r.nextDouble() * Math.log(2); 
        
        String xb = toBinaryString(Double.doubleToLongBits(X[0]));
        System.out.println(xb);
        String yb = toBinaryString(Double.doubleToLongBits(Y[0]));
        System.out.println(yb);
        String zb = toBinaryString(Double.doubleToLongBits(Z[0]));
        System.out.println(zb);
      
        long Ex = Integer.parseInt(xb.substring(1, 12), 2);
        long Ey = Integer.parseInt(yb.substring(1, 12), 2);
        long Ez = Integer.parseInt(zb.substring(1, 12), 2);

        long qc, qh, rc, rh, kxh, kyh, Exln2, Enewln2;

        long my, mx, mz, Q, D, mxMmy, mxPmy, mXMmY, l, Enew, Mx, My, Mz, MX, MY, lnx, lx, ly, lz, lex, zn;
        long Eref3, Eref1, Eref2;
        BigInteger R, ex;
        
        Eref3 = Math.max(Ex, Ey);

            my = Long.parseLong((Y[0] == 0 ? '0' : '1') + yb.substring(12, 64), 2) >> Eref3 - Ey;
            mx = Long.parseLong((X[0] == 0 ? '0' : '1') + xb.substring(12, 64), 2) >> Eref3 - Ex;
            //my = (long) -(my - (1 * Math.pow(2, 52 - (Eref3 - 1023))));
            //mx = (long) (mx + (1 * Math.pow(2, 52 - (Eref3 - 1023))));
            System.out.println("mx: " + mx + "my: " + my);

        if (Ez > 1023) {
            mz = Long.parseLong((Z[0] == 0 ? '0' : '1') + zb.substring(12, 64), 2);// << Ez - 1023;
        } else {
            mz = Long.parseLong((Z[0] == 0 ? '0' : '1') + zb.substring(12, 64), 2);// >> 1023 - Ez;
        }
        String bmx = toBinaryString(mx); String bmy = toBinaryString(my);
        
        BigInteger[] kxc = new BigInteger[11];
        BigInteger[] kyc = new BigInteger[11];
 
        kxc[0] = BigInteger.valueOf(multiply_by_kmh(Long.parseLong(bmx.substring(0, 6), 2)));
        kyc[0] = BigInteger.valueOf(multiply_by_kmh(Long.parseLong(bmy.substring(0, 6), 2)));

        kxc[1] = BigInteger.valueOf(multiply_by_kmh(Long.parseLong(bmx.substring(6, 12), 2)));
        kyc[1] = BigInteger.valueOf(multiply_by_kmh(Long.parseLong(bmy.substring(6, 12), 2)));
        
        kxc[2] = BigInteger.valueOf(multiply_by_kmh(Long.parseLong(bmx.substring(12, 18), 2)));
        kyc[2] = BigInteger.valueOf(multiply_by_kmh(Long.parseLong(bmy.substring(12, 18), 2)));
        
        kxc[3] = BigInteger.valueOf(multiply_by_kmh(Long.parseLong(bmx.substring(18, 24), 2)));
        kyc[3] = BigInteger.valueOf(multiply_by_kmh(Long.parseLong(bmy.substring(18, 24), 2)));
        
        kxc[4] = BigInteger.valueOf(multiply_by_kmh(Long.parseLong(bmx.substring(24, 30), 2)));
        kyc[4] = BigInteger.valueOf(multiply_by_kmh(Long.parseLong(bmy.substring(24, 30), 2)));
        
        kxc[5] = BigInteger.valueOf(multiply_by_kmh(Long.parseLong(bmx.substring(30, 36), 2)));
        kyc[5] = BigInteger.valueOf(multiply_by_kmh(Long.parseLong(bmy.substring(30, 36), 2)));
        
        kxc[6] = BigInteger.valueOf(multiply_by_kmh(Long.parseLong(bmx.substring(36, 42), 2)));
        kyc[6] = BigInteger.valueOf(multiply_by_kmh(Long.parseLong(bmy.substring(36, 42), 2)));
        
        kxc[7] = BigInteger.valueOf(multiply_by_kmh(Long.parseLong(bmx.substring(42, 48), 2)));
        kyc[7] = BigInteger.valueOf(multiply_by_kmh(Long.parseLong(bmy.substring(42, 48), 2)));
        
        kxc[8] = BigInteger.valueOf(multiply_by_kmh(Long.parseLong(bmx.substring(48, 54), 2)));
        kyc[8] = BigInteger.valueOf(multiply_by_kmh(Long.parseLong(bmy.substring(48, 54), 2)));
        
        kxc[9] = BigInteger.valueOf(multiply_by_kmh(Long.parseLong(bmx.substring(54, 60), 2)));
        kyc[9] = BigInteger.valueOf(multiply_by_kmh(Long.parseLong(bmy.substring(54, 60), 2)));
        
        kxc[10] = BigInteger.valueOf(multiply_by_kmh(Long.parseLong(bmx.substring(60, 64), 2)));
        kyc[10] = BigInteger.valueOf(multiply_by_kmh(Long.parseLong(bmy.substring(60, 64), 2)));
        
        BigInteger xK = BigInteger.ZERO; BigInteger yK = BigInteger.ZERO; int xkd = 0, ykd = 0, zkd = 0;
        
        for(int i = 1; i < 11; i++)
        {
           xK = xK.add(kxc[i - 1].shiftLeft((64 - (6 * i)) - 25));   
           yK = yK.add(kyc[i - 1].shiftLeft((64 - (6 * i)) - 25));
        }
        
        for(int j = 7; j < 11; j++)
        {
               int fp = (64 - (6 * j)) - 25;
               
               String fpb = kxc[j - 1].toString(2);
               
            while (fpb.length() < 32) 
            {
                fpb = '0' + fpb;
            }
               
               fpb = fpb.substring(fpb.length() + fp, fpb.length());
               
               while(fpb.length() < 25)
               {
                   fpb = fpb + '0';
               }
               xkd = xkd + Integer.parseInt(fpb, 2);
               
               fpb = kyc[j - 1].toString(2);
               
               while (fpb.length() < 32) 
               {
                   fpb = '0' + fpb;
               }
               
               fpb = fpb.substring(fpb.length() + fp, fpb.length());
               
               while(fpb.length() < 25)
               {
                   fpb = fpb + '0';
               }
               ykd = ykd + Integer.parseInt(fpb, 2);
        }
        
        xkd += kxc[10].intValue(); ykd += kyc[10].intValue();
        
        String Xkd = Integer.toBinaryString(xkd); String Ykd = Integer.toBinaryString(ykd);
        
        while (Xkd.length() < 25) { Xkd = Xkd + '0'; } while (Ykd.length() < 25) { Ykd = Ykd  + '0'; }
        
        if(Xkd.length() > 25) { xK = xK.add(BigInteger.valueOf(Integer.parseInt(Xkd.substring(0, Xkd.length() - 25), 2))); }
        if(Ykd.length() > 25) { yK = yK.add(BigInteger.valueOf(Integer.parseInt(Ykd.substring(0, Ykd.length() - 25), 2))); }
        
        if(Xkd.charAt(Xkd.length() - 25) == '1') { xK = xK.add(BigInteger.ONE); } 
        if(Ykd.charAt(Ykd.length() - 25) == '1') { yK = yK.add(BigInteger.ONE); }
        
        String bmz = toBinaryString(mz); BigInteger dp[] = new BigInteger[11];
        
        dp[0] = BigInteger.valueOf(multiply_by_1_div_ln2(Long.parseLong(bmz.substring(0, 6), 2)));
        dp[1] = BigInteger.valueOf(multiply_by_1_div_ln2(Long.parseLong(bmz.substring(6, 12), 2)));
        dp[2] = BigInteger.valueOf(multiply_by_1_div_ln2(Long.parseLong(bmz.substring(12, 18), 2)));
        dp[3] = BigInteger.valueOf(multiply_by_1_div_ln2(Long.parseLong(bmz.substring(18, 24), 2)));  
        dp[4] = BigInteger.valueOf(multiply_by_1_div_ln2(Long.parseLong(bmz.substring(24, 30), 2)));
        dp[5] = BigInteger.valueOf(multiply_by_1_div_ln2(Long.parseLong(bmz.substring(30, 36), 2)));
        dp[6] = BigInteger.valueOf(multiply_by_1_div_ln2(Long.parseLong(bmz.substring(36, 42), 2)));
        dp[7] = BigInteger.valueOf(multiply_by_1_div_ln2(Long.parseLong(bmz.substring(42, 48), 2)));
        dp[8] = BigInteger.valueOf(multiply_by_1_div_ln2(Long.parseLong(bmz.substring(48, 54), 2)));
        dp[9] = BigInteger.valueOf(multiply_by_1_div_ln2(Long.parseLong(bmz.substring(54, 60), 2)));
        dp[10] = BigInteger.valueOf(multiply_by_1_div_ln2(Long.parseLong(bmz.substring(60, 64), 2)));
        
        int ipz = 0; BigInteger fpz = BigInteger.ZERO;
        
        for(int i = 1; i < 11; i++)
        {
            String bkz = dp[i - 1].toString(2);
            
            l = bkz.length();
        if (l < 32) {
            for (int j = 0; j < 32 - l; j++) {
                bkz = '0' + bkz;
            }
        }
            
            int pz = ((64 - (6 * i)) - 77) + 32; 
            
            if(pz > 0)
            {
                String bkfz = bkz.substring(pz, bkz.length());
                l = bkfz.length();
                if (l < 77) {
            for (int j = 0; j < 77 - l; j++) {
                bkfz = bkfz + '0';
             }
            }    
                fpz = fpz.add(new BigInteger(bkfz, 2));
                ipz = ipz + Integer.parseInt(bkz.substring(0, pz), 2);
            }
            
            else
            {
                for(int j = 0; j > pz; j--)
                {
                    bkz = '0' + bkz;
                }
                
                l = bkz.length();
                if (l < 77) {
            for (int j = 0; j < 77 - l; j++) {
                bkz = bkz + '0';
            }
                }
                
                fpz = fpz.add(new BigInteger(bkz, 2));
        }
        }
        
        String bkz = dp[10].toString(2);
        
            l = bkz.length();
        if (l < 32) {
            for (int j = 0; j < 32 - l; j++) {
                bkz = '0' + bkz;
            }
        }
        
        for(int j = 0; j > -42; j--)
                {
                    bkz = '0' + bkz;
                }
                
                fpz = fpz.add(new BigInteger(bkz, 2));
        
        String bfz = fpz.toString(2);
        l = bfz.length();
        if (l < 77) {
            for (int j = 0; j < 77 - l; j++) {
                bfz = '0' + bfz;
            }
        }
        
        ipz = ipz + (bfz.length() > 77  ? Integer.parseInt(bfz.substring(0, bfz.length() - 77), 2) : 0);
        
        BigDecimal zK = BigDecimal.valueOf(ipz).add(BigDecimal.valueOf(toDecimal(bfz.substring(bfz.length() - 77, bfz.length()))));
        
        Q = ipz;  R = new BigInteger(bfz.substring(bfz.length() - 77, bfz.length() - 13), 2);
        
        System.out.println("R: " + R + "MZ: " + mz);
        
        bmz = R.toString(2); while(bmz.length() < 64) { bmz = '0' + bmz; }
        
        dp[0] = BigInteger.valueOf(multiply_by_ln2((Long.parseLong(bmz.substring(0, 7), 2))));
        dp[1] = BigInteger.valueOf(multiply_by_ln2((Long.parseLong(bmz.substring(7, 14), 2))));
        dp[2] = BigInteger.valueOf(multiply_by_ln2((Long.parseLong(bmz.substring(14, 21), 2))));
        dp[3] = BigInteger.valueOf(multiply_by_ln2((Long.parseLong(bmz.substring(21, 28), 2))));  
        dp[4] = BigInteger.valueOf(multiply_by_ln2((Long.parseLong(bmz.substring(28, 35), 2))));
        dp[5] = BigInteger.valueOf(multiply_by_ln2((Long.parseLong(bmz.substring(35, 42), 2))));
        dp[6] = BigInteger.valueOf(multiply_by_ln2((Long.parseLong(bmz.substring(42, 49), 2))));
        dp[7] = BigInteger.valueOf(multiply_by_ln2((Long.parseLong(bmz.substring(49, 56), 2))));
        dp[8] = BigInteger.valueOf(multiply_by_ln2((Long.parseLong(bmz.substring(56, 63), 2))));
        dp[9] = BigInteger.valueOf(multiply_by_ln2((Long.parseLong(bmz.substring(63, 64), 2))));
        
        fpz = BigInteger.ZERO;
        
        for(int i = 1; i < 10; i++)
        {
           fpz = fpz .add(dp[i - 1].shiftLeft((64 - (7 * i)) - 25));   
        }
        
        for(int j = 6; j < 10; j++)
        {
               int fp = (64 - (7 * j)) - 25;
               
               String fpb = dp[j - 1].toString(2);
               
            while (fpb.length() < 32) 
            {
                fpb = '0' + fpb;
            }
               
               fpb = fpb.substring(fpb.length() + fp, fpb.length());
               
               while(fpb.length() < 25)
               {
                   fpb = fpb + '0';
               }
               
               zkd = zkd + Integer.parseInt(fpb, 2);
        }
        
        zkd += dp[9].intValue();
        
        String Zkd = Integer.toBinaryString(zkd); while (Zkd.length() < 25) { Zkd = Zkd + '0'; }
        
        if(Zkd.length() > 25) { fpz = fpz.add(BigInteger.valueOf(Integer.parseInt(Zkd.substring(0, Zkd.length() - 25), 2))); }
        
        if(Zkd.charAt(Zkd.length() - 25) == '1') { fpz = fpz.add(BigInteger.ONE); }
        
        BigInteger xKD; BigInteger yKD;
        
        //Q = 3 - Q;
        
        if(Q == 1)
        {
            xKD = yK.negate();
            yKD = xK;
        }   
        else if(Q == 2)
        {
            xKD = xK.negate();
            yKD = yK.negate();
        }
        else if(Q == 3)
        {
            xKD = yK;
            yKD = xK.negate();
        }
        else
        {
            xKD = xK;
            yKD = yK;
        }
        bmz = toBinaryString(mz); 
        //Y[0] = X[0] - 0.25; X[0] = X[0] + 0.25;
        System.out.println(X[0] + "," + Y[0] + "," + Z[0] + "," + (X[0] * Z[0]));

        BigInteger[] x = new BigInteger[2]; BigInteger[] y = new BigInteger[2]; BigInteger[] z = new BigInteger[2];
        //System.out.println("Mx: " + (xK.add(yK)) + "My: " + ((xK.subtract(yK)).shiftRight(2 * (int)Q)));
        x[0] = BigInteger.valueOf(mx);//(xK.add(yK)).add((xK.subtract(yK)).shiftRight(2 * (int)Q));//BigInteger.valueOf(mx);//new BigInteger("10111101101101010011110010110101110100001011111010101", 2).shiftLeft(1); 
        y[0] = BigInteger.valueOf(my);;//(xK.add(yK)).subtract((xK.subtract(yK)).shiftRight(2 * (int)Q));//BigInteger.ZERO;//new BigInteger("11011110101110011111010110101110100100100110001110011", 2); 
        z[0] = BigInteger.valueOf(mz).shiftLeft(9);//BigInteger.valueOf(mz).shiftLeft(9);
        
        //System.out.println("xK: "+ xK + "yK: " + yK);
        
      /* if(x[0].compareTo(y[0].multiply(BigInteger.valueOf(2))) < 0)
        {
            long L = leadZero(x[0].subtract(y[0]), x[0].bitLength());
            Enew = L == 1 ? 1 : L - 1;
            /*if(Enew % 2 == 1) 
            { 
                y[0] = y[0].shiftRight(1); 
                x[0] = x[0].shiftRight(1); 
                
                x[1] = (x[0].add(y[0])).add((x[0].subtract(y[0])).shiftLeft((int)Enew + 1));
                y[0] = (x[0].add(y[0])).subtract((x[0].subtract(y[0])).shiftLeft((int)Enew + 1));
                x[0] = x[1];
            } 
                    
            else
            {
               x[1] = (x[0].add(y[0])).add((x[0].subtract(y[0])).shiftLeft((int)Enew));
               y[0] = (x[0].add(y[0])).subtract((x[0].subtract(y[0])).shiftLeft((int)Enew));
               x[0] = x[1];
            ///}
            //x[0] = x[0].shiftRight(((int)Enew / 2) + 1);
            //y[0] = y[0].shiftRight(((int)Enew / 2) + 1);
        }
        else
        {
             Enew = 0;
        } 
        System.out.println("Enew: " + Enew); */
        //System.out.println("x0: " + xK.add(yK).shiftRight(1) + " y0: " + Enew);
        System.out.println(((new BigDecimal(x[0])).divide(BigDecimal.valueOf(2).pow((int)(52 - (Ex - 1023))))) 
                + "," + ((new BigDecimal(y[0])).divide(BigDecimal.valueOf(2).pow((int)(52 - (Ey - 1023))))) + "," + 
                ((new BigDecimal(z[0])).divide(BigDecimal.valueOf(2).pow((int)(61 - (Ez - 1023))))));
        
       //x[0] = new BigInteger(bmx, 2); 
       //y[0] = new BigInteger(bmy, 2); 
       //z[0] = new BigInteger(bmz, 2);
        
       /*X[0] = (X[0] + 1)/0.7171848151519904;//1/1.646760258121065;//1/0.7171848151519904;
        Y[0] = (Y[0] - 1)/0.7171848151519904;
        
        if(Enew > 0)
        {
          /*if(Enew % 2 == 1) 
          { 
              X[0] = X[0] / 2; 
              Y[0] = Y[0] / 2; 
              
              X[1] = (X[0] + Y[0]) + (Math.pow(2, Enew+1) * (X[0] - Y[0]));
              Y[0] = (X[0] + Y[0]) - (Math.pow(2, Enew+1) * (X[0] - Y[0]));
              X[0] = X[1];
          }
          
          else
          {
             X[1] = (X[0] + Y[0]) + (Math.pow(2, Enew) * (X[0] - Y[0]));
             Y[0] = (X[0] + Y[0]) - (Math.pow(2, Enew) * (X[0] - Y[0]));
             X[0] = X[1];
          //}
        } */
        
        //z[0] = z[0].shiftLeft(63 - 53);
        
        //System.out.println(((new BigDecimal(x[0])).divide(BigDecimal.valueOf(2).pow((int)(52 - (Eref3 - 1023))))) 
                //+ "," + ((new BigDecimal(y[0])).divide(BigDecimal.valueOf(2).pow((int)(52 - (Eref3 - 1023))))) + "," + 
                //((new BigDecimal(z[0])).divide(BigDecimal.valueOf(2).pow((int)(64)))));
        
        //BigInteger C = BigInteger.ZERO;
        
        int i = 0;
        
        //while(z[0].compareTo(BigInteger.ZERO) > 0)
       //{
          cordic(x, y, z, 0, z[0].compareTo(BigInteger.ZERO) >= 0 ? 1 : -1, ALPHA(0, 0), 0, false);
          //C = C.add(y[0]);
          //y[0] = BigInteger.ZERO;
          //i++;
        //}
        //y[0] = C.subtract(BigInteger.valueOf(2).pow((int) (53 - Eref3)));
        
        //double d = 0;
        
        //while(Z[0] > 0)
        //{
          cordic(X, Y, Z, 0, Z[0] >= 0 ? 1 : -1, ALPHA(0, (long)0), 0, false);
          //d = d + Y[0];
          //Y[0] = 0;
        //}
       //Y[0] = d;
          
       // bmz = BigInteger.valueOf(Enew).shiftLeft(61).toString(2); while(bmz.length() < 64) { bmz = '0' + bmz; }
        
        dp[0] = BigInteger.valueOf(multiply_by_ln2(Long.parseLong(bmz.substring(0, 7), 2)));
        dp[1] = BigInteger.valueOf(multiply_by_ln2(Long.parseLong(bmz.substring(7, 14), 2)));
        dp[2] = BigInteger.valueOf(multiply_by_ln2(Long.parseLong(bmz.substring(14, 21), 2)));
        dp[3] = BigInteger.valueOf(multiply_by_ln2(Long.parseLong(bmz.substring(21, 28), 2)));  
        dp[4] = BigInteger.valueOf(multiply_by_ln2(Long.parseLong(bmz.substring(28, 35), 2)));
        dp[5] = BigInteger.valueOf(multiply_by_ln2(Long.parseLong(bmz.substring(35, 42), 2)));
        dp[6] = BigInteger.valueOf(multiply_by_ln2(Long.parseLong(bmz.substring(42, 49), 2)));
        dp[7] = BigInteger.valueOf(multiply_by_ln2(Long.parseLong(bmz.substring(49, 56), 2)));
        dp[8] = BigInteger.valueOf(multiply_by_ln2(Long.parseLong(bmz.substring(56, 63), 2)));
        dp[9] = BigInteger.valueOf(multiply_by_ln2(Long.parseLong(bmz.substring(63, 64), 2)));
        
        fpz = BigInteger.ZERO; zkd = 0;
        
        for(i = 1; i < 10; i++)
        {
           fpz = fpz .add(dp[i - 1].shiftLeft((64 - (7 * i)) - 25));   
        }
        
        for(int j = 6; j < 10; j++)
        {
               int fp = (64 - (7 * j)) - 25;
               
               String fpb = dp[j - 1].toString(2);
               
            while (fpb.length() < 32) 
            {
                fpb = '0' + fpb;
            }
               
               fpb = fpb.substring(fpb.length() + fp, fpb.length());
               
               while(fpb.length() < 25)
               {
                   fpb = fpb + '0';
               }
               
               zkd = zkd + Integer.parseInt(fpb, 2);
        }
        
        zkd += dp[9].intValue();
        
        Zkd = Integer.toBinaryString(zkd); while (Zkd.length() < 25) { Zkd = Zkd + '0'; }
        
        if(Zkd.length() > 25) { fpz = fpz.add(BigInteger.valueOf(Integer.parseInt(Zkd.substring(0, Zkd.length() - 25), 2))); }
        
        if(Zkd.charAt(Zkd.length() - 25) == '1') { fpz = fpz.add(BigInteger.ONE); }
        
        //x[1] = (x[0].add(y[0])).add((x[0].subtract(y[0])).shiftRight(2 * (int)Q)); 
        //y[1] = (x[0].add(y[0])).subtract((x[0].subtract(y[0])).shiftRight(2 * (int)Q));   
        z[0] = z[0].add(fpz);
                
        String xo = x[0].toString(2);
        String yo = y[0].toString(2);
        String zo = z[0].toString(2);

       // Ex = Eref3 + 11 - leadZero(x[0], 64);
         //Ez = -leadZero(z[0], 63) + 2;
        Ey = Ex + Ez - 1023 + 11 - leadZero(y[0], 64); 
       
        double XO, YO, ZO; System.out.println(i + "," + zo.length());
        
        String EX = Long.toBinaryString(Ex); while(EX.length() < 11) { EX = '0' + EX; }
        String EY = Long.toBinaryString(Ey); while(EY.length() < 11) { EY = '0' + EY; }
        String EZ = Long.toBinaryString(Ez); while(EZ.length() < 11) { EZ = '0' + EZ; }
        
        if(xo.charAt(0) == '-')
        {
           xo = '1' + EX + xo.substring(2, xo.length() > 54 ? 54 : xo.length());
           while(xo.length() < 64) { xo = xo + '0'; }
        }
        else
        {
            xo = '0' + EX + xo.substring(1, xo.length() > 53 ? 53 : xo.length());
            while(xo.length() < 64) { xo = xo + '0'; }
        }
        XO = toDouble(xo);
        
        if(yo.charAt(0) == '-')
        {
           yo = '1' + EY + yo.substring(2, yo.length() > 54 ? 54 : yo.length());
           while(yo.length() < 64) { yo = yo + '0'; }
        }
        else
        {
            yo = '0' + EY + yo.substring(1, yo.length() > 53 ? 53 : yo.length()); 
            while(yo.length() < 64) { yo = yo + '0'; }
        }
        YO = toDouble(yo);
        
        if(zo.charAt(0) == '-')
        {
           zo = '1' + EZ + zo.substring(2, zo.length() > 54 ? 54 : zo.length());
           while(zo.length() < 64) { zo = zo + '0'; }  
        }
        else
        {
            zo = '0' + EZ + zo.substring(1, zo.length() > 53 ? 53 : zo.length()); 
            while(zo.length() < 64) { zo = zo + '0'; }
        }
        ZO = toDouble(zo); //Z[0] = 2 * (Z[0] + (0.5 * Enew * Math.log(2)));
        
        System.out.println(((new BigDecimal(x[0])).divide((BigDecimal.valueOf(2).pow((int)(52 - (Eref3 - 1023)))))) 
                + "," + ((new BigDecimal(y[0])).divide(BigDecimal.valueOf(2).pow((int)(52 - (Eref3 - 1023))))) + "," + 
               ((new BigDecimal(z[0])).divide(BigDecimal.valueOf(2).pow(54))));
        System.out.println(YO);
        System.out.println(Y[0]);
        System.out.println("QC: " + (new BigDecimal(R).multiply(BigDecimal.valueOf(Math.log(2)))).divide(BigDecimal.valueOf(2).pow(64)));
        System.out.println("R: " + (new BigDecimal(fpz)).divide(BigDecimal.valueOf(2).pow(64)).round(MathContext.DECIMAL64).doubleValue());
        System.out.println(R + "," + new BigInteger(bmz, 2));
        System.out.println((multiply_by_pi_div_2((long) 86) / Math.pow(2, 25)) + "," + (86 * Math.PI / 2));
        System.out.println(new BigDecimal(BigInteger.valueOf(2).pow(64)).subtract(new BigDecimal(BigInteger.valueOf(2).pow(64)).multiply(BigDecimal.valueOf(Math.atan(1)))));
        //System.out.println(zK + "," + Enew);
        BigDecimal X0 = new BigDecimal(x[0]); BigDecimal Y0 = new BigDecimal(y[0]);
        //System.out.println((((X0.add(Y0)).subtract((X0.subtract(Y0)).divide(BigDecimal.valueOf(2).pow((int)(2 * Q))))).
                //multiply(BigDecimal.valueOf(2).pow((int)(Q - 1)))).
                //divide(BigDecimal.valueOf(2).pow((int)(52 - (Eref3 - 1023)))));
                                     
        System.out.println(toDouble("0100000001010100011000011001111100010111001101001001010000010001"));
                                     
        //System.out.println((BigDecimal.valueOf(Math.PI).multiply(BigDecimal.valueOf(2).pow(62))).toBigInteger().toString(2));
        System.out.println(new BigDecimal("0.0538885588723374376808616344").multiply(BigDecimal.valueOf(Math.PI/2)));
        System.out.println(new BigDecimal("5236780103785494").divide(BigDecimal.valueOf(Math.pow(2, 41))));
        System.out.println(toDecimal("0100010001100111101000101010110111111000010011010000"));
                                           
        System.out.println(Long.parseLong("0100010011111000000011010010111001110001100001110001101010101111", 2));                              
        System.out.println(new BigDecimal("1353586139796039680").divide(BigDecimal.valueOf((Math.pow(2, 61))), MathContext.DECIMAL64));
        System.out.println(new BigInteger("335689396625921197").divide(BigInteger.valueOf(2).pow(52)));                                   
        System.out.println(Q + "," + 0);
        System.out.println(new BigInteger("4503599627370496").divide(BigInteger.valueOf(2)));
        //Arctan_Table();
        
        for(i = 0; i <55; i++)
        {
            System.out.println(
                  
                    "rotator U" + i + " (clk, rst, m, vm, 6'd" + i + ",z[" + i + "][63], x[" + i + "], y[" + i + "], x[" + (i+1) + 
                    "], y[" + (i+1) + "]);" + "\r\n" +
                    "Z_Path Z" + i + " (clk, rst, m, vm, 6'd" + i + ", x[" + i +"][63], y[" + i + "][63], z[" + i + "], z[" + (i+1) + "]);" + "\r\n" +
                    "always@(posedge clk) begin\r\n" +
                     "Eref[" + (i+1) +"] <= Eref[" + i + "];" + "\r\n" +
                    "sx[" + (i+1) +"] <= sx[" + i + "];" + "\r\n" +
                    "sy[" + (i+1) +"] <= sy[" + i + "];" + "\r\n" +
                    "sz[" + (i+1) +"] <= sz[" + i + "];" + "\r\n"
                    + "end\r\n"
                    
                    );
        }
        
       /* for(i = 19; i <54; i++)
        {
            System.out.println(
                    "assign z[" + (i+1) + "] = z[" + i + "];" + "\r\n" + 
                    "assign Eref[" + (i+1) +"] = Eref[" + i + "];" + "\r\n" +
                    "assign sx[" + (i+1) +"] = sx[" + i + "];" + "\r\n" +
                    "assign sy[" + (i+1) +"] = sy[" + i + "];" + "\r\n" +
                    "assign sz[" + (i+1) +"] = sz[" + i + "];" + "\r\n" +
                    "assign DP[" + (i-19) +"] = (sign[" + i +"] == 0) ? DP[" + (i-20) + "] + (64'b1 <<< (" + (61 - i) + ")) : DP[" + (i-20) +"];" + "\r\n" +
                    "assign DN[" + (i-19) +"] = (sign[" + i +"] == 1) ? DN[" + (i-20) + "] + (64'b1 <<< (" + (61 - i) + ")) : DN[" + (i-20) +"];"+ "\r\n" +
                   "rotator U" + i + " (clk,rst, m, vm, 6'd" + i + ", z[" + i + "]["+ i +"], x[" + i + "], y[" + i + "], x[" + (i+1) + 
                    "], y[" + (i+1) + "], sign[" + i + "]);" + "\r\n\r\n" 
                    );
        }
        
        System.out.println(
                
                    "assign Eref[" + (i+1) +"] = Eref[" + i + "];" + "\r\n" +
                    "assign sx[" + (i+1) +"] = sx[" + i + "];" + "\r\n" +
                    "assign sy[" + (i+1) +"] = sy[" + i + "];" + "\r\n" +
                    "assign sz[" + (i+1) +"] = sz[" + i + "];\r\n" +
                "rotator U" + i + " (clk,rst, m, vm, 6'd" + i + ", z[" + i + "]["+ i +"], x[" + i + "], y[" + i + "], x[" + (i+1) + 
                    "], y[" + (i+1) + "], sign[" + i + "]);" + "\r\n"
                ); */
    }                                      
}
