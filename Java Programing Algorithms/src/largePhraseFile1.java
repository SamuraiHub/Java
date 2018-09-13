import java.io.*;
import java.util.*;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Muaz Aljarhi
 */
public class largePhraseFile1 {
    
    private static final Logger LOG = Logger.getLogger(largePhraseFile.class.getName());
    
    public static void main(String... args) {

        FileInputStream fs = null;
        try 
        {
            fs = new FileInputStream("F:\\Downloads\\Other\\PhrasesST.txt");
            //System.out.println("Top phrases from the file: " + Arrays.toString(Arrays.copyOfRange(new largePhraseFile().getTopPhrases(fs, 100), 0, 100)));
        } 
        catch (IOException ex) 
        {
            LOG.log(Level.SEVERE, null, ex);
        } 
        finally 
        {
            try 
            {
                if(fs != null)
                   fs.close();
            } 
            catch (IOException ex) 
            {
                LOG.log(Level.SEVERE, null, ex);
            }
        }
    }
    
    //used for loading the pharase file form specified path
    public static FileInputStream getFile(String path) throws FileNotFoundException
    {
        return new FileInputStream(path);
    }
    
    // A node that can reperesent a sequence chars from multiple phrases inside a trie. used for reducing memory and increasing performance
    protected class Node
    {
	public static final int Chars =  38; //letters + nums + space + backslash = 26 + 10 + 1 + 1 = 38
	public static final int NA   = -1; // means the end or leaf of a trie or no next node.
	public int next []; // points to next nodes inside a trie list;
        public String[] s; // a secquence of chars starting that start with a char defined by the index inside the string.
        public int[] count; // holds counts of the number of occurance of phrases ending in a substring that starts with the specified char based on the index.

	Node ()
	{
		next = new int [Chars];
		s = new String[Chars];
                count = new int[Chars];
                Arrays.fill(next, NA); //intially all indexes point to -1 or nothing
                Arrays.fill(s, ""); // initially all indexes are filled with the empty string
                Arrays.fill(count, 0); //intially all coounts for all indexes of starting chars are 0
	}
    }
    
     //converts a character to an index (witch is then used as the position inside the specified array)
     int charToIndex(char Char)
     {
	if(Character.isDigit(Char))
        {
            
            return 26 + Character.digit(Char, 10); // 26 to 35 are reserved for digits
        }
        else if(Char == ' ')
        {
            return 36; // index 36 is for the space
        }
        else if(Char == '\'')
        {
           return 37;   // index 37 is for backslash 
        }
        else
        {
          return Character.toLowerCase(Char) - 'a'; // letters after being converted to lower case have indexes 0 to 25 reserved for them
        }     
     }
    
