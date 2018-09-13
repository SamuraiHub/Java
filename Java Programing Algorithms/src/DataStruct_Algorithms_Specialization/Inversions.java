import java.io.File;
import java.io.FileOutputStream;
import java.util.*;

public class Inversions {

    private static long getNumberOfInversions(int[] a, int[] b, int left, int right) {
        long numberOfInversions = 0;
        if (right <= left+1) {
            return 0;
        }
        int ave = (left + right) >> 1;
        numberOfInversions += getNumberOfInversions(a, b, left, ave);
        numberOfInversions += getNumberOfInversions(a, b, ave, right);
        //write your code here
        
        
        
        int k = left, i = left, j = ave;
        
        while(i < ave && j < right)
        {
             if(a[j] < a[i])
             {
                 b[k] = a[j];
                 j++;
                 k++;
                 numberOfInversions += ave-i; 
             }
             else
             {
                 b[k] = a[i];
                 i++;
                 k++;
             }
        }
                
        while(i < ave)
        {
            b[k] = a[i];
            i++;
            k++;
        }
        
        
        while(j < right)
        {
            b[k] = a[j];
            j++;
            k++;
        }
        
        for(i = left; i < k; i++)
        {
          a[i] = b[i];  
        }
        
        return numberOfInversions;
    }
     
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }
        int[] b = new int[n];
        System.out.println(getNumberOfInversions(a, b, 0, n));
    }
}

