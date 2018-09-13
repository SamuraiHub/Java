import java.util.*;

public class Knapsack {
    static int optimalWeight(int W, int[] w) {
        //write you code here
        int[] result = new int[W+1];
        
        for (int i = 0; i < w.length; i++) 
        {
            for (int j = W; j >= w[i]; j--) 
            {
                int wr = result[j-w[i]] + w[i];
                
                if (wr > result[j]) 
                {
                    result[j] = wr;
                }
            }
        }
        return result[W];
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int W, n;
        W = scanner.nextInt();
        n = scanner.nextInt();
        int[] w = new int[n];
        for (int i = 0; i < n; i++) {
            w[i] = scanner.nextInt();
        }
        System.out.println(optimalWeight(W, w));
    }
}