    //adds the specified phrase to the trie
    public void addPhrase(String phrase, List<Node> trie) 
    {
            Node Current = trie.get(0);
            int j = 0;
            int k = charToIndex(phrase.charAt(0));
            
            String c = Current.s[k];
            
            while(c.length() >= 1 && c.length() <= phrase.substring(j).length() && c.equals(phrase.substring(j, j+c.length())))
            { 
               j+=c.length();
               if(j == phrase.length())
               {
                  Current.count[k]++; // pharse found, add one to the count of the last substring of the phrase. 
                  break;    
               } 
               if(Current.next[k] == -1)
               {
                  trie.add(new Node()); //last part of phrases not found. add a new Node.
                  Current.next[k] =  trie.size()-1; //set the next node of char k (first char in this part of the phrase) to he new node
               }
               
               Current = trie.get(Current.next[k]);
               k = charToIndex(phrase.charAt(j));
               c = Current.s[k];
            }
            
            if("".equals(c))
            {
                Current.s[k] = phrase.substring(j);
                Current.count[k] = 1; // new pharase added, set the count to one. 
            }
            else if(j != phrase.length())
            {
                trie.add(new Node());
                int i1 = 1;
                j++;
               
                while(j < phrase.length() && c.charAt(i1) == phrase.charAt(j))
                {
                    i1++;
                    j++;
                }
                
                Current.s[k] = c.substring(0,i1);
                int c1 = charToIndex(c.charAt(i1));
                trie.get(trie.size()-1).next[c1] = Current.next[k];     
                Current.next[k] = trie.size()-1;
                int c0 = charToIndex(c.charAt(0));
                trie.get(trie.size()-1).s[c1] = c.substring(i1);
                trie.get(trie.size()-1).count[c1] = Current.count[c0];
                if(j == phrase.length())
                {
                   Current.count[c0] = 1;
                }
                else
                { 
                   Current.count[c0] = 0; 
                   int s1 = charToIndex(phrase.charAt(j));
                   trie.get(trie.size()-1).s[s1] = phrase.substring(j);
                   trie.get(trie.size()-1).count[s1] = 1;
                }
            }
    }
        //Inserts a phrase x with specifed count ct into the heap and modifies it accordingly 
        public void insert(String[] phrases, int[] cs, String x, int ct, List<Integer>[] phraselocs, int pos)
        {
		while(pos>1 && cs[pos >> 1] > ct)
                {
			phrases[pos] =  phrases[pos >> 1];
                        cs[pos] = cs[pos >> 1];
                        List<Integer> h = phraselocs[Math.abs(phrases[pos].hashCode()%phraselocs.length)];
                        h.add(pos);
			pos = pos >> 1;
                        h.remove((Integer) pos);
		}
                phrases[pos] = x;
                cs[pos] = ct;
                phraselocs[Math.abs(x.hashCode()%phraselocs.length)].add(pos); // use abs just incase the hashcode is negative
	}
	
        //Extracts the min phrase of the heap and modifies it accordingly. pos = num elements + 1 of the phrases array
	public String ExtractMin(String[] phrases, int[] cs, List<Integer>[] phraselocs, int pos)
        {
	    String min = phrases[1];
            phraselocs[Math.abs(min.hashCode()%phraselocs.length)].remove((Integer) 1);

            int k = 1, smallest = 2;
            while (smallest < pos) {
                if (smallest + 1 < pos && cs[smallest] > cs[smallest + 1]) 
                {
                    smallest++;
                }
                if (cs[pos] > cs[smallest]) 
                {
                    phrases[k] = phrases[smallest];
                    cs[k] = cs[smallest];
                    List<Integer> h = phraselocs[Math.abs(phrases[k].hashCode()%phraselocs.length)];
                    h.remove((Integer) smallest);
                    h.add(k);
                    k = smallest;
                    smallest <<= 1;
                } else 
                {
                    break;
                }
            }
            phrases[k] = phrases[pos];
            cs[k] = cs[pos];
            List<Integer> h = phraselocs[Math.abs(phrases[k].hashCode()%phraselocs.length)];
            h.remove((Integer) pos);
            h.add(k);
            phrases[pos] = null;
            cs[pos] = 0;
            return min;
	}
        
        // modifies the array heaps accordingly by setting a count of a phrase (indexed by pos) to the input higher number ct. hsize is the current size of heap 
        public void IncMaximum(String[] phrases, int[] cs, int ct, List<Integer>[] phraselocs, int pos, int hsize)
        {
            String S = phrases[pos];
            
            int k = pos, smallest = pos << 1;
            while (smallest < hsize) 
            {
                if (smallest + 1 < cs.length && cs[smallest] > cs[smallest + 1]) 
                {
                    smallest++;
                }
                if (ct > cs[smallest]) 
                {
                    phrases[k] = phrases[smallest];
                    cs[k] = cs[smallest];
                    List<Integer> h = phraselocs[Math.abs(phrases[k].hashCode()%phraselocs.length)];
                    h.remove((Integer) smallest);
                    h.add(k);
                    k = smallest;
                    smallest <<= 1;
                } 
                else 
                {
                    break;
                }
            }
            phrases[k] = S;
            cs[k] = ct;
            List<Integer> h = phraselocs[Math.abs(phrases[k].hashCode()%phraselocs.length)];
            h.remove((Integer) pos);
            h.add(k);
        }
        
