import java.util.*;
import java.io.*;
import java.util.zip.CheckedInputStream;

public class SuffixArrayLong {
    class FastScanner {
        StringTokenizer tok = new StringTokenizer("");
        BufferedReader in;

        FastScanner() {
            in = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() throws IOException {
            while (!tok.hasMoreElements())
                tok = new StringTokenizer(in.readLine());
            return tok.nextToken();
        }

        int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }
    
    public int[] SortAndOrder(int[] sorted, int[] rank, int mr, int l)
    {
        int[] sorted1 = new int[sorted.length];
        int[] rank1 = new int[rank.length];
        int[] count = new int[mr];
        
        //count sort last half
        for(int i = 0; i < sorted.length; i++)
        {
          int j = (sorted[i]+l)%sorted.length;  
          count[rank[j]]++; 
        }
        for(int i = 1; i < mr; i++)
        {
            count[i] += count[i-1];
        }
        //sorted by the last half
        for(int i = sorted.length-1; i >= 0; i--)
        {
          int j = (sorted[i]+l)%sorted.length;
          count[rank[j]]--;
          sorted1[count[rank[j]]] = sorted[i];
        }
        Arrays.fill(count, 0);
        //count sort first half
        for(int i = 0; i < sorted.length; i++)
        {
          count[rank[sorted[i]]]++; 
        }
        for(int i = 1; i < mr; i++)
        {
            count[i] += count[i-1];
        }
        
        //sorted completely
        for(int i = sorted.length-1; i >= 0; i--)
        {
          count[rank[sorted1[i]]]--;
          sorted[count[rank[sorted1[i]]]] = sorted1[i];
        }
        
        //update the rank
        rank1[sorted.length-1]=0;
        rank1[sorted[1]] = 1;
        for(int i = 2; i <sorted.length; i++)
        {
           int j = (sorted[i-1]+l)%sorted.length;
           int k = (sorted[i]+l)%sorted.length;
                
           if(rank[sorted[i-1]] == rank[sorted[i]] && rank[j] == rank[k])
               rank1[sorted[i]] = rank1[sorted[i-1]];
           else
               rank1[sorted[i]] = rank1[sorted[i-1]]+1;     
        }
        
        return rank1;
    }

    // Build suffix array of the string text and
    // return an int[] result of the same length as the text
    // such that the value result[i] is the index (0-based)
    // in text where the i-th lexicographically smallest
    // suffix of text starts.
    public int[] computeSuffixArray(String text) {
        // write your code here
        int l = 1;
        int n = text.length() - 1;
        int[] rank = new int[text.length()];
        int[] sorted = new int[text.length()];
        
        for(int i = 0; i < n; i++)
        {
            sorted[i+1] = i;
            switch (text.charAt(i))
            {
                case 'A':
                    rank[i] = 1;
                    break;
                case 'C':
                    rank[i] = 2;
                    break;
                case 'G': 
                    rank[i] = 3;
                    break;
                case 'T':
                    rank[i] = 4;
                    break;
            }
        }
        sorted[0] = n;
        rank[n] = 0;
        
        int mr = 5;
        
        while(l < n)
        {
            rank = SortAndOrder(sorted, rank, mr, l);
            mr = rank[sorted[sorted.length-1]]+1;
            l = l << 1; //1,2,4...
        }
       
        return sorted;
    }


    static public void main(String[] args) throws IOException {
        new SuffixArrayLong().run();
    }

    public void print(int[] x) {
        for (int a : x) {
            System.out.print(a + " ");
        }
        System.out.println();
    }

    public void run() throws IOException {
        FastScanner scanner = new FastScanner();
        String text = scanner.next();
        int[] suffix_array = computeSuffixArray(text);
        print(suffix_array);
    }
}
