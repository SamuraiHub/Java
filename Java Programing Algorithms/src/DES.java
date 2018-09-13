
import java.io.*;


/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Aljarhi
 */
public class DES {

     public String encrypt(String s, String key)
    {
        String[] PK = permuteKey(key);

        byte[] b = s.getBytes();

        String K = "";

          for (byte j : b)  
  {
     int val = j;

          String sb = Integer.toBinaryString(val);

     for(int i = 0; i<(8-sb.length()); i++)
     {
         K = K + 0;
     }

     K = K + sb;

  }

   System.out.println("Text " + "'" + s + "' to binary: " + K);
         
 int[] IP = {  58,    50,   42,    34,    26,   18,    10,    2,
               60,    52,   44,    36,    28,   20,    12,    4,
               62,    54,   46,    38,    30,   22,    14,    6,
               64,    56,   48,    40,    32,   24,    16,    8,
               57,    49,   41,    33,    25,   17,     9,    1,
               59,    51,   43,    35,    27,   19,    11,    3,
               61,    53,   45,    37,    29,   21,    13,    5,
               63,    55,   47,    39,    31,   23,    15,    7 };

     String SP = "";

   for(int i = 0; i < 64; i++)
   {
       SP = SP + K.charAt(IP[i]-1);
   }

     System.out.println("Initial Txt Permutation: " + SP);

     String L = SP.substring(0, SP.length()/2);

     System.out.println("Left Half: " + L);

     String R = SP.substring(SP.length()/2);

     System.out.println("Right Half: " + R);

    int[] E = {  32,     1,    2,     3,     4,    5,
                  4,     5,    6,     7,     8,    9,
                  8,     9,   10,    11,    12,   13,
                 12,    13,   14,    15,    16,   17,
                 16,    17,   18,    19,    20,   21,
                 20,    21,   22,    23,    24,   25,
                 24,    25,   26,    27,    28,   29,
                 28,    29,   30,    31,    32,    1 };

 int[] S1 = {     14,  4,  13,  1,   2, 15,  11,  8,   3, 10,   6, 12,   5,  9,   0,  7,
                   0, 15,   7,  4,  14,  2,  13,  1,  10,  6,  12, 11,   9,  5,   3,  8,
                   4,  1,  14,  8,  13,  6,   2, 11,  15, 12,   9,  7,   3, 10,   5,  0,
                  15, 12,   8,  2,   4,  9,   1,  7,   5, 11,   3, 14,  10,  0,   6, 13 };

 int[] S2 = { 15,  1,   8, 14, 6, 11,   3,  4,   9,  7,   2, 13,  12,  0,   5, 10,
      3, 13,   4,  7,  15,  2,   8, 14,  12,  0,   1, 10,   6,  9,  11,  5,
      0, 14,   7, 11,  10,  4,  13,  1,   5,  8,  12,  6,   9,  3,   2, 15,
     13,  8,  10,  1,   3, 15,   4,  2,  11,  6,   7, 12,   0,  5,  14,  9 };

  int[] S3 = {  10,  0,  9, 14,   6,  3,  15,  5,   1, 13,  12,  7,  11,  4,   2,  8,
     13,  7,   0,  9,   3,  4,   6, 10,   2,  8,   5, 14,  12, 11,  15,  1,
     13,  6,   4,  9,   8, 15,   3,  0,  11,  1,   2, 12,   5, 10,  14,  7,
      1, 10,  13,  0,   6,  9,   8,  7,   4, 15,  14,  3,  11,  5,   2, 12 };

 int[] S4 = {  7, 13,  14,  3,   0,  6,   9, 10,   1,  2,   8,  5,  11, 12,   4, 15,
     13,  8,  11,  5,   6, 15,   0,  3,   4,  7,   2, 12,   1, 10,  14,  9,
     10,  6,   9,  0,  12, 11,   7, 13,  15,  1,   3, 14,   5,  2,   8,  4,
      3, 15,   0,  6,  10,  1,  13,  8,   9,  4,   5, 11,  12,  7,   2, 14 };

 int[] S5 = {   2, 12,   4,  1,   7, 10,  11,  6,   8,  5,   3, 15,  13,  0,  14,  9,
     14, 11,   2, 12,   4,  7,  13,  1,   5,  0,  15, 10,   3,  9,   8,  6,
      4,  2,   1, 11,  10, 13,   7,  8,  15,  9,  12,  5,   6,  3,   0, 14,
     11,  8,  12,  7,   1, 14,   2, 13,   6, 15,   0,  9,  10,  4,   5,  3 };


int[] S6 = { 12,  1,  10, 15,   9,  2,   6,  8,  0, 13,   3,  4,  14,  7,   5, 11,
     10, 15,   4,  2,   7, 12,   9,  5,   6,  1,  13, 14,   0, 11,   3,  8,
      9, 14,  15,  5,   2,  8,  12,  3,   7,  0,   4, 10,   1, 13,  11,  6,
      4,  3,   2, 12,   9,  5,  15, 10,  11, 14,   1,  7,   6,  0,   8, 13 };

int[] S7 = {    4, 11,   2, 14,  15,  0,   8, 13,   3, 12,   9,  7,   5, 10,   6,  1,
     13,  0,  11,  7,   4,  9,   1, 10,  14,  3,  5, 12,   2, 15,   8,  6,
      1,  4,  11, 13,  12,  3,   7, 14,  10, 15,  6,  8,   0,  5,   9,  2,
      6, 11,  13,  8,   1,  4,  10,  7,   9,  5,  0, 15,  14,  2,   3, 12 };

int[] S8 = {   13,  2,   8,  4,   6, 15,  11,  1,  10,  9,   3, 14,   5,  0,  12,  7,
      1, 15,  13,  8,  10,  3,   7,  4,  12,  5,   6, 11,   0, 14,   9,  2,
      7, 11,   4,  1,   9, 12,  14,  2,   0,  6,  10, 13,  15,  3,   5,  8,
      2,  1,  14,  7,   4, 10,   8, 13,  15, 12,   9,  0,   3,  5,   6, 11 };

int[][] S = {S1, S2, S3, S4, S5, S6, S7, S8};

      for(int i = 0; i<16; i++)
      {
          String ER = "";

   for(int j = 0; j < 48; j++)
   {
       ER = ER + R.charAt(E[j]-1);
   }
          
      if(i == 0)
      System.out.println("Expanded Right half: " + ER);


     String KER = "";

     for(int j = 0; j < 48; j++)
     {
         if((ER.charAt(j) == '0' && PK[i].charAt(j) == '0') || (ER.charAt(j) == '1' && PK[i].charAt(j) == '1'))
         {
            KER = KER + 0;
         }
         else
         KER = KER + 1;
     }

     if(i == 0)
     System.out.println("XOR result: " + KER);

     String KSB = "";

        for(int j = 0; j<8; j ++)
        {
          int r = Integer.valueOf((KER.substring((j*6),((j+1)*6))).substring(0,1)+(KER.substring((j*6),((j+1)*6))).charAt(5), 2);
          int c = Integer.valueOf((KER.substring((j*6),((j+1)*6))).substring(1,5), 2);

          String bs = Integer.toBinaryString(S[j][(r*16)+c]);

          for(int k=0; k<(4-bs.length()); k++)
          {
              KSB = KSB + 0;
          }

          KSB = KSB + bs;

           if(i == 0)  // format: 111100: row:2 clm:14 Sbox1-> 5 ->0101
           System.out.println(KER.substring((j*6),((j+1)*6)) + ": " + "row:" + r + " clm:" + c + " Sbox"+ (j+1) + "->:" +
                   Integer.valueOf(KSB.substring(KSB.length()-4), 2) + "->" + KSB.substring(KSB.length()-4));

        }

        int[] P = {      16,   7,  20,  21,
                         29,  12,  28,  17,
                          1,  15,  23,  26,
                          5,  18,  31,  10,
                          2,   8,  24,  14,
                         32,  27,   3,   9,
                         19,  13,  30,   6,
                         22,  11,   4,  25 };

      String KF = "";

        for(int j = 0; j <  32; j++)
        {
          KF = KF + KSB.charAt(P[j]-1);
        }

      if(i == 0)
       System.out.println("SBOX PRM: " + KF);

      String R1 = "";

      for(int j = 0; j < 32; j++)
     {
         if((L.charAt(j) == '0' && KF.charAt(j) == '0') || (L.charAt(j) == '1' && KF.charAt(j) == '1'))
         {
            R1 = R1 + 0;
         }
         else
         R1 = R1 + 1;
     }
      L = R; R =R1;

      if(i == 0)
       System.out.println("Right Half Result: " + R1);
     
        }

       System.out.println("Final Left Half: " + L);
       System.out.println("Final Right Half: " + R);

    String RV = R + L;

    System.out.println("Message: " + RV);

   int[] IP1 = { 40,     8,   48,    16,    56,   24,    64,   32,
                  39,     7,   47,    15,    55,   23,    63,   31,
                  38,     6,   46,    14,    54,   22,    62,   30,
                  37,     5,   45,    13,    53,   21,    61,   29,
                  36,     4,   44,    12,    52,   20,    60,   28,
                  35,     3,   43,    11,    51,   19,    59,   27,
                  34,     2,   42,    10,    50,   18,    58,   26,
                  33,     1,   41,     9,    49,   17,    57,   25 };

      String C = "";

        for(int j = 0; j <  64; j++)
        {
          C = C + RV.charAt(IP1[j]-1);
        }

      System.out.println("Encrypted message: " + C);

     return C;
    }