    /**
     * adds the phrases from the list trie to the specified heap (topPhrasesh) or modifies the heap if it is full. Only phrases with most count are included. 
     * takes no more than O(number of phrases in the trie * log(limit)
     * @param trie the trie containing the list of phrases.
     * @param topPhrasesh the array heap for storing the most counted phrases.
     * @param pc the array for storing the count of each phrase.
     * @param phraselocs an array of hashsets which stores the locations of the strings inside the heap. 
     * A String's hash code modulus array size is used as the array index
     * @return current length of the rrray heaps (number of current top phrases+1)
     * //used only for the initial set of phrases
     */
    public int setTopPhrases(List<Node> trie, String[] topPhrasesh, int[] pc, List<Integer>[] phraselocs)
    {  
        int k = 1; //index inside the heap
        String a = ""; //a pharase or part of a pharase inside the trie
        List<Integer> trieStack = new ArrayList<>(); // stores path through the trie
        List<Integer> TSPos = new ArrayList<>(); // stores the current position inside a node of the trie along the path
        //(i.e. the index of the stating char the path the trie continued from). 
        trieStack.add(0);
        TSPos.add(0);

        while(TSPos.get(0) < Node.Chars && "".equals(trie.get(0).s[TSPos.get(0)]))
        {
               TSPos.set(0, TSPos.get(0) + 1); 
        }
        if(TSPos.get(0) == Node.Chars) // trie is empty just return 1
            return 1;

        
        while(!trieStack.isEmpty())
        {
            int m = trieStack.size()-1;
            Node Current = trie.get(trieStack.get(m));
            
            a = a + Current.s[TSPos.get(m)];
            int sc = Current.count[TSPos.get(m)];
            
            if(sc > 0)
            {      
                if(k == pc.length)
                {                    
                    if(sc > pc[1])
                    {
                        ExtractMin(topPhrasesh, pc, phraselocs, k-1);
                        insert(topPhrasesh, pc, a, sc, phraselocs, k-1);
                    }
                }
                else
                {    
                    insert(topPhrasesh, pc, a, sc, phraselocs, k);
                    k++;        
                }
            }
            
            if(Current.next[TSPos.get(m)] != -1)
            {
                trieStack.add(Current.next[TSPos.get(m)]);
                TSPos.add(0);
                while("".equals(Current.s[TSPos.get(m)]))
                {
                  TSPos.set(m, TSPos.get(m) + 1); 
                }
            }
            else
            {
                a = a.substring(0, a.length() - Current.s[TSPos.get(m)].length());
                TSPos.set(m, TSPos.get(m) + 1);
                while(TSPos.get(m) < Node.Chars && "".equals(Current.s[TSPos.get(m)]))
                {
                  TSPos.set(m, TSPos.get(m) + 1); 
                }
                
                while(TSPos.get(m) == Node.Chars)
                {
                    trieStack.remove(m);
                    TSPos.remove(m);
                    if(!TSPos.isEmpty())
                    { 
                       m = TSPos.size()-1;
                       Current = trie.get(trieStack.get(m));
                       a = a.substring(0, a.length() - Current.s[TSPos.get(m)].length());
                       TSPos.set(m, TSPos.get(m) + 1);
                       while(TSPos.get(m) < Node.Chars && "".equals(Current.s[TSPos.get(m)]))
                       {
                          TSPos.set(m, TSPos.get(m) + 1); 
                       }
                    }
                    else
                        break;
                }
            }
        }
        
        return k;
    }
    
