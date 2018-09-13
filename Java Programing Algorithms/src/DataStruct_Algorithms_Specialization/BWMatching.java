import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BWMatching {
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

    // Preprocess the Burrows-Wheeler Transform bwt of some text
    // and compute as a result:
    //   * starts - for each character C in bwt, starts[C] is the first position
    //       of this character in the sorted array of
    //       all characters of the text.
    //   * occ_count_before - for each character C in bwt and each position P in bwt,
    //       occ_count_before[C][P] is the number of occurrences of character C in bwt
    //       from position 0 to position P inclusive.
    private void PreprocessBWT(String bwt, Map<Character, Integer> starts, Map<Character, int[]> occ_counts_before) 
    {
        // Implement this function yourself
        int[] startc = new int[3];
        
        int[] a = new int[bwt.length()];
        int[] c = new int[bwt.length()];
        int[] g = new int[bwt.length()];
        int[] t = new int[bwt.length()];
            
        occ_counts_before.put('A', a);
        occ_counts_before.put('C', c);
        occ_counts_before.put('G', g);
        occ_counts_before.put('T', t);
        
        switch (bwt.charAt(0)) 
        {
            case 'A':
                startc[0] = 1;
                a[0] = 1;
                c[0] = 0;
                g[0] = 0;
                t[0] = 0;
                break;
            case 'C':
                a[0] = 0;
                c[0] = 1;
                g[0] = 0;
                t[0] = 0;
                startc[1] = 1;
                break;
            case 'G':
                a[0] = 0;
                c[0] = 0;
                g[0] = 1;
                t[0] = 0;
                startc[2] = 1;
                break;
            case 'T':
                a[0] = 0;
                c[0] = 0;
                g[0] = 0;
                t[0] = 1;
            default:
                break;
        }
        // uses count sort
        for(int i =1; i < bwt.length(); i++)
        {
            switch (bwt.charAt(i)) 
            {
                case 'A':
                    startc[0]++;
                    a[i] = a[i-1]+1;
                    c[i] = c[i-1];
                    g[i] = g[i-1];
                    t[i] = t[i-1];
                    break;
                case 'C':
                    startc[1]++;
                    a[i] = a[i-1];
                    c[i] = c[i-1]+1;
                    g[i] = g[i-1];
                    t[i] = t[i-1];
                    break;
                case 'G':  
                    startc[2]++;
                    a[i] = a[i-1];
                    c[i] = c[i-1];
                    g[i] = g[i-1]+1;
                    t[i] = t[i-1];
                    break;
                case 'T':
                    a[i] = a[i-1];
                    c[i] = c[i-1];
                    g[i] = g[i-1];
                    t[i] = t[i-1]+1;
                    break;
                default:
                    a[i] = a[i-1];
                    c[i] = c[i-1];
                    g[i] = g[i-1];
                    t[i] = t[i-1];
                    break;
           }
        }
        
        starts.put('A', 0);
        
        starts.put('C', startc[0]);
        
        startc[1] = startc[0]+startc[1];
        
        starts.put('G', startc[1]);
        
        startc[2] = startc[2]+startc[1];
        
        starts.put('T', startc[2]);
    }

    // Compute the number of occurrences of string pattern in the text
    // given only Burrows-Wheeler Transform bwt of the text and additional
    // information we get from the preprocessing stage - starts and occ_counts_before.
    int CountOccurrences(String pattern, String bwt, Map<Character, Integer> starts, Map<Character, int[]> occ_counts_before) 
    {
        // Implement this function yourself
        char c = pattern.charAt(pattern.length()-1);
        int s1 = starts.get(c);
        int s2 = occ_counts_before.get(c)[bwt.length()-1]+s1;
        
        for(int i = pattern.length()-2; i > -1; i--)
        {
            c = pattern.charAt(i);
           int ocb1 = occ_counts_before.get(c)[s1];
           int ocb2 = occ_counts_before.get(c)[s2]-ocb1; 
            
           s1  = starts.get(c)+ocb1;
           s2 = s1 + ocb2;
        
           if(ocb2 == 0)
            return 0;
        }
           
        return s2 - s1;
    }

    static public void main(String[] args) throws IOException {
        new BWMatching().run();
    }

    public void print(int[] x) {
        for (int a : x) {
            System.out.print(a + " ");
        }
        System.out.println();
    }

    public void run() throws IOException {
        FastScanner scanner = new FastScanner();
        String bwt = scanner.next();
        // Start of each character in the sorted list of characters of bwt,
        // see the description in the comment about function PreprocessBWT
        Map<Character, Integer> starts = new HashMap<Character, Integer>();
        // Occurrence counts for each character and each position in bwt,
        // see the description in the comment about function PreprocessBWT
        Map<Character, int[]> occ_counts_before = new HashMap<Character, int[]>();
        // Preprocess the BWT once to get starts and occ_count_before.
        // For each pattern, we will then use these precomputed values and
        // spend only O(|pattern|) to find all occurrences of the pattern
        // in the text instead of O(|pattern| + |text|).
        PreprocessBWT(bwt, starts, occ_counts_before);
        int patternCount = scanner.nextInt();
        String[] patterns = new String[patternCount];
        int[] result = new int[patternCount];
        for (int i = 0; i < patternCount; ++i) {
            patterns[i] = scanner.next();
            result[i] = CountOccurrences(patterns[i], bwt, starts, occ_counts_before);
        }
        print(result);
    }
}
