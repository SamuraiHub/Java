import java.util.*;

public class DifferentSummands {
    private static List<Integer> optimalSummands(int n) {
        List<Integer> summands = new ArrayList<Integer>((int) Math.sqrt(n));
        //write your code here
        int j = 0; int i = 1;
        while(j <= n)
        {
            summands.add(i);
            j = j+i;
            i++;
        }
        if(j > n)
        {
            int r = j-n-1;
            summands.remove(r);
        }
        
        return summands;
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        List<Integer> summands = optimalSummands(n);
        System.out.println(summands.size());
        for (Integer summand : summands) {
            System.out.print(summand + " ");
        }
    }
}

