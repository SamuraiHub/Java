import java.util.Scanner;

public class PlacingParentheses {
    private static long getMaximValue(String exp) {
      //write your code here
      int n = (exp.length()+1) >> 1;
      int n2 = n*n;  
      
      long[] max = new long[n2];
      long[] min = new long[n2];
      
      for(int i = 0; i < exp.length(); i=i+2)
      {
          max[i >> 1] = Long.parseLong(exp.charAt(i)+"");
          min[i >> 1] = Long.parseLong(exp.charAt(i)+"");
      }
      
      for(int i =  1; i < n; i++)
      {
        max[n+i] = eval(max[i-1], max[i], exp.charAt(2*i-1));
        min[n+i] = eval(min[i-1], min[i], exp.charAt(2*i-1));
      }
      
      for(int j = 2; j < n; j++)
      {
          for(int i = j; i < n; i++)
          {
              long mx = Long.MIN_VALUE, mn = Long.MAX_VALUE;
              for(int k = 0; k < j; k++)
              {
                  switch (exp.charAt(2*(k+i-j)+1)) {
                      case '+':
                          mx = Math.max(eval(max[k*n+k+i-j],max[(j-k-1)*n+i], exp.charAt(2*(k+i-j)+1)), mx);
                          mn = Math.min(eval(min[k*n+k+i-j],min[(j-k-1)*n+i], exp.charAt(2*(k+i-j)+1)), mn);
                          break;
                      case '-':
                          mx = Math.max(eval(max[k*n+k+i-j],min[(j-k-1)*n+i], exp.charAt(2*(k+i-j)+1)), mx);
                          mn = Math.min(eval(min[k*n+k+i-j],max[(j-k-1)*n+i], exp.charAt(2*(k+i-j)+1)), mn);
                          break;
                      case '*':
                          mx = Math.max(eval(max[k*n+k+i-j],max[(j-k-1)*n+i], exp.charAt(2*(k+i-j)+1)), mx);
                          mx = Math.max(eval(max[k*n+k+i-j],min[(j-k-1)*n+i], exp.charAt(2*(k+i-j)+1)), mx);
                          mx = Math.max(eval(min[k*n+k+i-j],max[(j-k-1)*n+i], exp.charAt(2*(k+i-j)+1)), mx);
                          mx = Math.max(eval(min[k*n+k+i-j],min[(j-k-1)*n+i], exp.charAt(2*(k+i-j)+1)), mx);
                          mn = Math.min(eval(min[k*n+k+i-j],min[(j-k-1)*n+i], exp.charAt(2*(k+i-j)+1)), mn);
                          mn = Math.min(eval(min[k*n+k+i-j],max[(j-k-1)*n+i], exp.charAt(2*(k+i-j)+1)), mn);
                          mn = Math.min(eval(max[k*n+k+i-j],min[(j-k-1)*n+i], exp.charAt(2*(k+i-j)+1)), mn);
                          mn = Math.min(eval(max[k*n+k+i-j],max[(j-k-1)*n+i], exp.charAt(2*(k+i-j)+1)), mn);
                          break;
                      default:
                          assert false;
                          break; 
                  }
              }
              max[j*n+i] = mx; min[j*n+i] = mn;
          }
      }
          
      return max[n2-1];
    }

    private static long eval(long a, long b, char op) {
        switch (op) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
            default:
                assert false;
                return 0;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String exp = scanner.next();
        System.out.println(getMaximValue(exp));
    }
}

