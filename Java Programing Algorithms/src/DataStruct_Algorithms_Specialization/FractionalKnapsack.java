import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class FractionalKnapsack{
    private static double getOptimalValue(int capacity, int[] values, int[] weights) {
        double value = 0;
        //write your code here
        
        int[] v = new int[values.length];
        int[] w = new int[values.length];
        Integer[] n = new Integer[values.length];
        
        for(int i = 0; i < values.length; i++)
        {
            n[i] = i;
        }
        
        WVCompare WV = new WVCompare(weights, values);
        
        Arrays.sort(n,WV);
 
        for(int i = 0; i < values.length; i++)
        {
            v[i] = values[n[i]];
            w[i] = weights[n[i]];
        }
        
        int r = capacity, i = 0;
        
        while(i < values.length && r > 0)
        {  
          if(w[i] <= r)
          {
              r = r  - w[i];
              value = value + v[i];
              i++;
          }
          else
          {
              value = value + ((double) r * v[i])/w[i];
              break; 
          }
        }
        
        return value;
    }
    
    private static class WVCompare implements Comparator<Integer>{
     
    private  final double[] wv; 
  
     
    public WVCompare(int[] weights, int[] values)
    {
        wv = new double[weights.length];
        
        for(int i = 0; i < wv.length; i++)
        {
            wv[i] = ((double) values[i])/weights[i];        
        }
    }

        public int compare(Integer o1, Integer o2) {
        
        return Double.compare(wv[o2],wv[o1]);
    }
    
}

    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int capacity = scanner.nextInt();
        int[] values = new int[n];
        int[] weights = new int[n];
        for (int i = 0; i < n; i++) {
            values[i] = scanner.nextInt();
            weights[i] = scanner.nextInt();
        }
        System.out.println(getOptimalValue(capacity, values, weights));
    }

 
}


