import java.util.*;

public class FibonacciHuge {
    
    private static long getFibonacciHuge(long n, long m) {
        //write your code here
        if(n == 1)
        return 1; 
        
        long fn = 0, fn1 = 1, fn2 = 0;
        
        int[] fb = new int[1000000]; int j = 2;
        fb[0]= 0; fb[1] = 1;
        
        for(int i = 2; ; i++)
        {
            fn = (fn2 + fn1) % m;
            fb[j] = (int) fn; 
            if(fb[j] == 1 && fb[j-1] == 0)
            break;    
    
            j++;
            fn2 = fn1;
            fn1 = fn;
        }

        return fb[(int) (n%(j-1))];
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long n = scanner.nextLong();
        long m = scanner.nextLong();
        System.out.println(getFibonacciHuge(n, m));
    }
}

