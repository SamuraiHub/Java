package TopCoder;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Administrator
 */
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class IsolatedPrimes {

    /**
     * @param args the command line arguments
     */

    public IsolatedPrimes()
    {

    }

   public String findPrime1(int x, int a, int b)
   {
    String result = null;

    int p = ((BigInteger.valueOf(a+1)).nextProbablePrime()).intValue(); 

    int np = ((BigInteger.valueOf(p)).nextProbablePrime()).intValue(); 

    int TP = 0; // total number of primes in the range including the prime number;

    int k = p-a;

    if(k%2 == 0)
    {
      if(k == 2) 
      {
       TP++;
       k++;        
      }  
      else
      {
       k++;   
      }    
    }
    
    for(int j = k; j<=p; j=j+2)
    {
     if(BigInteger.valueOf(j).isProbablePrime(200))
     TP++;
    }

    for(int j = np; j<=p+b; j=j+2)
    {
     if(BigInteger.valueOf(j).isProbablePrime(200))
     TP++;
    }

     if(TP <= x)
      return String.valueOf(p);

    for(int i = p; i<Math.pow(2,63)-1-b; i = p)
    {  

    int numBNP = 0; // number of prime numbers in the range of the prime number and less than the range of the next prime number
    int numANP = 0; // number of prime numbers in the range of the prime number and in the range of the next prime number

    if(np-a < (i-a + ((a+b)/2)))
    {
      k = i-a;

    if(k%2 == 0)
    {
      if(k == 2)
      {
       numBNP++;
       k++;
      }
      else
      {
       k++;
      }
    }
        for(int j = k; j<np-a; j=j+2)
    {
      if(BigInteger.valueOf(j).isProbablePrime(200))
      numBNP++;
    }

      numANP = TP - numBNP;
      
      numBNP = 0;

        k = i+b+1;

    if(k%2 == 0)
    {
       k++;
    }
      for(int j = k; j<=np+b; j = j+2)
    {           
     if(BigInteger.valueOf(j).isProbablePrime(200))
     numBNP++;
    }
     TP = numBNP + numANP;

     if(TP <= x)
      return String.valueOf(np);
    
    p = np;
    np = ((BigInteger.valueOf(np)).nextProbablePrime()).intValue();
    }
    else
    {
     TP = 0;

      k = np-a;
    
    if(k%2 == 0)
    {
        k++;
    }
    for(int j = k; j<=np; j=j+2)
    {
     if(BigInteger.valueOf(j).isProbablePrime(200))
     TP++;
    }
    p = np;
    np = ((BigInteger.valueOf(i)).nextProbablePrime()).intValue();  
      
    for(int j = np; j<=p+b; j=j+2)
    {
     if(BigInteger.valueOf(j).isProbablePrime(200))
     TP++;   
    }
     if(TP <= x)
      return String.valueOf(p);   
    }
    }
    return result;
   }

  public String findPrime2(int x, int a, int b)
  {
    List<Integer> TP = new ArrayList<Integer>();

    List<Integer> PS = new ArrayList<Integer>();

    int p = ((BigInteger.valueOf(a+1)).nextProbablePrime()).intValue();

    int np = ((BigInteger.valueOf(p)).nextProbablePrime()).intValue();

    int PT = 0;

    int n = 0;

    PS.add(np);

    TP.add(0);

    int i = p-a;

    if(i%2 == 0)
    {
        if(i == 2)
      {
       TP.set(0, 1);
      }
       i++;
    }

   while(i<Math.pow(2,63)-1-b)
   {
     if(i == np-a || i == np-a+1)
     {
      if(TP.size() != 0)
      PT = PT + TP.get(TP.size()-1);
      else
      PT = 0;

      TP.add(0);
      np = ((BigInteger.valueOf(np)).nextProbablePrime()).intValue();
      PS.add(np);
     }

      if(BigInteger.valueOf(i).isProbablePrime(200))
     {
        TP.set(TP.size()-1, TP.get(TP.size()-1)+1);
     }
       if((PT + TP.get(TP.size()-1)) > x)
        {
         PT = PT - TP.remove(0);
         p = PS.remove(0);
   
        }

     if(i == p+b || i == p+b-1)
     {
       TP.clear();
       PS.clear();
       return String.valueOf(p);
     }

    i= i+2;
   }

   return null;
  }
  public String findPrime(int x, int a, int b)
  {
    int ev = 1;

    if(b%2 == 0)
    {
      ev = 0;
    }

    int p = ((BigInteger.valueOf(a+1)).nextProbablePrime()).intValue();

    int i = p+b-ev;

    int n = p-a;

    int PT = 0;

    while(i>=n)
   {
     if(BigInteger.valueOf(i).isProbablePrime(200))
     {
       PT++;
     }

     if(PT == x)
     {
       p = ((BigInteger.valueOf(i+a)).nextProbablePrime()).intValue();

       i = p+b-ev;

       n = p-a;

       PT = 0;

       continue;
     }

      if(i == n || i == n+1)
     {
      return String.valueOf(p);
     }
     
     i = i-2;
   }

    return null;
  }
    public static void main(String[] args) {
      // TODO code application logic here
      
     IsolatedPrimes IS = new IsolatedPrimes();

     int x = 76;
     int a = 1127;
     int b = 546;
     String p1 = IS.findPrime(x, a, b);

     System.out.println(p1);
    }
}
