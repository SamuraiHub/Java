
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Al-Jarhi
 */
public class TextEncryption {

    /**
     * @param args the command line arguments
     */

    private int[] numKey;

    public String CCEncrypt(String s, String key) throws Exception
    {
        if(s.length() != key.length())
        {
            throw new Error("s and key must be of same length!");
        }

       String cipText = "";

      for(int i=0; i<s.length(); i++)
      {
        int n = Character.digit(s.charAt(i), 10);

         if(key.charAt(i) >= 'a' && key.charAt(i) <= 'z')
       {
             cipText = cipText + (char)(((key.charAt(i)-'a'+n)%26)+'a');
       }
       else
       {
       if(key.charAt(i) >= 'A' && key.charAt(i) <= 'Z')
       {
           cipText = cipText + (char)(((key.charAt(i)-'A'+n)%26)+'A');
       }
       else
       cipText = cipText + ((Character.digit(key.charAt(i), 10)+n)%10);
    }
    }
     return cipText;
    }

    public String CCDecrypt(String s, String key)
    {
        if(s.length() != key.length())
        {
            throw new Error("s and key must be of same length!");
        }

     String orgText = "";

     for(int i=0; i<s.length(); i++)
      {
       if(key.charAt(i) >= 'a' && key.charAt(i) <= 'z')
       {
       orgText = orgText + (((s.charAt(i)-'a')-(key.charAt(i)-'a')+26)%26);
       }
       else
       {
       if(s.charAt(i) >= 'A' && s.charAt(i) <= 'Z')
       {
       orgText = orgText + (((s.charAt(i)-'A')-(key.charAt(i)-'A')+26)%26);
       }
       else
       orgText = orgText + ((Character.digit(s.charAt(i), 10) - Character.digit(key.charAt(i), 10)+10)%10);
      }
    }

    return orgText;
    }

    private void numKey(String key, int enc)
     {

      numKey = new int[key.length()];

      String key1 = key;

      int k = 0;

      while(key1.length() > 0)
     {
       char sm = key1.charAt(0);

       for(int i =1; i<key1.length(); i++)
       {
        if(sm > key1.charAt(i))
        {
          sm = key1.charAt(i);
        }
       }

       for(int j = 0; j<key.length(); j++)
       {
         if(key.charAt(j) == sm)
         {
           if(enc == 1)
           {
           numKey[k] = j;
           }
           else
           {
           numKey[j] = k;
           }
           k++;
           key1 = key1.substring(0, key1.indexOf(key.charAt(j))) + key1.substring(key1.indexOf(key.charAt(j))+1);
         }
       }
       }
     }


     public String CTEncrypt(String s, String key)
     {
  
      String cipText = "";

      int rows = s.length()/key.length();

      if(s.length()%key.length() != 0)
      {
        rows++;
      }

      String[] CTC = new String[rows];

      int rem = key.length() - s.length()%key.length();

      numKey(key, 1);

      for(int i = 0; i<rows-1; i++)
      {
        CTC[i] = s.substring(i*key.length(), (i+1)*key.length());
      }

       CTC[rows-1] = s.substring((rows-1)*key.length(), s.length());

       for(int i = 0; i<rem; i++)
       {
        CTC[rows-1] = CTC[rows-1] + '&';
       }

       for(int i = 0; i<key.length(); i++)
       {
         for(int j = 0; j<rows; j++)
         {
           cipText = cipText + CTC[j].charAt(numKey[i]);
         }
       }
      return cipText;
     }

     public String CTDecrypt(String s, String key)
     {
        String orgText = "";

      int rows = s.length()/key.length();

      if(s.length()%key.length() != 0)
      {
        rows++;
      }

      String[] CTC = new String[key.length()];

      numKey(key, 0);

      for(int i = 0; i<key.length(); i++)
       {
        CTC[i] = s.substring(rows*numKey[i], rows*(numKey[i]+1));
       }

       for(int i = 0; i<rows; i++)
       {
         for(int j = 0; j<key.length(); j++)
         {
           orgText = orgText + CTC[j].charAt(i);
         }
       }
        orgText = orgText.replace('&',' ');

        return orgText;
     }

    public static void main(String[] args) throws Exception {
        // TODO code application logic here

        TextEncryption TE = new TextEncryption();

         System.out.println("\n Enter the Encrypted Text: ");

        BufferedReader Input = new BufferedReader(new InputStreamReader(System.in));

        String inText = Input.readLine();

        System.out.print("Enter key1: ");

        String inKey1 = Input.readLine();

        System.out.print("Enter key2: ");

        String inKey2 = Input.readLine();

        String ct = TE.CTDecrypt(inText, inKey2);

        System.out.println("Decryption of the Encrypted text: " + TE.CCDecrypt(ct, inKey1));
        
    }

}
