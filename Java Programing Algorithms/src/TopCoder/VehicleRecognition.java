/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package TopCoder;

/**
 *
 * @author Al-Jarhi
 */
public class VehicleRecognition {

    
    public static void main(String[] args)
    {
      boolean[] a = new boolean[4000000]; boolean[] b = new boolean[3000000];
      
      System.out.println(a[2]);
      
      long start = System.currentTimeMillis();
      
      for(int i = 0; i < 3000000; i++)
      {
        b[i] = a[i];  
      }
      
      long finish = System.currentTimeMillis();
      
      System.out.println("time: " + (finish-start));
      
      int[] d = new int[1000000]; 
      
      for(int i = 0; i < 1000000; i++)
      {
        d[i] = i%10;  
      }
      
      
      start = System.currentTimeMillis();
     
      int[] c = new int[1000000]; 
      
      for(int i = 0; i < 1000000; i++)
      {
        d[i] = c[i];  
      }
      
      finish = System.currentTimeMillis();
      
      System.out.println("time: " + (finish-start));
    }
}
