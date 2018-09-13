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
public class scaleFactor {

    /**
     * @param args the command line arguments
     */ public static int v = 0;
    
    public static void sfs(double kp, int i, int j, String sg)
    {
        if(i == j )
        {
            String sf = Integer.toBinaryString((int) (kp * Math.pow(2, 27)));
            
            while(sf.length() < 32) { sf = '0' + sf; }
            
            System.out.println("9'b"+ sg + ": HSFactor2 = 32'b" + sf + ";");
            
            return;
        }
        
        for(int s = 0; s < 5; s++)
        {
                double k = kp/(Math.sqrt(1-(Math.pow(s, 2) * Math.pow(8, (-2 * i)))));
                
                String sb = Integer.toBinaryString(s); if(sb.length() == 1) { sb = "00" + sb; } 
                if(sb.length() == 2) { sb = "0" + sb; }
  
                sfs(k, i+1, j, sg + sb);          
        }
    }
    
    
    public static void main(String[] args) {
        // TODO code application logic here
        
        //sfs(1.0, 4, 7, "");
        
        //System.out.println(toDouble("1100000001010111110101000110001000100011101110101011000101011000"));
        
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
                                            
        double[] X = new double[2]; X[0] = (1.2074970677630725*Math.pow(2, -16))+(1.2074970677630725);
        double[] Y = new double[2]; Y[0] = -(1.2074970677630725*Math.pow(2, -16))+(1.2074970677630725);
        double[] z = new double[2];
        X[0] = X[0]; Y[0] = Y[0];
        System.out.println(X[0]);
        Random r = new Random();

       z[0] = 5.65;//r.nextDouble() * Math.PI * 0.5;
       //0.36939505782351084;//1.03822307498423;//9.157560027571627E-4;
       z[1] = 0.1048225555204378; 
       
        String zb = Long.toBinaryString((long) (z[1] * Math.pow(2, 61)));
        
        while(zb.length() < 63) { zb = '0' + zb; }
        String zb1 = zb;
       int s = zb.charAt(1) == '0' ? 1 : -1; 
        
       System.out.println("X0: "+zb);
       //System.out.println(resize(Long.toBinaryString((long) (X[0] * Math.pow(2, 52))), 64));
       boolean t = false;
       for(int j = 1; j < marh.length; j++)
       {
        
        double x = 1; double y = s*Math.pow(2, -marh[j][marh[j].length-1]); double x2, y2;
        
        //X[1] = X[0] + (s*Y[0]*Math.pow(2, -marh[j][0]));
        //Y[1] = Y[0] + (s*X[0]*Math.pow(2, -marh[j][0]));
        //X[0] = X[1]; Y[0] = Y[1];
        
        for(int i = 1; i < marh[j].length; i++)
        {
            x2 = x + (s*Math.pow(2,-marh[j][i]) * y);
            y2 = y + (s * Math.pow(2,-marh[j][i]) * x);
            x = x2; y = y2;
           //X[1] = X[0] - (s*Y[0]*Math.pow(2, -marh[j][i]));
           //Y[1] = Y[0] - (s*X[0]*Math.pow(2, -marh[j][i]));
           //X[0] = X[1]; Y[0] = Y[1];
        }
        
        //System.out.println("x: "+x);
        //System.out.println("y: "+y);
        long XB = (long) (x * Math.pow(2, 32));
        long YB = (long) (y * Math.pow(2, 32));
        //System.out.println("xb: "+resize(Long.toBinaryString(XB),33));
        //System.out.println("yb: "+resize(Long.toBinaryString(YB),33));
        
        X[1] = X[0] + (s*Y[0]*Math.pow(2, -j));
        Y[1] = Y[0] + (s*X[0]*Math.pow(2, -j));
        X[0] = X[1]; Y[0] = Y[1];
        //X[1] = (X[0]*x) + (Y[0]*y);
        //Y[1] = (Y[0]*x) + (X[0]*y);
        //X[0] = X[1]; Y[0] = Y[1];
        //System.out.println(X[0]);
        z[1] = z[1] - (s * atanh(Math.pow(2, -j)));//- (s * error(marh[j], true));
        
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
        
         s = zb.charAt(1) == '0' ? 1 : -1; 
         
         System.out.println("X0: "+zb);
         //System.out.println(resize(Long.toBinaryString((long) (X[1] * Math.pow(2, 52))), 64));
          if(j == 4 && !t)
          {
              j--;
              t = true;
          }
       }
       t = false;
       for(int i = 7; i < 19; i++)
       {
         X[1] = X[0] + (s*Y[0]*Math.pow(2, -i));
         Y[1] = Y[0] + (s*X[0]*Math.pow(2, -i));
         X[0] = X[1]; Y[0] = Y[1];
         
         z[1] = z[1] - (s * atanh(Math.pow(2, -i)));
         
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
        
         s = zb.charAt(1) == '0' ? 1 : -1; 

         //System.out.println("X0: "+X[0]);
         //System.out.println(resize(Long.toBinaryString((long) (X[0] * Math.pow(2, 52))), 64));
         System.out.println("X0: "+zb);
         
         if(i == 13 && !t)
          {
              i--;
              t = true;
          }
       }
       s = zb.charAt(18) == '0' ? 1 : -1;
       System.out.println("X0: "+zb);
       //System.out.println(resize(Long.toBinaryString((long) (X[1] * Math.pow(2, 52))), 64));
      
       for(int i = 18; i < 27; i++)
       {
         X[1] = X[0] + (s*Y[0]*Math.pow(2, -i));
         Y[1] = Y[0] + (s*X[0]*Math.pow(2, -i));
         X[0] = X[1]; Y[0] = Y[1];
         
         z[1] = z[1] - (s * (Math.pow(2, -i)));
         s = zb.charAt(i+1) == '0' ? -1 : 1;
         
         
         System.out.println("X0: "+z[1]);
       }
       
       t = false;
       for(int i = 27; i < 55; i++)
       {  
         X[1] = X[1] + (s*Y[0]*Math.pow(2, -i));
         Y[1] = Y[1] + (s*X[0]*Math.pow(2, -i));
         
         z[1] = z[1] - (s * (Math.pow(2, -i)));
         s = zb.charAt(i+1) == '0' ? -1 : 1;
         System.out.println("X0: "+z[1]);
         
         if(i == 40 && !t)
          {
              i--;
              t = true;
          }
       }
       X[0] = X[1];
       Y[0] = Y[1];
       System.out.println("z: "+z[0]);
       System.out.println((X[0]+Y[0])*Math.pow(2,7));
       System.out.println(Math.exp(5.65));
       System.out.println(Math.exp(5.65) - ((X[0]+Y[0])*Math.pow(2,7)));
       
       for(int i = 1; i < 40; i++)
       {
           System.out.println(
                   
                   "rotator UX"+i+" (clk, rst, m, vm, 6'd0,Z[62], x["+(54+i)+"], y["+(54+i)+"], x["+(55+i)+"], y["+(55+i)+"]);\r\n"+
                   "always@(posedge clk) begin\r\n"+
                   "Eref["+(55+i)+"] <= Eref["+(54+i)+"];\r\n"+
                   "sx["+(55+i)+"] <= sx["+(54+i)+"];\r\n"+
                   "sy["+(55+i)+"] <= sy["+(54+i)+"];\r\n"+
                   "sz["+(55+i)+"] <= sz["+(54+i)+"];\r\n"+
                   "z["+(55+i)+"] <= z["+(54+i)+"];\r\n"+
                   "end"
                   );
                   
                   System.out.println();
       }
       
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

    private static double toDouble(String binary) {
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
        System.out.println(exp);
        return d * Math.pow(2, exp);
    }
    
    public static String resize(String s, int length) {
        while (s.length() < length) {
            s = '0' + s;
        }
        return s;
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
