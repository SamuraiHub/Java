import java.io.*;
import java.util.*;

class Node
{
	public static final int Letters =  4;
	public static final int NA      = -1;
	public int next [];
        public int s;

	Node ()
	{
		next = new int [Letters];
		Arrays.fill (next, NA);
                s = 0;
	}
}

public class TrieMatching implements Runnable {
	int letterToIndex (char letter)
	{
		switch (letter)
		{
			case 'A': return 0;
			case 'C': return 1;
			case 'G': return 2;
			case 'T': return 3;
			default: assert (false); return Node.NA;
		}
	}
        
        List<Node> buildTrie(String[] patterns) {
        List<Node> trie = new ArrayList<Node>();
        trie.add(new Node());
        
        for(String pattern : patterns)
        {
            Node Current = trie.get(0);
            
            for(int i = 0; i < pattern.length(); i++)
            {
                int l = letterToIndex(pattern.charAt(i));
                Integer k = Current.next[l];
                
                if(k != -1)
                {
                    Current = trie.get(k);
                }
                else
                {
                    Current.next[l] = trie.size();
                    Current.s++;
                    Current = new Node();
                    trie.add(Current);
                }
            }
        }

        return trie;
    }

	List <Integer> solve (String text, int n, String[] patterns, int lgst) {
		List <Integer> result = new ArrayList <Integer> ();
		// write your code here
                List<Node> trie = buildTrie(patterns);
                int r;
                for(int i = 0; i < text.length(); i++)
                {
                    if(text.length() - i < lgst)
                       r = PrefixTrieMatchingL(text, i, trie);
                    else
                       r = PrefixTrieMatching(text, i, trie); 
                    
                    if(r != -1)
                        result.add(r);
                }

		return result;
	}
        
        int PrefixTrieMatching(String text, int i, List<Node> trie)
        {
            int j = i;
            int k = trie.get(0).next[letterToIndex(text.charAt(i))];
            if(k == -1)
                return -1;
            else
            {
              while(trie.get(k).s != 0)
              {
                j++;
                k = trie.get(k).next[letterToIndex(text.charAt(j))];
                if(k == -1)
                  return -1;
              }
            
              return i;
            }
        }
        
        int PrefixTrieMatchingL(String text, int i, List<Node> trie)
        {
            int j = i;
            int k = trie.get(0).next[letterToIndex(text.charAt(i))];
            if(k == -1)
                return -1;
            else
            {
              while(trie.get(k).s != 0)
              {
                j++;
                if(j == text.length())
                    return -1;
                k = trie.get(k).next[letterToIndex(text.charAt(j))];
                if(k == -1)
                  return -1;
              }
            
              return i;
            }
        }
        

	public void run () {
		try {
			BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
			String text = in.readLine ();
		 	int n = Integer.parseInt (in.readLine ());
		 	String[] patterns = new String[n];
                        int lgst = 1;
			for (int i = 0; i < n; i++) {
				patterns[i] = in.readLine();
                                if(patterns[i].length() > lgst)
                                    lgst = patterns[i].length();
			}

			List <Integer> ans = solve (text, n, patterns, lgst);

			for (int j = 0; j < ans.size (); j++) {
				System.out.print ("" + ans.get (j));
				System.out.print (j + 1 < ans.size () ? " " : "\n");
			}
		}
		catch (Throwable e) {
			e.printStackTrace ();
			System.exit (1);
		}
	}

	public static void main (String [] args) {
		new Thread (new TrieMatching ()).start ();
	}
}
