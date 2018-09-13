/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Muaz Aljarhi
 */
public class AutoClass {
    
    public static void main(String[] args)
    {
      double[][] M = {{0.0,0.5,0.5},{0.0,0.0,1.0},{1.0,0.0,0.0}};
      double[] v = {1.0,1.0,1.0};
      double[] v1 = new double[3];
      
      for(int i = 0; i < 5; i++)
      {
          for(int j = 0; j < 3; j++)
          {
              v1[j] = 1.00*((M[0][j] * v[0]) + (M[1][j] * v[1]) + (M[2][j] * v[2]));          
          }
          v[0] = v1[0];v[1] = v1[1];v[2] = v1[2];
      }
        
      System.out.println("v[0]: "+(v[0])+", v[1]: "+(v[1])+", v[2]: "+(v[2]));
    }
    
}