    /**
     * adds the phrases from the list trie to the specified sorted array (topPhrasesh) and/or modifies the array if it is full. Only phrases with most count are included. 
     * takes no more than O(number of phrases in the trie * log(limit)
     * @param trie the trie containing the list of phrases.
     * @param topPhrasesh the array heap for storing the most counted phrases.
     * @param pc the array for storing the count of each phrase.
     * @param k the current number of top phrases. Maybe less than the limit+1 or the length of topPhrasesh. 
     * @param phraselocs an array of hashsets which stores the locations of the strings inside the heap. 
     * A String's hash code modulus array size is used as the array index 
     * @return current length of the array heaps (number of current top phrases+1)
     * used for subsequent sets of phrases (which where obtained form the same file)
     */
    public int modifyTopPhrases(List<Node> trie, String[] topPhrasesh, int[] pc, int k, List<Integer>[] phraselocs)
    {          
        String a = ""; //a pharase or part of a pharase inside the trie
        List<Integer> trieStack = new ArrayList<>(); // stores path through the trie
        List<Integer> TSPos = new ArrayList<>(); // stores the current position inside a node of the trie along the path
        //(i.e. the index of the stating char the path the trie continued from). 
        trieStack.add(0);
        TSPos.add(0);

        while(TSPos.get(0) < Node.Chars && "".equals(trie.get(0).s[TSPos.get(0)]))
        {
               TSPos.set(0, TSPos.get(0) + 1); 
        }
        if(TSPos.get(0) == Node.Chars) // trie is empty just return 1
            return 1;
        
        while(!trieStack.isEmpty())
        {
            int m = trieStack.size()-1;
            Node Current = trie.get(trieStack.get(m));
            
            a = a + Current.s[TSPos.get(m)];
            int sc = Current.count[TSPos.get(m)];
            
            if(sc > 0)
            {
                List<Integer> h = phraselocs[Math.abs(a.hashCode()%phraselocs.length)];
                int i = 0;
                
                for(int j : h) 
                {
                    if(topPhrasesh[j].equals(a))
                    {
                        i = j;
                        break;
                    }
                }
                
                if(i >= 1)
                {
                    IncMaximum(topPhrasesh, pc, pc[i]+sc,phraselocs,i, k);
                }
                else
                { 
                   if(k == pc.length)
                   {                      
                      if(sc > pc[1])
                      {
                         ExtractMin(topPhrasesh, pc, phraselocs, k-1);
                         insert(topPhrasesh, pc, a, sc, phraselocs, k-1);
                      }
                   }
                   else
                   {    
                      insert(topPhrasesh, pc, a, sc, phraselocs, k);
                      k++;        
                   }
                }
            }
            
            if(Current.next[TSPos.get(m)] != -1)
            {
                trieStack.add(Current.next[TSPos.get(m)]);
                TSPos.add(0);
                while("".equals(Current.s[TSPos.get(m)]))
                {
                  TSPos.set(m, TSPos.get(m) + 1); 
                }
            }
            else
            {
                a = a.substring(0, a.length() - Current.s[TSPos.get(m)].length());
                TSPos.set(m, TSPos.get(m) + 1);
                while(TSPos.get(m) < Node.Chars && "".equals(Current.s[TSPos.get(m)]))
                {
                  TSPos.set(m, TSPos.get(m) + 1); 
                }
                
                while(TSPos.get(m) == Node.Chars)
                {
                    trieStack.remove(m);
                    TSPos.remove(m);
                    if(!TSPos.isEmpty())
                    { 
                       m = TSPos.size()-1;
                       Current = trie.get(trieStack.get(m));
                       a = a.substring(0, a.length() - Current.s[TSPos.get(m)].length());
                       TSPos.set(m, TSPos.get(m) + 1);
                       while(TSPos.get(m) < Node.Chars && "".equals(Current.s[TSPos.get(m)]))
                       {
                          TSPos.set(m, TSPos.get(m) + 1); 
                       }
                    }
                    else
                        break;
                }
            }
        }
        
        return k;
    }
        
