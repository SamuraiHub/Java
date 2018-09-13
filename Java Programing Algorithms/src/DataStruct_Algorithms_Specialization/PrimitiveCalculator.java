import java.util.*;

public class PrimitiveCalculator {
    private static List<Integer> optimal_sequence(int n) {
        List<Integer> sequence = new ArrayList<Integer>();
        
      
            
        
        int[] mpc = new int[n+1];
        int[] ln = new int[n+1];
        mpc[1] = 0;
        ln[1] = 0; 
        
        for(int i = 2; i <= n; i++)
        {
            mpc[i] = n;
            
            if(i % 3 == 0)
            {
                 mpc[i] = mpc[i/3];
                 ln[i] = i/3;
            }
            
            if(i % 2 == 0)
            {
                if(mpc[i/2] < mpc[i])
                {
                    mpc[i] = mpc[i/2];
                    ln[i] = i/2;
                }
            }
            
            if(mpc[i-1] < mpc[i])
            {
                    mpc[i] = mpc[i-1];
                    ln[i] = i-1;
            }
            
            mpc[i]++;
        }
        
        while (n > 0) {
            sequence.add(n);
            n = ln[n];
        }
        Collections.reverse(sequence);
        return sequence;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        List<Integer> sequence = optimal_sequence(n);
        System.out.println(sequence.size() - 1);
        for (Integer x : sequence) {
            System.out.print(x + " ");
        }
    }
}

