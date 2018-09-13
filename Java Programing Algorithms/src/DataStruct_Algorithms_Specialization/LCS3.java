import java.util.*;

public class LCS3 {

    private static int lcs3(int[] a, int[] b, int[] c) {
        //Write your code here
        int ws = b.length*c.length;
        
        int[] lcs = new int[a.length*ws];
        
        lcs[0] = a[0] == b[0] && b[0] == c[0] ? 1 : 0;
        
        for(int i = 1; i < c.length; i++)
        {
            if(c[i] == b[0] && c[i] == a[0])
                lcs[i] = 1; 
            else
                lcs[i] = lcs[i-1];
        }
        
        for(int i = 1; i < b.length; i++)
        {
            if(b[i] == a[0] && b[i] == c[0])
                lcs[i*c.length] = 1;
            else
                lcs[i*c.length] = lcs[(i-1)*c.length];
        }
        
        for(int i = 1; i < a.length; i++)
        {
            if(a[i] == b[0] && a[i] == c[0])
                lcs[i*ws] = 1;
            else
                lcs[i*ws] = lcs[(i-1)*ws];
        }
        
        for(int j = 1; j < b.length; j++)
        {
            for(int k = 1; k < c.length; k++)
            {
                int m = Math.max(lcs[(j-1)*c.length+k], lcs[j*c.length+k-1]);
                
                if(lcs[(j-1)*c.length+k-1] >= m)
                {
                    if(b[j] == c[k] && c[k] == a[0] && m == 0)
                    {
                      lcs[j*c.length+k] = lcs[(j-1)*c.length+k-1] + 1;  
                    }
                    else
                        lcs[j*c.length+k] = lcs[(j-1)*c.length+k-1];
                }
                else
                  lcs[j*c.length+k] = m;  
            }
        }
        
        for(int i = 1; i < a.length; i++)
        {
            for(int k = 1; k < c.length; k++)
            {
                int m = Math.max(lcs[(i-1)*ws+k], lcs[i*ws+k-1]);
                
                if(lcs[(i-1)*ws+k-1] >= m)
                {
                    if(a[i] == c[k] && c[k] == b[0] && m == 0)
                    {
                      lcs[i*ws+k] = lcs[(i-1)*ws+k-1] + 1;  
                    }
                    else
                        lcs[i*ws+k] = lcs[(i-1)*ws+k-1];
                }
                else
                  lcs[i*ws+k] = m;  
            }
        }
        
        for(int i = 1; i < a.length; i++)
        {
            for(int j = 1; j < b.length; j++)
            {
                int m = Math.max(lcs[(i-1)*ws+j*c.length], lcs[i*ws+(j-1)*c.length]);
                
                if(lcs[(i-1)*ws+(j-1)*c.length] >= m)
                {
                    if(b[j] == a[i] && a[i] == c[0] && m == 0)
                    {
                      lcs[i*ws+j*c.length] = lcs[(i-1)*ws+(j-1)*c.length] + 1;  
                    }
                    else
                        lcs[i*ws+j*c.length] = lcs[(i-1)*ws+(j-1)*c.length];
                }
                else
                  lcs[i*ws+j*c.length] = m;  
            }
        }
        
        
        for(int i = 1; i < a.length; i++) {
            for(int j = 1; j < b.length; j++) {
                for(int k = 1; k < c.length; k++)
                {
                    int m = Math.max(lcs[(i-1)*ws+j*c.length+k], lcs[(i-1)*ws+(j-1)*c.length+k]);
                    m = Math.max(m, lcs[(i-1)*ws+j*c.length+k-1]);
                    m = Math.max(m, lcs[i*ws+(j-1)*c.length+k]);
                    m = Math.max(m, lcs[i*ws+j*c.length+k-1]);
                    m = Math.max(m, lcs[i*ws+(j-1)*c.length+k-1]);
                    
                    if(lcs[(i-1)*ws+(j-1)*c.length+k-1] >= m)
                    {
                        if(a[i] == b[j] && b[j] == c[k])
                        {
                          lcs[i*ws+j*c.length+k] = lcs[(i-1)*ws+(j-1)*c.length+k-1] + 1;  
                        }
                        else
                          lcs[i*ws+j*c.length+k] = lcs[(i-1)*ws+(j-1)*c.length+k-1];
                    }
                    else
                      lcs[i*ws+j*c.length+k] = m; 
                    
                }
            }
        }
        
     
        return lcs[a.length*ws-1];
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int an = scanner.nextInt();
        int[] a = new int[an];
        for (int i = 0; i < an; i++) {
            a[i] = scanner.nextInt();
        }
        int bn = scanner.nextInt();
        int[] b = new int[bn];
        for (int i = 0; i < bn; i++) {
            b[i] = scanner.nextInt();
        }
        int cn = scanner.nextInt();
        int[] c = new int[cn];
        for (int i = 0; i < cn; i++) {
            c[i] = scanner.nextInt();
        }
        System.out.println(lcs3(a, b, c));
    }
}

