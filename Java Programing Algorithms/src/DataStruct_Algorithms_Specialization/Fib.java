import java.util.Scanner;

public class Fib {
  private static long calc_fib(int n) {
    if (n <= 1)
      return n;
    
    long fn = 0, fn1 = 1, fn2 = 0;
    
    for(int i = 2; i <=n; i++)
    {
        fn = fn2 + fn1;
        fn2 = fn1;
        fn1 = fn;
    }

    return fn;
  }

  public static void main(String args[]) {
    Scanner in = new Scanner(System.in);
    int n = in.nextInt();

    System.out.println(calc_fib(n));
  }
}
