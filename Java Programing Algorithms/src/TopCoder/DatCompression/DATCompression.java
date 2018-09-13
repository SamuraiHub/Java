/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package TopCoder.DatCompression;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


/**
 *
 * @author Aljarhi
 */
public class DATCompression {

    /**
     * @param args the command line arguments
     */
 
    public int init()
    {
        
      return 0;    
    }
    
    public int[] compress(int[] data)
    {
        int X = data[0], Y = data[1], L = data[2], i = 0, D = 0, d, m = 1; 
        
        int[] dd = new int[X * Y *(L-1)];

       for(i = 0; i < X; i++)
       {
           for(int j = 0; j < Y; j++)
           {
               int s = data[(i*Y*L)+(j*L)+3];
                       
               for(int k = 1; k < L; k++)
               {
                   d = data[(i*Y*L)+(j*L)+k+3] - data[(i*Y*L)+(j*L)+k+2];
                   
                   d = d < 0 ? d = (-d * 2) : (d * 2) + 1; if(d > m){ m = d; }
                  
                   dd[D] = d; D++; 
               }
           }
       }
       
        data = reduce(dd, m, D, 0);
        
       int[] cData = new int[data[0]+6]; cData[0] = X; cData[1] = Y; cData[2] = L;
       
       for(i = 0, d = 3; i <data[0]+3; i++, d++)
       {
           cData[d] = data[i];
       }
       
       return cData;
    }
    
    public int[] decompress(int[] data)
    {
        throw new UnsupportedOperationException("Not yet implemented!");
    }
    
    private int[] reduce(int[] dd, int m, int D, int j)
    {
        int i, d = 0; boolean f = false; System.out.println(D);
        
        m = (int)(Math.log10(m)/Math.log10(2))+1;
        
       if(D > 10000000)
        {
            f = true;
                    
            for(i = 0; i <D; i++)
            {
              d = d+(2 * (int)(Math.log10(dd[i])/Math.log10(2)))+1;  
            }
        }
       
       boolean[] bData = f ? new boolean[d] : new boolean[m*D]; d = -1;
       
       for(i = 0; i < D; i++)
       { 
           int n = (int)(Math.log10(dd[i])/Math.log10(2))+1;
          if(f)
          { 
              d = d+(2 * n)-1;
              n = d - n;
          } 
           else
          {
           d = ((i+1)*m)-1;
           n = d - n;
          }
          
           for(; d != n; d--)
           {   
               int k = dd[i];
                
                dd[i] = dd[i]/2;
                
                bData[d] = (k - (dd[i]*2)) == 1;
           }
       } 
 
        dd = new int[bData.length-(bData.length/3)]; D = 0; f = bData[0]; d = 1; m = 1;
        
        for(i = 1; i < bData.length; i++)
        { 
                    if(bData[i] == f)
                    {
                        d++;
                    }    
                    else
                    {
                        if(d > m){ m = d; }

                           dd[D] = d; 
                           D++;
                       
                        f = !f;
                        d = 1;
                    }
        } 
   
        if(D < 1001)
        {
            //l = data.length;
            //k = data.length/2;
            
            /*l = l - (k*2);*/ System.out.println(); System.out.println(j);
            
            int[] rData = new int[D+3]; rData[0] = D; rData[1] = j; rData[2] = bData[0] == true ? 1:0;
            
            for(i = 0, d = 3; i < D; i++, d++)
            {
              rData[d] = dd[i];  
            } //{j, data[0] == true ? 1:0, l == 0 ? k:k+1, k};
            
            return rData;
        }       
 
          return reduce(dd, m, D, ++j);       
        
        //return new int[]{};
    }
    
    
    public static void main(String[] args) throws FileNotFoundException, IOException {
        // TODO code application logic here
        
        BufferedReader r = new BufferedReader(new FileReader(
                "F:/Stuff/Muaz/Programming/Java/Java Programing Algorithms/src/TopCoder/DatCompression/data/CRI-645_2800_2800_acq_0007.csv"));
        
        int[] data = new int[(104*104*59)+3]; data[0] = 104; data[1] = 104; data[2] = 59; int i = 3;
        
        String l;
        
        while((l = r.readLine()) != null)
        {
           String[] vs = l.split(",");
           
           for(int j = 2; j < vs.length; j++)
           {
            data[i] = Integer.parseInt(vs[j]); i++;   
           }
        }
        
        long Start = System.currentTimeMillis();
        
        DATCompression compression = new DATCompression(); compression.init();
        
        int[] compress = compression.compress(data);
        
        System.out.println("Ratio: "+data.length/compress.length);
        
        long Finish = System.currentTimeMillis();
               
        System.out.print("Compress time: " + (Finish-Start)/1000.0);
    }
}
