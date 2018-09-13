
import java.io.*;
import java.lang.Integer;
import java.util.ArrayList;
import java.util.List;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Aljarhi
 */
public class sum_greatest_cs_sequence 
{
    public static int[] get_gr_sq(int[] sq) //returns greatest dquence pf ints fount in list
    {
        int[] gr_sq = new int[sq.length];
        int[] t_sq = new int[sq.length];;
        gr_sq[0] = sq[0]; t_sq[0] = 0;
        int j = 1;
        
        for(int i = 1; i < sq.length; i++)
        {
            t_sq[0] = sq[i-1]; 
            int k = 1;
            //System.out.println(t_sq[0]);
            while(i < sq.length && sq[i] > sq[i-1])
            {
                t_sq[k] = sq[i];
                k++; 
                i++;
                //System.out.println(t_sq[k-1]);
            }
            //System.out.println("//");
            
            if(k > j)
            {
             for(j = 0; j < k; j++)
             {
               gr_sq[j] = t_sq[j];  
             }
            }
        }
        //System.out.println(gr_sq[0]+", " + gr_sq[j-1]);
        return gr_sq;
    }
    
    
    
    public static int sum_sq(int[] sq) //returns the sum of all elements in sq
    {
        int s = 0;
        for(int i:sq)
        {
         s = s + i;   
        }
        
        return Math.abs(s);
    }
    
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new FileReader("F:/Stuff/Muaz/Notes/cs_sequence.txt"));
        List<Integer> I = new ArrayList<Integer>();
        String s;
        
        while((s = br.readLine()) != null)
        {
          I.add(Integer.parseInt(s));              
        }
        int[] a = new int [I.size()];
        
        for(int j = 0; j < a.length; j++)
        {
            a[j] = I.get(j).intValue();
            //System.out.println(a[j]);
        }
        
        
 
        System.out.println(sum_sq(get_gr_sq(a)));
        
    }
    
}
