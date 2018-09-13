import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class SuffixArrayMatching {
    class fastscanner {
        StringTokenizer tok = new StringTokenizer("");
        BufferedReader in;

        fastscanner() {
            in = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() throws IOException {
            while (!tok.hasMoreElements())
                tok = new StringTokenizer(in.readLine());
            return tok.nextToken();
        }

        int nextint() throws IOException {
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
    public int[] computeSuffixArray(String text, int[] LCP) 
    {
        // write your code here
        int l = 1;
        int n = text.length() - 1;
        int[] rank = new int[text.length()];
        int[] sorted = new int[text.length()];
        ArrayList<int[]> ranks = new ArrayList<int[]>(17);
        
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
            ranks.add(rank);
            rank = SortAndOrder(sorted, rank, mr, l);
            mr = rank[sorted[sorted.length-1]]+1;
            l = l << 1; //1,2,4...
        }
        LCP[0] = -1;
        LCP[1] = 0;
        
        for(int i= 2; i < sorted.length; i++)
        {
           int cp = 0;
           int m = l >> 1;
           int j = ranks.size()-1;
           int x = sorted[i-1];
           int y = sorted[i];

           // gets the largest commen prefix witch is at most the shorter of the concecutive sorted suffixes by recursively going down the ranks of m
           while(m > 0)
           { 
              if(ranks.get(j)[x] == ranks.get(j)[y])
              {
                 x = x+m;
                 y = y+m;
                 cp = cp + m;
              }
              
              m = m >> 1; 
              j--;   
           }
           LCP[i] = cp;
        }
       
        return sorted;
    }
    
    public List<Integer> findOccurrences(String pattern, String text, int[] suffixArray, int[] LCP) {
        List<Integer> result =new ArrayList<Integer>();
        // write your code here
        int mid = suffixArray.length/2;
        int left = 0;
        int right = suffixArray.length;
        boolean find = false;
        OUTER:
        while (mid != left) {
            int l = Math.min(suffixArray[mid] + pattern.length(),text.length());
            int a = 0;
            int n = 0;
            for(int i = suffixArray[mid]; i < l; i++)
            {
                if(pattern.charAt(n) < text.charAt(i))
                {
                    a = -1;
                    break;
                }
                else if(pattern.charAt(n) > text.charAt(i))
                {
                    a = 1;
                    break;
                }
                n++;
            }
            switch (a) 
            {
                case -1:
                    right = mid;
                    mid = (right + left) / 2;
                    break;
                case 1:
                    left = mid;
                    mid = (right + left) / 2;
                    break;
                default:
                    find = true;
                    break OUTER;
            }
        }

        if(find)
        { 
           result.add(suffixArray[mid]);
           int j = mid+1;
           while(j < text.length() && LCP[j] >= pattern.length())
           { 
             result.add(suffixArray[j]);  
             j++;
           }
           j = mid;
           while(j > 1 && LCP[j] >= pattern.length())
           {
             j--; 
             result.add(suffixArray[j]); 
           }
        }
        
        return result;
    }

    static public void main(String[] args) throws IOException {
        new SuffixArrayMatching().run();
    }

    public void print(boolean[] x) {
        for (int i = 0; i < x.length; ++i) {
            if (x[i]) {
                System.out.print(i + " ");
            }
        }
        System.out.println();
    }

    public void run() throws IOException {
        fastscanner scanner = new fastscanner();
        String text = scanner.next() + "$";
        int[] LCP = new int[text.length()];
        int[] suffixArray = computeSuffixArray(text,LCP);
        int patternCount = scanner.nextint();
        boolean[] occurs = new boolean[text.length()];
        for (int patternIndex = 0; patternIndex < patternCount; ++patternIndex) {
            String pattern = scanner.next();
            List<Integer> occurrences = findOccurrences(pattern, text, suffixArray, LCP);
            for (int x : occurrences) {
                occurs[x] = true;
            }
        }
        print(occurs);
    }
}
