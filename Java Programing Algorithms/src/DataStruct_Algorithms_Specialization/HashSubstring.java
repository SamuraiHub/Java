import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class HashSubstring {

    private static FastScanner in;
    private static PrintWriter out;

    public static void main(String[] args) throws IOException {
        in = new FastScanner();
        out = new PrintWriter(new BufferedOutputStream(System.out));
        printOccurrences(getOccurrences(readInput()));
        out.close();
    }
    
    private static int hashFunc(String s) {
        long hash = 0;
        for (int i = s.length() - 1; i >= 0; --i)
            hash = ((hash * 263) + s.charAt(i)) % 1000000007;
        return (int)hash;
    }

    private static Data readInput() throws IOException {
        String pattern = in.next();
        String text = in.next();
        return new Data(pattern, text);
    }

    private static void printOccurrences(List<Integer> ans) throws IOException {
        for (Integer cur : ans) {
            out.print(cur);
            out.print(" ");
        }
    }

    private static List<Integer> getOccurrences(Data input) 
    {
        String s = input.pattern, t = input.text;
        
        int n = t.length(), m = s.length();
                LinkedList<Integer> occurrences = new LinkedList<Integer>();
        int PH = hashFunc(s);
        int subSH = hashFunc(t.substring(n-m, n));
        //System.out.println("PH: " + PH);
       // System.out.println("subSH: " + subSH);
        if(PH == subSH)
            {    
                  occurrences.addFirst(n-m);
            }
        long y = 1;
        for(int i = 0; i < m; i++)
            y = (y*263)%1000000007;

        for(int i = t.length()-1; i >= m; --i)
        {
            
            subSH = (int)(((263L*subSH) + t.charAt(i-m) - (t.charAt(i) * y)) % 1000000007);
            if(subSH < 0) { subSH = subSH + 1000000007; }
             //System.out.println("subSH1: " + hashFunc(t.substring(i-m, i)));
            //System.out.println("subSH: " + subSH );
            
            if(PH == subSH)
            {    
                  occurrences.addFirst(i-m);
            }
        }
        return occurrences;
    }

    static class Data {
        String pattern;
        String text;
        public Data(String pattern, String text) {
            this.pattern = pattern;
            this.text = text;
        }
    }

    static class FastScanner {
        private BufferedReader reader;
        private StringTokenizer tokenizer;

        public FastScanner() {
            reader = new BufferedReader(new InputStreamReader(System.in));
            tokenizer = null;
        }

        public String next() throws IOException {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                tokenizer = new StringTokenizer(reader.readLine());
            }
            return tokenizer.nextToken();
        }

        public int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }
}

