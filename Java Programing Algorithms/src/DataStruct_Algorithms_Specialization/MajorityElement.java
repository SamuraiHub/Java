import java.util.*;
import java.io.*;

public class MajorityElement {
    private static int getMajorityElement(int[] a, int left, int right) {
        if (left+1 == right) 
        {
            return a[left];
        }
        if (left + 2 == right) 
        {
            if(a[left] == a[right-1])
            {
                return a[left];
            }
            return -1;
        }
        //write your code here
        int mid = (right+left) >> 1; 
        int m1 = getMajorityElement(a,mid,right);
        int m2 = getMajorityElement(a,left,mid);
        
        if(m1 == m2)
        {
            return m1 == -1 ? -1 : m1;
        }
        
        if(m2 == -1)
        {
            int c = 0;
           for(int i = left; i < right; i++)
           {
               if(a[i] == m1) c++;
           }
           
           return c > (right-left)/2 ? m1 : -1;
        }
        
        if(m1 == -1)
        {
            int c = 0;
           for(int i = left; i < right; i++)
           {
               if(a[i] == m2) c++;
           }
           
           return c > (right-left)/2 ? m2 : -1;
        }
                    
            int c1 =0, c2 = 0;
            for(int i = left; i < right; i++)
           {
               if(a[i] == m1) c1++;
               if(a[i] == m2) c2++;
           }
            
            return c1 > (right-left)/2 ? m1 : c2 > (right-left)/2 ? m2 : -1;
    }

    public static void main(String[] args) {
        FastScanner scanner = new FastScanner(System.in);
        int n = scanner.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }
        if (getMajorityElement(a, 0, n) != -1) {
            System.out.println(1);
        } else {
            System.out.println(0);
        }
    }
    static class FastScanner {
        BufferedReader br;
        StringTokenizer st;

        FastScanner(InputStream stream) {
            try {
                br = new BufferedReader(new InputStreamReader(stream));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        String next() {
            while (st == null || !st.hasMoreTokens()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }
}

