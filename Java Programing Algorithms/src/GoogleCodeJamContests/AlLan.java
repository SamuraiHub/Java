/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.*;
import java.util.ArrayList;


/**
 *
 * @author Administrator
 */
public class AlLan{


private BufferedReader br;
private BufferedWriter bw;

     /**
     * @param args the command line arguments
     */

  public AlLan(String inFileName, String outFileName){
        try {
        File    inFile = new File(inFileName);
    br = new BufferedReader(new FileReader(inFile));
        File    outFile = new File(outFileName);
     bw = new BufferedWriter(new FileWriter(outFile));



        } catch (Exception e) {

        }
}
public int PatternTest(String s,String[] as)
{
 int j = 0;
  int n = 0;
 ArrayList temp = new ArrayList<String>();

  while(j< s.length())
  {
   if(s.charAt(j) =='(')
   {
    j++;
     if(j>1)
     {
       int  m = j;
        for(int l = 0; l<temp.size(); l++)
     {
    boolean b = false;
     j = m;
     String c = (String)temp.get(l);
     while(s.charAt(j) != ')')
    {
      
      if(c.charAt(n) == s.charAt(j))
      {
      b = true;
      }
      j++;
     }
     if(b == false)
     {
         temp.remove(l);
        l--;
     }
    }
     n++;
     j++;
    }
     else
     {

        while(s.charAt(j) != ')')
    {
     for(int l = 0; l<as.length; l++)
     {
      if(as[l].charAt(n) == s.charAt(j))
      temp.add(as[l]);
     }
    j++;
    }
     n++;
     j++;
     }
   }
   else
   {

      if(j>0)
     {
       String t = "";
       int m = n;
          while(j<s.length())
     {
        if(s.charAt(j) !='(')
        {
       t = t+s.charAt(j);
        j++;
        n++;
        }
        else
        break;
       }
     
      for(int l =0; l<temp.size(); l++)
      {
         String c= (String) temp.get(l);
         if(!c.substring(m, n).equals(t))
         {
         temp.remove(l);
         l--;
         }
      } 
      
     }
      else
      {
        String t = "";
       while(j<s.length())
     {
        if(s.charAt(j) !='(')
        {
       t = t+s.charAt(j);
        j++;
        n++;
        }
        else
        break;
       }
          for(int l = 0; l<as.length; l++)
      {
       if(as[l].substring(0, n).equals(t))
       {
        temp.add(as[l]);     
       }    
      }
     }
   }
  }
  return temp.size();
}
public void NumberOfMatchingwords()
{
 try
 {

String l1 = br.readLine();
String[] NOI = l1.split(" ");
int f = Integer.parseInt(NOI[1]);
String[] ALW = new String[f];
for(int i = 0; i<f; i++)
{
 ALW[i] = br.readLine();
}

f = Integer.parseInt(NOI[2]);

for(int i = 0; i<f; i++)
{
  
  l1 = br.readLine();
 int k = PatternTest(l1,ALW);

bw.write("Case #"+(i+1)+": "+k);
bw.newLine();
}
 
 br.close();
 bw.close();
 }
 catch (Exception e)
 {
  
  e.printStackTrace();
 }

}
    public static void main(String[] args) {
        // TODO code application logic here

        AlLan AL1 = new AlLan("H:/Muaz/Java Programing Algorithms/test/GoogleCodeJamContests/AlienLanguage/A-large-practice.in",
        "H:/Muaz/Java Programing Algorithms/test/GoogleCodeJamContests/AlienLanguage/A-LargeOP.in");
        AL1.NumberOfMatchingwords();

      
    }

}