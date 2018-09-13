import java.io.*;
import java.math.*;
import java.util.*;

public class NonSharedSubstring implements Runnable {
	
        String solve (String p, String q) 
        {  
            ArrayList<Node> ST  = (ArrayList<Node>) computeSuffixTreeEdges(q+'$');
                    
            ArrayList<Integer> ST2 = new ArrayList<>(p.length());
            ArrayList<Integer> ST3 = new ArrayList<>(p.length());
            ArrayList<String> ST4 = new ArrayList<>(p.length());
            
            for(int j = 0; j < p.length(); j++)
            {
               String SS = ST.get(0).Suffix[letterToIndex(p.charAt(j))];  
               if("".equals(SS))
               {
                   return ""+p.charAt(j);
               }
               
               ST2.add(0);
               ST3.add(0);
               ST4.add(SS);
            }
            
            for (int i = 1; i < p.length(); i++) 
            {
                for(int j = i; j < p.length(); j++)
                {
                    Node NS = ST.get(ST2.get(j-i));
                    
                   String SS = ST4.get(j-i); 
                    
                   if(SS.length()+ST3.get(j-i) > i)
                   {
                     if(SS.charAt(i-ST3.get(j-i)) != p.charAt(j))
                     {
                         return p.substring(j-i, j+1);
                     }
                   }
                   else
                   {
                       ST3.set(j-i, ST3.get(j-i)+SS.length());
                       ST2.set(j-i, NS.next[letterToIndex(SS.charAt(0))]);
                       
                       SS = ST.get(ST2.get(j-i)).Suffix[letterToIndex(p.charAt(j))];
                       if("".equals(SS))
                           return p.substring(j-i, j+1);
                       
                       
                       ST4.set(j-i,SS);
                   }
                }
            }

		return p;
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

    // Build a suffix tree of the string text and return the tree
    // with all of the labels of its edges (the corresponding 
    // substrings of the text).
    public List<Node> computeSuffixTreeEdges(String text) 
    {
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
            
        return trie;
    }

	public void run () {
		try {
			BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
			String p = in.readLine ();
			String q = in.readLine ();

			String ans = solve (p, q);

			System.out.println (ans);
		}
		catch (Throwable e) {
			e.printStackTrace ();
			System.exit (1);
		}
	}

	public static void main (String [] args) {
		new Thread (new NonSharedSubstring ()).start ();
	}
}
