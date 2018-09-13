/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Project_Euler;

/**
 *
 * @author Aljarhi
 */
public class Largest_palindrome_product {

    /**
     * @param args the command line arguments
     */
    public static boolean palindromeNumCheck(long n)
    {
        String p = Long.toString(n, 10);
        
        for(int i = 0; i < p.length()/2; i++)
        {
            if(p.charAt(i) != p.charAt(p.length()-(1+i)))
            {
                return false;
            }
        }
        
        return true;
    }
   
    
    public static void main(String[] args) {
        // TODO code application logic here
        
         Long Start = System.currentTimeMillis();
        
        int lp = 0; 
         
       for(int i = 0; i<1000; i++)  
       {
           for(int j = i; j <1000; j++)
           {
             int p = i*j;
               
             if(palindromeNumCheck(p))
             {
                lp = Math.max(lp, p);
             }
           }
       }
       
        System.out.println(lp);
       
       Long Finish = System.currentTimeMillis();
       
       System.out.println((Finish-Start)/1000.0);
        
    }
}
