
import java.util.*;
import java.io.*;
import java.math.BigDecimal;
import static java.math.BigDecimal.ROUND_HALF_UP;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;
import static java.math.RoundingMode.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class APlusB {

    
     /**
   * Returns {@code n!}, that is, the product of the first {@code n} positive integers, or {@code 1}
   * if {@code n == 0}.
   *
   * <p><b>Warning:</b> the result takes <i>O(n log n)</i> space, so use cautiously.
   *
   * <p>This uses an efficient binary recursive algorithm to compute the factorial with balanced
   * multiplies. It also removes all the 2s from the intermediate products (shifting them back in at
   * the end).
   *
   * @throws IllegalArgumentException if {@code n < 0}
   */
    
    public static void main(String[] args) throws IOException {

//        BufferedReader rf = new BufferedReader(new FileReader("C:\\Users\\Muaz Aljarhi\\Desktop\\EURUSDmicro60.csv"));
//
//        double c = 0.0, a = 0.0, b = 0.0;
//        int u = 0, d = 0;
//
//        for (String S = rf.readLine(); S != null; S = rf.readLine()) {
//            String[] V = S.split(",");
//
//            double a1 = Double.parseDouble(V[3]);
//            double b1 = Double.parseDouble(V[4]);
//
//            if (a != 0.0) {
//                b = b + Math.max(a1, a) - Math.min(b1, a);
//            }
//
//            a = Double.parseDouble(V[5]);
//            double c1 = (Math.max(a1, a) + Math.min(b1, a)) / 2;
//
//            if (c != 0) {
//                if (c >= c1) {
//                    u++;
//                } else {
//                    d++;
//                }
//            }
//
//            c = c1;
//        }
//        
//        int[][] e = new int[10000][10000];
//        int[] f = new int[100000000];
//        
//        
//        Long t1 = System.nanoTime();
//        for(int i = 0; i < 50; i++)
//        {
//          for(int j = 0; j < e.length; j++)
//          {
//              for(int k = 0; k<e[j].length; k++)
//              {
//                  e[j][k] = e[j][k]+5;
//              }
//          }
//        }
//        Long t2 = System.nanoTime();
//        System.out.println("Time: "+((t2-t1)/1000000000.0)+"s");
//        
//        t1 = System.nanoTime();
//        for(int i = 0; i < 50; i++)
//        {
//            for(int j = 0; j < 10000; j++)
//          {
//             for(int k = 0; k < 10000; k++)
//             {
//                 int m = j*10000+k;
//                 f[m] = f[m]+5;
//             }
//          }
//        }  
//        t2 = System.nanoTime();
//        System.out.println("Time: "+((t2-t1)/1000000000.0)+"s");
//        
//       System.out.println("Size:  "+ Integer.parseInt("0000000000000000000000000000000123"));
        System.out.println('9'-'0');
    }
    
    private static final BigDecimal SQRT_DIG = new BigDecimal(75);
private static final BigDecimal SQRT_PRE = new BigDecimal(10).pow(SQRT_DIG.intValue());

/**
 * Private utility method used to compute the square root of a BigDecimal.
 * 
 * @author Luciano Culacciatti 
 * @url http://www.codeproject.com/Tips/257031/Implementing-SqrtRoot-in-BigDecimal
 */
private static BigDecimal sqrtNewtonRaphson  (BigDecimal c, BigDecimal xn, BigDecimal precision){
    
    BigDecimal currentPrecision = BigDecimal.ONE;
    BigDecimal xn1 = BigDecimal.ZERO;
    
    while(currentPrecision.compareTo(precision) > -1)
    {
        BigDecimal fx = xn.pow(2).add(c.negate());
        BigDecimal fpx = xn.multiply(new BigDecimal(2));
        xn1 = fx.divide(fpx, 2 * SQRT_DIG.intValue(), RoundingMode.HALF_DOWN);
        xn1 = xn.add(xn1.negate());
        BigDecimal currentSquare = xn1.pow(2);
        currentPrecision = currentSquare.subtract(c);
        currentPrecision = currentPrecision.abs();
        xn = xn1;
    }
    return xn1;
}

/**
 * Uses Newton Raphson to compute the square root of a BigDecimal.
 * 
 * @author Luciano Culacciatti 
 * @url http://www.codeproject.com/Tips/257031/Implementing-SqrtRoot-in-BigDecimal
 */
public static BigDecimal bigSqrt(BigDecimal c){
    return sqrtNewtonRaphson(c,new BigDecimal(1),new BigDecimal(1).divide(SQRT_PRE));
}

public static BigDecimal sqrt(BigDecimal A, final int SCALE) {
    BigDecimal x0 = new BigDecimal("0");
    BigDecimal x1 = new BigDecimal(Math.sqrt(A.doubleValue()));
    BigDecimal TWO = new BigDecimal(2);
    while (!x0.equals(x1)) {
        x0 = x1;
        x1 = A.divide(x0, SCALE, ROUND_HALF_UP);
        x1 = x1.add(x0);
        x1 = x1.divide(TWO, SCALE, ROUND_HALF_UP);

    }
    return x1;
}

}
