/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package TopCoder.DatCompression;

import java.util.Arrays;

/**
 *
 * @author Aljarhi
 */
public class binaryAnalysis {

    /**
     * @param args the command line arguments
     */
    
    public static int sum(int[] num)
    {
        int sum = 0;
        
        for(int i = 0; i < num.length; i++)
        {
            sum = sum + num[i];
        }
        
        return sum;
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
        int[][] bn = new int[127][];
        
        for(int i = 1; i <128; i++)
        {
            int m = (int) (Math.log10(i)/Math.log10(2));
            
            int l = i, j = 2*m; int[] b = new int[2*m+1];  
            
            while(l >= 1)
            {
                int k = l;
                l = l/2;
                 
                b[j] = k - l*2; j--;
            }
        
            bn[i-1] = b;
        }
      
        /*int[] ds = new int[255*255*8];*/ int[] df = new int[14]; Arrays.fill(df, 0);
        //int l = 0;
        
        for(int i = 0; i <127; i++)
        {
            for(int j = 0; j<127; j++)
            {
                int k, d = 0;
                for(k = 0; k < bn[i].length; k++)
                { 
                    if(bn[i][k] == 0)
                    {
                        d++;
                    }
                    else if(d > 0)
                    {
                       //System.out.print(d+",");
                       df[d-1]++; d = 0;
                    }
                }
                
                for(k = 0; k < bn[j].length; k++)
                {
                    if(bn[j][k] == 0)
                    {
                        d++;
                    }
                    else if(d > 0)
                    {
                       //System.out.print(d+",");
                       df[d-1]++; d = 0;
                    }
                }
                
                if(d > 0)
                {
                    //System.out.print(d+",");
                       df[d-1]++; d = 0;
                }
            }
        }
        double sum = sum(df); int[] numb = {1,3,3,5,5,5,5,7,7,7,7,7,7,7,7}; int tb = 0;
        
        for(int i = 0;  i < 14; i++)
        {
           tb = tb + (int)((df[i]*100/sum) + 0.5)*numb[i];
            
            System.out.print((int)((df[i]*100/sum) + 0.5)+",");
        }
        System.out.println();
        System.out.println(tb);
    }
}
