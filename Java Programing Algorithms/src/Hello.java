
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.InvalidParameterSpecException;
import java.security.spec.KeySpec;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.crypto.*;
import javax.crypto.spec.*;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Aljarhi
 */
public class Hello {

      static int modulo(int value, int N) {
        while (value >= N) value -= N;
        while (value < 0) value += N;

        return value;
    }


      void count()
    {
     int i = 0; int t = 5, a, b, c, d; boolean[] e = {true,false,true,false}; int[] f = {1, 2, 3}; String[] g = {"nona", "t5e5e", "et54"};
      while(true)
      {
         a = i%4;

         if(f != null)
         {
           e[a] = true;
           a++;
          }
          if(i == 1000000)
          break;

           i++;
           t++;
      }
   
    }

        private static void add(int[] src, int element, int index, int len)
    {
        int h = src[index];
        src[index] = element;
        for(int i = index+1; i <= len; i++)
        {
            int t = src[i];
            src[i] = h;
            h = t;
        }
    }

        private static void add1(int[] k, int i)
    {
        k[i]++;
    }

     public static void main(String[] args) throws Exception
    {
       /* Long start = System.currentTimeMillis();

      for(int j = 0; j < 100; j++)
      {
        (new Hello()).count();
      }

      Long finish = System.currentTimeMillis();

      System.out.println((finish-start)/1000.0);   */
        
        Long start = System.currentTimeMillis();
        
    
SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
    KeySpec spec = new PBEKeySpec("msauammn".toCharArray(), "Kf3eG5mT".getBytes(), 1024, 256);
    SecretKey tmp = factory.generateSecret(spec);
    SecretKey secret = new SecretKeySpec(tmp.getEncoded(), "AES");
    
    Charset charset1 = Charset.forName("ISO8859-1");
CharsetDecoder decoder = charset1.newDecoder();
    Charset charset = Charset.forName("UTF-8");
CharsetEncoder encoder = charset.newEncoder();
   
    Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
    cipher.init(Cipher.ENCRYPT_MODE, secret);
    AlgorithmParameters params = cipher.getParameters();
    byte[] iv = params.getParameterSpec(IvParameterSpec.class).getIV();
     ByteBuffer bbuf = encoder.encode(CharBuffer.wrap("Hey Ya Nub! How u doin?!"));
    byte[] ciphertext = cipher.doFinal(bbuf.array());
    String ct = (decoder.decode(ByteBuffer.wrap(ciphertext))).toString();

    System.out.println(ct + " " + ciphertext.length + " " + ct.length() + " " + ct.getBytes("Unicode").length);
    
 cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
 cipher.init(Cipher.DECRYPT_MODE, secret, new IvParameterSpec(iv));
 
 String plaintext = new String(cipher.doFinal(ct.getBytes(charset1)), "UTF-8");
 System.out.println(plaintext);
 
 Long finish = System.currentTimeMillis();
 
 System.out.println((finish-start)/1000.0);
                 
}
}
