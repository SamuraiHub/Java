/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Administrator
 */
import java.util.*;
import java.math.*;
public class SquareDigits {
 private ArrayList T;
 private int s;
 private int i;

    /**
     * @param args the command line arguments
     */
    public SquareDigits(){
     T = new ArrayList<Integer>();
     s = 0;
     i = 0;
    }
    public void ResultSet(int x){
      
      while(!T.contains(s))
     {
      T.add(s);
      s = (int)Math.pow((x%10),2.0);

      while(x/10 != 0)
      {
        x = x/10;
        s = s + (int)Math.pow((x%10),2.0);
      }
      x = s;
     }
    }

    public int smallestResult(int n){
    
    while(!T.contains(n))
    {
     T.clear();
     ResultSet(i);  
      i++;
    }
     return i-1;
    }

    public static void main(String[] args) {
        // TODO code application logic here
        SquareDigits SD1 = new SquareDigits();
        int sr = SD1.smallestResult(85);
        System.out.println(sr);
    }

}
