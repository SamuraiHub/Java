
import java.io.*;
import java.lang.Integer;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Muaz Al-Jarhi
 */


public class Caesar_Cipher {

    /**
     * @param args the command line arguments
     */

    public Caesar_Cipher(){

    }

    public String encrypt(String s, int key)
    {
       String cipText = "";

      for(int i=0; i<s.length(); i++)
      {
       if(s.charAt(i) >= 'a' && s.charAt(i) <= 'z')
       {
       cipText = cipText + (char)(((s.charAt(i)-'a'+key)%26)+'a');
       }
       else
       {
       if(s.charAt(i) >= 'A' && s.charAt(i) <= 'Z')
       {
       cipText = cipText + (char)(((s.charAt(i)-'A'+key)%26)+'A');
       }
       else
       cipText = cipText + s.charAt(i);
      } 
    }
     return cipText;
    }

    public String decrypt(String s, int key)
    {
     String orgText = "";


     for(int i=0; i<s.length(); i++)
      {
       if(s.charAt(i) >= 'a' && s.charAt(i) <= 'z')
       {
       orgText = orgText + (char)(((s.charAt(i)-'a'-key+26)%26)+'a');
       }
       else
       {
       if(s.charAt(i) >= 'A' && s.charAt(i) <= 'Z')
       {
       orgText = orgText + (char)(((s.charAt(i)-'A'-key+26)%26)+'A');
       }
       else
       orgText = orgText + s.charAt(i);
      }
    }

    return orgText;
    }

       public static void main(String[] args) throws IOException {
        // TODO code application logic here
       
            long startTime = System.nanoTime();
 // ... the code being measured ...
           Math.sin(startTime);
 long estimatedTime = System.nanoTime() - startTime;
           
           System.out.println("t: " + (estimatedTime/(Math.pow(10.0, 9.0))));
           
       /*Caesar_Cipher CC = new Caesar_Cipher();
        
        System.out.print("Caesar Cipher Encryption");
        
        System.out.println("\n Enter the text to be encrypted: ");

        BufferedReader Input = new BufferedReader(new InputStreamReader(System.in));

        String inText = Input.readLine();

        System.out.print("Enter the shift amount: ");

        int inKey = Integer.parseInt(Input.readLine());

        String ct = CC.encrypt(inText, inKey);

        System.out.println("Encrypted text:  " + ct);

        System.out.println("Decryption of the Encrypted text: " + CC.decrypt(ct, inKey)); */

    }

}
