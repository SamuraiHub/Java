/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GoogleCodeJamContests.Trouble_Sort;

/**
 *
 * @author Muaz Aljarhi
 */
import java.util.*;
import java.io.*;
public class Solution {
  public static void main(String[] args) {
    Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
    int t = in.nextInt();  // Scanner has functions to read ints, longs, strings, chars, etc.
    for (int i = 1; i <= t; ++i) {
      int n = in.nextInt();
      int[] a = new int[n];
      for(int j = 0; j < n; j++)
      {
        a[j] = in.nextInt();
      }
        for(int j = 0; j < a.length-2; j++)
        {
            if(a[j] > a[j+2])
            { 
                int temp = a[j+2];
                a[j+2] = a[j];
                a[j] = temp;
            }
          }
        for(int j = a.length-1; j >= 2; j--)
        {
            if(a[j] < a[j-2])
            { 
                int temp = a[j-2];
                a[j-2] = a[j];
                a[j] = temp;
            }
         }
        boolean cs = true;
        for(int j = 0; j < a.length-2; j++)
        {
            if(a[j+1] < a[j] || a[j+1] > a[j+2])
            {
                System.out.println("Case #" + i + ": " + (j+1));
                cs = false;
                break;
            }    
       }
       if(cs)
        System.out.println("Case #" + i + ": OK");
    }
  }
}