     public String decrypt(String s, String key)
    {
              String[] PK = permuteKey(key);


 int[] IP = {  58,    50,   42,    34,    26,   18,    10,    2,
               60,    52,   44,    36,    28,   20,    12,    4,
               62,    54,   46,    38,    30,   22,    14,    6,
               64,    56,   48,    40,    32,   24,    16,    8,
               57,    49,   41,    33,    25,   17,     9,    1,
               59,    51,   43,    35,    27,   19,    11,    3,
               61,    53,   45,    37,    29,   21,    13,    5,
               63,    55,   47,    39,    31,   23,    15,    7 };

     String SP = "";

   for(int i = 0; i < 64; i++)
   {
       SP = SP + s.charAt(IP[i]-1);
   }

     String L = SP.substring(0, SP.length()/2);

     String R = SP.substring(SP.length()/2);

    int[] E = {  32,     1,    2,     3,     4,    5,
                  4,     5,    6,     7,     8,    9,
                  8,     9,   10,    11,    12,   13,
                 12,    13,   14,    15,    16,   17,
                 16,    17,   18,    19,    20,   21,
                 20,    21,   22,    23,    24,   25,
                 24,    25,   26,    27,    28,   29,
                 28,    29,   30,    31,    32,    1 };

 int[] S1 = {     14,  4,  13,  1,   2, 15,  11,  8,   3, 10,   6, 12,   5,  9,   0,  7,
                   0, 15,   7,  4,  14,  2,  13,  1,  10,  6,  12, 11,   9,  5,   3,  8,
                   4,  1,  14,  8,  13,  6,   2, 11,  15, 12,   9,  7,   3, 10,   5,  0,
                  15, 12,   8,  2,   4,  9,   1,  7,   5, 11,   3, 14,  10,  0,   6, 13 };

 int[] S2 = { 15,  1,   8, 14, 6, 11,   3,  4,   9,  7,   2, 13,  12,  0,   5, 10,
      3, 13,   4,  7,  15,  2,   8, 14,  12,  0,   1, 10,   6,  9,  11,  5,
      0, 14,   7, 11,  10,  4,  13,  1,   5,  8,  12,  6,   9,  3,   2, 15,
     13,  8,  10,  1,   3, 15,   4,  2,  11,  6,   7, 12,   0,  5,  14,  9 };

  int[] S3 = {  10,  0,  9, 14,   6,  3,  15,  5,   1, 13,  12,  7,  11,  4,   2,  8,
     13,  7,   0,  9,   3,  4,   6, 10,   2,  8,   5, 14,  12, 11,  15,  1,
     13,  6,   4,  9,   8, 15,   3,  0,  11,  1,   2, 12,   5, 10,  14,  7,
      1, 10,  13,  0,   6,  9,   8,  7,   4, 15,  14,  3,  11,  5,   2, 12 };

 int[] S4 = {  7, 13,  14,  3,   0,  6,   9, 10,   1,  2,   8,  5,  11, 12,   4, 15,
     13,  8,  11,  5,   6, 15,   0,  3,   4,  7,   2, 12,   1, 10,  14,  9,
     10,  6,   9,  0,  12, 11,   7, 13,  15,  1,   3, 14,   5,  2,   8,  4,
      3, 15,   0,  6,  10,  1,  13,  8,   9,  4,   5, 11,  12,  7,   2, 14 };

 int[] S5 = {   2, 12,   4,  1,   7, 10,  11,  6,   8,  5,   3, 15,  13,  0,  14,  9,
     14, 11,   2, 12,   4,  7,  13,  1,   5,  0,  15, 10,   3,  9,   8,  6,
      4,  2,   1, 11,  10, 13,   7,  8,  15,  9,  12,  5,   6,  3,   0, 14,
     11,  8,  12,  7,   1, 14,   2, 13,   6, 15,   0,  9,  10,  4,   5,  3 };


int[] S6 = { 12,  1,  10, 15,   9,  2,   6,  8,  0, 13,   3,  4,  14,  7,   5, 11,
     10, 15,   4,  2,   7, 12,   9,  5,   6,  1,  13, 14,   0, 11,   3,  8,
      9, 14,  15,  5,   2,  8,  12,  3,   7,  0,   4, 10,   1, 13,  11,  6,
      4,  3,   2, 12,   9,  5,  15, 10,  11, 14,   1,  7,   6,  0,   8, 13 };

int[] S7 = {    4, 11,   2, 14,  15,  0,   8, 13,   3, 12,   9,  7,   5, 10,   6,  1,
     13,  0,  11,  7,   4,  9,   1, 10,  14,  3,  5, 12,   2, 15,   8,  6,
      1,  4,  11, 13,  12,  3,   7, 14,  10, 15,  6,  8,   0,  5,   9,  2,
      6, 11,  13,  8,   1,  4,  10,  7,   9,  5,  0, 15,  14,  2,   3, 12 };

int[] S8 = {   13,  2,   8,  4,   6, 15,  11,  1,  10,  9,   3, 14,   5,  0,  12,  7,
      1, 15,  13,  8,  10,  3,   7,  4,  12,  5,   6, 11,   0, 14,   9,  2,
      7, 11,   4,  1,   9, 12,  14,  2,   0,  6,  10, 13,  15,  3,   5,  8,
      2,  1,  14,  7,   4, 10,   8, 13,  15, 12,   9,  0,   3,  5,   6, 11 };

int[][] S = {S1, S2, S3, S4, S5, S6, S7, S8};

      for(int i = 0; i<16; i++)
      {
          String ER = "";

   for(int j = 0; j < 48; j++)
   {
       ER = ER + R.charAt(E[j]-1);
   }

     String KER = "";

     for(int j = 0; j < 48; j++)
     {
         if((ER.charAt(j) == '0' && PK[15-i].charAt(j) == '0') || (ER.charAt(j) == '1' && PK[15-i].charAt(j) == '1'))
         {
            KER = KER + 0;
         }
         else
         KER = KER + 1;
     }

     String KSB = "";

        for(int j = 0; j<8; j ++)
        {
          int r = Integer.valueOf((KER.substring((j*6),((j+1)*6))).substring(0,1)+(KER.substring((j*6),((j+1)*6))).charAt(5), 2);
          int c = Integer.valueOf((KER.substring((j*6),((j+1)*6))).substring(1,5), 2);

              String bs = Integer.toBinaryString(S[j][(r*16)+c]);

          for(int k=0; k<(4-bs.length()); k++)
          {
              KSB = KSB + 0;
          }

          KSB = KSB + bs;

        }

        int[] P = {      16,   7,  20,  21,
                         29,  12,  28,  17,
                          1,  15,  23,  26,
                          5,  18,  31,  10,
                          2,   8,  24,  14,
                         32,  27,   3,   9,
                         19,  13,  30,   6,
                         22,  11,   4,  25 };

      String KF = "";

        for(int j = 0; j <  32; j++)
        {
          KF = KF + KSB.charAt(P[j]-1);
        }

      String R1 = "";

      for(int j = 0; j < 32; j++)
     {
         if((L.charAt(j) == '0' && KF.charAt(j) == '0') || (L.charAt(j) == '1' && KF.charAt(j) == '1'))
         {
            R1 = R1 + 0;
         }
         else
         R1 = R1 + 1;
     }
      L = R; R =R1;

        }

    String RV = R + L;

   int[] IP1 = { 40,     8,   48,    16,    56,   24,    64,   32,
                  39,     7,   47,    15,    55,   23,    63,   31,
                  38,     6,   46,    14,    54,   22,    62,   30,
                  37,     5,   45,    13,    53,   21,    61,   29,
                  36,     4,   44,    12,    52,   20,    60,   28,
                  35,     3,   43,    11,    51,   19,    59,   27,
                  34,     2,   42,    10,    50,   18,    58,   26,
                  33,     1,   41,     9,    49,   17,    57,   25 };

      String C = "";

        for(int j = 0; j <  64; j++)
        {
          C = C + RV.charAt(IP1[j]-1);
        }

        byte[] b = new byte[C.length()/8];

      for(int j = 0; j<(C.length()/8); j++)
      {
         int val = Integer.valueOf(C.substring(j*8, (j+1)*8), 2);

         byte byt = (byte) val;

         b[j] = byt;
      }

     String M = new String(b);

     return M;

    }

