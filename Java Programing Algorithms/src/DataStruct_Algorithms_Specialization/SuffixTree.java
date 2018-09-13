import java.util.*;
import java.io.*;

public class SuffixTree {
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
    
    class Node
    {
	public final String[] Suffix;
	public int next [];
        

	Node ()
	{
            Suffix = new String[5];
            Arrays.fill(Suffix, "");
            next = new int [5];
            Arrays.fill (next, -1);
	}
    }
    
    int letterToIndex(char letter)
	{
		switch (letter)
		{
			case 'A': return 0;
			case 'C': return 1;
			case 'G': return 2;
			case 'T': return 3;
                        case '$': return 4;
			default: assert (false); return -1;
		}
	}

    // Build a suffix tree of the string text and return a list
    // with all of the labels of its edges (the corresponding 
    // substrings of the text) in any order.
    public List<String> computeSuffixTreeEdges(String text) {
        List<String> result = new ArrayList<String>();
        // Implement this function yourself
        List<Node> trie = new ArrayList<Node>();
        trie.add(new Node());
        
        for(int i = 0; i < text.length(); i++)
        {
            Node Current = trie.get(0);
            int j = i;
            int k = letterToIndex(text.charAt(j));
            String c = Current.Suffix[k];
            
            while(c.length() >= 1 && c.length() <= text.substring(j).length() && c.equals(text.substring(j, j+c.length())))
            {
               Current = trie.get(Current.next[k]);
               j+=c.length();
               k = letterToIndex(text.charAt(j));
               c = Current.Suffix[k];
            }
            
            if("".equals(c))
            {
                Current.Suffix[k] = text.substring(j);
            }
            else
            {
                trie.add(new Node());
                int i1 = 1;
                j++;
                
                while(c.charAt(i1) == text.charAt(j))
                {
                    i1++;
                    j++;
                }
                
                Current.Suffix[k] = ""+c.substring(0,i1);
                
                if(Current.next[k] != -1)
                {
                   trie.get(trie.size()-1).next[letterToIndex(c.charAt(i1))] = Current.next[k];    
                }
                
                Current.next[k] = trie.size()-1;
                trie.get(Current.next[k]).Suffix[letterToIndex(c.charAt(i1))] = c.substring(i1);
                trie.get(Current.next[k]).Suffix[letterToIndex(text.charAt(j))] = text.substring(j);
            }
        }
        
        for(int i = 0; i < trie.size(); i++)
        {
            for(String SS : trie.get(i).Suffix)
            {
                if(!"".equals(SS))
                    result.add(SS);
            }
        }
            
        return result;
    }


    static public void main(String[] args) throws IOException {
        new SuffixTree().run();
    }

    public void print(List<String> x) {
        for (String a : x) {
            System.out.println(a);
        }
    }

    public void run() throws IOException {
        FastScanner scanner = new FastScanner();
        String text = scanner.next();
        List<String> edges = computeSuffixTreeEdges(text);
        print(edges);
    }
}
