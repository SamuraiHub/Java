import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class PointsAndSegments {

    private static int[] fastCountSegments(int[] starts, int[] ends, int[] points) {
        int[] cnt = new int[points.length];
        //write your code here
        int[] a = new int[starts.length];
        int[] b = new int[starts.length];
        int[] s1 = Arrays.copyOf(starts, starts.length);
        int[] e1 = Arrays.copyOf(ends, starts.length); 
        
        MergeSort(starts, ends, a, b, 0, starts.length);
        MergeSort(e1, s1, a, b, 0, starts.length);
  
        for (int i = 0; i < points.length; i++) {
          
            int c1 = binarySearch(starts, points[i], 0, starts.length);
            int c2 = binarySearch1(e1, points[i], 0, starts.length);
            
            cnt[i] +=  c1 - c2;
        }
        
        return cnt;
    }
    
    static int binarySearch(int[] s, int x, int left, int right) 
    {
        
        if(left >= right)
            return left;
        
        int mid = (right+left) >> 1;
        
        if(s[mid] <= x)     
        return binarySearch(s,x,mid+1,right);    
        else
        return binarySearch(s,x,left,mid);
    }
    
    static int binarySearch1(int[] s, int x, int left, int right) 
    {
         if(left >= right)
            return left;
        
        int mid = (right+left) >> 1;
        
        if(s[mid] < x)     
        return binarySearch1(s,x,mid+1,right);    
        else
        return binarySearch1(s,x,left,mid);
    }
    
        private static void MergeSort(int[] s, int[] e, int[] a, int[] b, int left, int right) 
        {
            
           if(right <= left+1) 
           {
             return;
           }
            
          int ave = (left + right) >> 1;
          MergeSort(s, e, a, b, left, ave);
          MergeSort(s, e, a, b, ave, right);
        //write your code here
          int k = left, i = left, j = ave;
        
          while(i < ave && j < right)
          {
             if(s[j] < s[i])
             {
                 a[k] = s[j];
                 b[k] = e[j];
                 j++;
                 k++;
             }
             else
             {
                 a[k] = s[i];
                 b[k] = e[i];
                 i++;
                 k++;
             }
          }
        
          while(i < ave)
          {
            a[k] = s[i];
            b[k] = e[i];
            i++;
            k++;
          }
        
          while(j < right)
          {
            a[k] = s[j];
            b[k] = e[j];
            j++;
            k++;
          }
        
          for(i = left; i < k; i++)
          {
            s[i] = a[i];
            e[i] = b[i];
          }
    }

    private static int[] naiveCountSegments(int[] starts, int[] ends, int[] points) {
        int[] cnt = new int[points.length];
        for (int i = 0; i < points.length; i++) {
            for (int j = 0; j < starts.length; j++) {
                if (starts[j] <= points[i] && points[i] <= ends[j]) {
                    cnt[i]++;
                }
            }
        }
        return cnt;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n, m;
        n = scanner.nextInt();
        m = scanner.nextInt();
        int[] starts = new int[n];
        int[] ends = new int[n];
        int[] points = new int[m];
        for (int i = 0; i < n; i++) {
            starts[i] = scanner.nextInt();
            ends[i] = scanner.nextInt();
        }
        for (int i = 0; i < m; i++) {
            points[i] = scanner.nextInt();
        }
        
        //use fastCountSegments
        int[] cnt = fastCountSegments(starts, ends, points);
        
        for(int x : cnt)
         System.out.print(x+" ");
    }
}

