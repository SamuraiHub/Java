/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GoogleCodeJamContests.Save_The_Universe_Again;

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
      String s = in.next();
      ArrayList<Integer> tt_dmg = new ArrayList<Integer>(s.length());
      ArrayList<Integer> ts_v_fd = new ArrayList<Integer>(s.length());
      int j = get_total_dmg(s, tt_dmg, ts_v_fd); // total damage
      
      if(tt_dmg.get(j) <= n)
          System.out.println("Case #" + i + ": " + 0);
      else
      {
          int right = j;
          int left = 0;
          while(right >= left)
          {
              int mid = (right+left)/2;
              
              if(n == tt_dmg.get(mid))
              {
                  right = mid;
                  break;
              }
              else if(n > tt_dmg.get(mid))
              {
                  left = mid+1;
              }
              else
              {
                  right = mid-1;
              }
          }
          
          if(right == -1)
          {
              System.out.println("Case #" + i + ": IMPOSSIBLE");
          }
          else
          {
            int m = 0;
          
            for(int k = j; k > right; k--)
            {
                m += ts_v_fd.get(k);
            }
            System.out.println("Case #" + i + ": " + m);
          }
      }
    }
  }
  
  public static int get_total_dmg(String s, ArrayList<Integer> tt_dmg, ArrayList<Integer> ts_v_fd)
  {
      int fd = 1; //shoot damage
      tt_dmg.add(0); // total dmg
      ts_v_fd.add(0); // total shoots vs fire damage
      int j = 0; // no. of fire damage increase
       
      for(int i = 0; i < s.length(); i++)
      {
          if(s.charAt(i) == 'C')
          {
             fd *= 2;
             tt_dmg.add(tt_dmg.get(j));
             ts_v_fd.add(0);
             j++;
          }
          else
          {
             tt_dmg.set(j, tt_dmg.get(j) + fd);
             ts_v_fd.set(j, ts_v_fd.get(j)+1);
          }
          
      }
      
      for(int i = j-1; i>=0; i--)
      {
         tt_dmg.set(i, tt_dmg.get(i)+ts_v_fd.get(i+1)*(int)Math.pow(2, i));
         ts_v_fd.set(i,ts_v_fd.get(i)+ts_v_fd.get(i+1));
      }
      
      return j;
  }
  
}
