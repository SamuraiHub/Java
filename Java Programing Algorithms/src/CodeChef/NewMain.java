/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CodeChef;

import java.util.LinkedList;

/**
 *
 * @author Muaz
 */
public class NewMain 
{

public static void main (String[] args) throws java.lang.Exception
{
java.io.BufferedReader r = new java.io.BufferedReader
(new java.io.InputStreamReader (System.in));
int t=Integer.parseInt(r.readLine());

for(int i = 0; i < t; i++)
{
    String C = r.readLine(), s = "";;
    String[] cs = C.split(" ");
    int n = Integer.parseInt(cs[0]),
        k = Integer.parseInt(cs[1]),
        m = Integer.parseInt(cs[2]),
            o;
    
    int c = factorial(n)/(factorial(k) * factorial(n-k));
    
    if(m > c)
    {
        System.out.println(-1);
    }
    else
    {
        for(int b = 0; b < k; b++)
        {
            s = s + "1";
        }
        
        o = Integer.parseInt(s, 2);
          
        for(int j = 1; j < m; j++)
        {
            o = nextUpper(o);
        }
        s = Integer.toBinaryString(o);
        
        for(int a = s.length(); a < n; a++) //5 9
        {
            s = "0" + s;
        }
    }
   
    System.out.println(s);
}
}

public static int nextUpper(int n) 
{
   int bc = Integer.bitCount(n);
   
   for (int i = n+1; i < 351; i++)
   {
      if (Integer.bitCount(i) == bc)
      {
        return i;
      }
   }
   
   throw new RuntimeException(n+" is the highest with a bit count of "+bc);
}

public static int factorial(int n)
{
    int f = 1;
    
   for(int i = 2; i <= n; i++)
   {
       f = f * i;
   }
       
    return f;
}


}