    private String[] permuteKey(String key)
    {
                byte[] b = key.getBytes();

        String K = "";

          for (byte j : b)
  {
     int val = j;
     
     String sb = Integer.toBinaryString(val);

     for(int i = 0; i<(8-sb.length()); i++)
     {
         K = K + 0;
     }

     K = K + sb;
  }
     System.out.println("Key " + "'" + key + "' to binary: " + K);

int[] PC1 = { 57,   49,   41,    33,    25,    17,   9,
               1,   58,    50,   42,    34,    26,  18,
              10,    2,    59,   51,    43,    35,   27,
              19,   11,     3,   60,    52,    44,   36,
              63,   55,    47,   39,    31,    23,   15,
               7,   62,    54,   46,    38,    30,   22,
              14,    6,    61,   53,    45,    37,   29,
              21,   13,     5,   28,    20,    12,    4 };

   String KP = "";

   for(int i = 0; i < 56; i++)
   {
       KP = KP + K.charAt(PC1[i]-1);
   }

   System.out.println("Permuted Key: " + KP);

   String [] C = new String[17];
   String [] D = new String[17];

      C[0] = KP.substring(0, KP.length()/2);

      System.out.println("Left Key: " + C[0]);

      D[0] = KP.substring(KP.length()/2);

      System.out.println("Right Key: " + D[0]);

   int[] NLS = {1, 1, 2, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 1};

   for(int i = 1; i <= 16; i++)
   {
     C[i] = C[i-1].substring(NLS[i-1]) + C[i-1].substring(0, NLS[i-1]);
     D[i] = D[i-1].substring(NLS[i-1]) + D[i-1].substring(0, NLS[i-1]);
   }

   System.out.println("Shifted Left Key: " + C[1]);

   System.out.println("Shifted Right Key: " + D[1]);

   int[] PC2 = { 14,    17,   11,    24,     1,    5,
                  3,    28,   15,     6,    21,   10,
                 23,    19,   12,     4,    26,    8,
                 16,     7,   27,    20,    13,    2,
                 41,    52,   31,    37,    47,   55,
                 30,    40,   51,    45,    33,   48,
                 44,    49,   39,    56,    34,   53,
                 46,    42,   50,    36,    29,   32 };

String[] k = new String[16];

   for(int j = 0; j < 16; j++)
   {
       k[j] = "";

       String lr  = C[j+1]+D[j+1];

   for(int i = 0; i < 48; i++)
   {
       k[j] = k[j] + lr.charAt(PC2[i]-1);
   }
   }

   System.out.println("Contracted Key: " + k[0]);

     return k;
    }


 public static void main(String[] args) throws IOException {

   DES D = new DES();

   String ct = D.encrypt("Helloppl", "ByePplll");

   System.out.println("Original text: "+D.decrypt(ct, "ByePplll")); 

  }
}