    /**
     * Returns the an array that contains the most frequently used phrases.
     * Phrase selection will be based on the number of times it
     * occurred in the file. Time is approximately O(n) + O(nlog(n)) = O(nlog(n))
     * @param inputStream the stream to be used to read from
     * @param limit the maximum top most used phrases to be used
     * @return An array of the top most frequently used phrases
     * @throws java.io.IOException if there was an error reading from file
     */
    public String[] getTopPhrases(InputStream inputStream, int limit) throws IOException 
    {
        //Create a trie, the last node containing the end substring of the pharase includes also the count of the phrase
        List<Node> trie = new ArrayList<>(500000);
        String[] topPhrasesh = new String[5000000]; // top phrases. 1 added for better processing
        int[] topPc = new int[5000000]; // count of top phrases
        List<Integer>[] phraselocs = (ArrayList<Integer>[]) new ArrayList[10000]; //new HashSet[limit < 100 ? limit : (limit/100)+1]; 
        //stores the locations of strings inside a heap in a way similar to a hashtable or map by using 
        //the hashcode(of thestring) mod phraseloc length as index for faster lookup. useful for modiying specific elements 
        for(int i = 0; i < phraselocs.length; i++)
            phraselocs[i] = new ArrayList();
        
        int k = 1; //number of top phrases + 1. Initially one
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String line = bufferedReader.readLine();
        //Read every line of the file.
        while (line != null) 
        {    
            trie.add(new Node());
            int n = 0;
            do 
            {
                //Split the line to get the phrases.
                StringTokenizer linePhrases = new StringTokenizer(line, "|");

                n++;
                //Read every phrase.
                String lpn = linePhrases.nextToken();
                addPhrase(lpn.substring(0, lpn.length() - 1), trie); //note there is a space after the first phrase
                for (int i = 0; i < 48; i++) 
                {
                    /*Check if the phrase is already in the trie.
                          If true, then increment the value by 1. Else add the phrase
                          as the new entry in the trie.*/
                    lpn = linePhrases.nextToken();
                    addPhrase(lpn.substring(1, lpn.length() - 1), trie); //note there is a space before and after each subsequent phrase
                }
                lpn = linePhrases.nextToken();
                    addPhrase(lpn.substring(1, lpn.length()), trie); //note there is a space before last phrase
                line = bufferedReader.readLine();
                
            } while (n < 100000 && line != null);
            
            if (k > 1) 
            {
                k = modifyTopPhrases(trie, topPhrasesh, topPc, k, phraselocs); //subsequent sets
            } 
            else 
            {
                k = setTopPhrases(trie, topPhrasesh, topPc, phraselocs); //initial set
            }
            trie.clear();
        }
        k--;
        String[] topPhrases = new String[k];
        for(int i = k-1; i >= 0; i--)
        {
            if(i < 50)
               System.out.println(topPhrasesh[1]+"="+topPc[1]);
            topPhrases[i] = ExtractMin(topPhrasesh, topPc, phraselocs, i+1);
        }

        return topPhrases;
    }
    
    // more memory consumed version of topPhrases. Used only for testing that the better version works correctly.
    public String[] getTopPhrasesT(InputStream inputStream, int limit) throws IOException 
    {
        //Create a map, where the key is the phrase,
        //and the value is the number of times phrase occurred in the file.
        Map<String, Integer> topPhrases = new LinkedHashMap<>();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = null;

            //Read every line of the file.
            while((line = bufferedReader.readLine()) != null ) 
            {
                //Split the line to get the phrases.
                StringTokenizer linePhrases = new StringTokenizer(line, "|");
                
                //Read every phrase.
                String lpn = linePhrases.nextToken();
                lpn = lpn.substring(0, lpn.length() - 1); //note there is a space after the first phrase
                //Check if the phrase is already in the collection.
                //If true, then increment the value by 1. Else add the phrase
                //as the new entry to the collection.
                if (topPhrases.containsKey(lpn)) 
                {
                    topPhrases.put(lpn, topPhrases.get(lpn)+ 1);
                } 
                else 
                {
                    topPhrases.put(lpn, 1);
                }

                for (int i = 0; i < 48; i++) 
                {
                    lpn = linePhrases.nextToken();
                    lpn = lpn.substring(1, lpn.length() - 1); //note there is a space before and after each subsequent phrase
                    
                    if (topPhrases.containsKey(lpn)) {
                        topPhrases.put(lpn, topPhrases.get(lpn)+ 1);
                    } else {
                        topPhrases.put(lpn, 1);
                    }
                }
                
                lpn = linePhrases.nextToken();
                    lpn = lpn.substring(1); //note there is a space before the last phrase
                    
                    if (topPhrases.containsKey(lpn)) {
                        topPhrases.put(lpn, topPhrases.get(lpn)+ 1);
                    } else {
                        topPhrases.put(lpn, 1);
                    }
            }

            //Sort the collection by Map value.
            //Limit the collection to 100000.
            topPhrases = topPhrases.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .limit(limit)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (v1,v2)->v1, LinkedHashMap::new));
        
        return topPhrases.keySet().toArray(new String[0]);
    }
}
