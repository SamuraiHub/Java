/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GoogleCodeJamContests.Cubic_UFOC;

/**
 *
 * @author Muaz Aljarhi
 */
import java.util.*;
import java.io.*;
public class Solution {
  public static void main(String[] args) {
    Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
    int t = in.nextInt(); // Scanner has functions to read ints, longs, strings, chars, etc.
    for (int i = 1; i <= t; ++i) {
      double A = in.nextDouble();
      A = A * 2;
      System.out.println(A);
      double theta = Math.sin(Math.asin(Math.pow(A, 2)-1)/2)*0.5;
      System.out.println(theta);
      System.out.println("Case #"+i+":");
      System.out.println(theta+" "+theta+" "+0);
      System.out.println(-theta+" "+theta+" "+0);
      System.out.println("0 0 0.5");
      
    }
  }
}