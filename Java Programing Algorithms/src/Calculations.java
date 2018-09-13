
import java.math.BigDecimal;
import static java.math.BigDecimal.ROUND_HALF_UP;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Muaz
 */
public class Calculations {

    /**
     * Takes an array consisting of the number of occurrences of each dice number and and returns the product of all the numbers
     * @param a an array of integers   
     * @return the product of all occurrences of each dice number    
     */
    
    public static BigInteger factorial(int n) {
    BigInteger factorial = BigInteger.ONE;

    for (int i = 2; i <= n; i++) {
        factorial = factorial.multiply(BigInteger.valueOf(i));
    }

    return factorial;
} 
    
    public static BigInteger prod(List<Integer> a)
    {
      BigInteger p = BigInteger.ONE;
      for(int i = 2; i < 7; i++)
      { 
            p = p.multiply(BigInteger.valueOf(i).pow(a.get(i)));
      }
     
     return p;     
    }
    
    public static BigInteger Prod(List<Integer> a)
    {
      BigInteger p = BigInteger.ONE;  
      for(int i = 0; i < a.size(); i++)
      { 
         p = p.multiply(BigInteger.valueOf(a.get(i)));
      }   
     return p;     
    }
    
    private static BigDecimal sqrtNewtonRaphson  (BigDecimal c, BigDecimal xn, BigDecimal precision){
    
    BigDecimal currentPrecision = BigDecimal.ONE;
    BigDecimal xn1 = BigDecimal.ZERO;
    BigDecimal SQRT_DIG = new BigDecimal(5);
    
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
    return sqrtNewtonRaphson(c,new BigDecimal(1),new BigDecimal(1).divide( new BigDecimal(10).pow(5)));
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
    
    /**
     * Returns the mean or expected value for the product of N fair dices rolled who's sum add to M
     * @param N The number of fair dices rolled
     * @param M the sun of the fair dices
     * @return The mean of the product of the rolled fair dices
     */
    public static BigDecimal productMean(int N, int M)
    {
        List<Integer> cn = new ArrayList<>(Arrays.asList(new Integer[]{0,0,0,0,0,0,0})); // stores the occurrence of each number (1:6) from all dices. the total number of occurances is N
        
        int a = M/N;
        cn.set(a, N);
        
        if(a*N < M)
        {
          int d = M - (a*N);
          cn.set(a, cn.get(a)-d);
          cn.set(a+1, d);
        }
        
        LinkedList<List<Integer>> seq = new LinkedList<>(); //temp storage for diffent occurrences of each of num (1:6) of the n dices
        seq.add(cn); 
        
         // stores diffent occurrences of each of num (1:6) of the n dices so that no sequence is repeated (no same sqequence with different ordering) along with te prodcut.
        HashMap<Integer,List<Integer>> numO = new HashMap<>(); 
        numO.put(Arrays.hashCode(cn.toArray(new Integer[cn.size()])), cn);
        //System.out.println("Prod: "+prod(cn));
        
        while(!seq.isEmpty())
        {
            List<Integer> d = seq.removeFirst();
            
            for(int i = 1; i < 6; i++)
            {
                if(d.get(i) > 0)
                {
                   for(int j = 2; j < 7; j++)
                   {
                       if((j == i && d.get(j) > 1) || (j != i && d.get(j) > 0))
                       { 
                           List<Integer> d1 = new ArrayList<>(Arrays.asList(new Integer[]{0,0,0,0,0,0,0}));
                           Collections.copy(d1, d);
                          
                           d1.set(i, d1.get(i)-1); //removes occurance of a number
                           d1.set(i+1, d1.get(i+1)+1); // adds an occurance of the next higer number
 
                           d1.set(j, d1.get(j)-1); //removes occurance of a number
                           d1.set(j-1, d1.get(j-1)+1); // adds an occurance of the next higer number

                           if (!numO.containsKey(Arrays.hashCode(d1.toArray(new Integer[cn.size()])))) {
                               numO.put(Arrays.hashCode(d1.toArray(new Integer[cn.size()])), d1);
                               seq.add(d1);
                           }
                           else if(!numO.get(Arrays.hashCode(d1.toArray(new Integer[cn.size()]))).equals(d1))
                               System.out.println(numO.get(Arrays.hashCode(d1.toArray(new Integer[cn.size()]))) + "\t"+d1);
                       }
                   }
                }
            }
        }
        
        MathContext mc = new MathContext(30);
        BigDecimal A = new BigDecimal(0, mc);
        BigDecimal C = new BigDecimal(0, mc);
        BigDecimal D = new BigDecimal(0, mc);
        
        //System.out.println("Prods2: "+ numO.size());
        
        for(List<Integer> entry : numO.values())
        {
           BigInteger Prod = prod(entry); 
           BigDecimal B = new BigDecimal(factorial(N), mc); 
           
           for(int i = 1; i < 7; i++)
           { 
                 B=B.divide(new BigDecimal(factorial(entry.get(i)), mc), mc);
              
           }
           C = C.add(B);
           A = A.add(B.multiply(new BigDecimal(Prod, mc)));
           D = D.add(B.multiply(new BigDecimal(Prod.pow(2), mc)));
        }
        A = A.divide(C, new MathContext(10));
        
        D = sqrt((D.divide(C, mc)).subtract(A.pow(2)), 5);
        
        System.out.println("Standard Deviation: "+D);
        System.out.println("TotalProds: "+C);
        return A;
    }
    
    public static BigDecimal ProductMean(int N, int M)
    {
        MathContext mc = new MathContext(10);
        BigInteger[] C = new BigInteger[]{BigInteger.ZERO, BigInteger.ZERO, BigInteger.ZERO};
        loopFunction(N, M, 0, C, new ArrayList<>(Arrays.asList(new Integer[N])));
        BigDecimal D = (new BigDecimal(C[0], mc)).divide(new BigDecimal(C[1], mc), mc);
        System.out.println("Standard Deviation: "+(sqrt(((new BigDecimal(C[2], mc)).divide(new BigDecimal(C[1], mc), mc)).subtract(D.pow(2)), 5)));
        System.out.println("TotalProds1: "+C[1]);
        return D;
    }
    
    static void loopFunction(int level, int M, int S, BigInteger[] C, List<Integer> seq) 
    {
        if (level == 0) 
        { // terminating condition
            if (S == M) 
            {
                BigInteger P = Prod(seq);
                C[0] = C[0].add(P);
                C[1] = C[1].add(BigInteger.ONE);
                C[2] = C[2].add(P.pow(2));
            }
        }
        else 
        {// inductive condition
            for (int i = 1; i < 7; i++) 
            {
                seq.set(level-1,i);
                loopFunction(level - 1, M, S + i, C, seq);
            }
        }
}
    
    
    /**
     * 
     * @param args 
     */
    public static void main(String[] args) {
        // TODO code application logic here
        int N = 50, M = 150;
        Long t1 = System.nanoTime();
        BigDecimal A = productMean(N, M);
        Long t2 = System.nanoTime();
        System.out.println("Expected Value: "+A+"\tTime: "+((t2-t1)/1000000.0));
        //t1 = System.nanoTime();
        //A = ProductMean(N, M);
        //t2 = System.nanoTime();
        //System.out.println("Expected Value1: "+A+"\tTime: "+((t2-t1)/1000000.0));
    }
}
