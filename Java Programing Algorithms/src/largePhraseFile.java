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
public class largePhraseFile {
    
    private static final Logger LOG = Logger.getLogger(largePhraseFile.class.getName());
    
    public static void main(String... args) 
    {
        File fs = null;
        try 
        {
            fs = new File("F:\\Downloads\\Other\\PhrasesST.txt");
            System.out.println("Top phrases from the file: " + Arrays.toString(Arrays.copyOfRange(new largePhraseFile().getTopPhrases(fs, 100), 0, 100)));
        } 
        catch (IOException ex) 
        {
            LOG.log(Level.SEVERE, null, ex);
        } 
    }
    
    
    private File[] splitFile(File file) throws IOException
    {
      List<Integer> rngs = new ArrayList<>();
      List<Integer> LI = new ArrayList<>();
      List<File> sFiles = new ArrayList<>();

      BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new BufferedInputStream(new FileInputStream(file))));
      BufferedWriter br1;
      BufferedWriter br2;
      
      int k = 1;
      if(file.length() > 1073741824)
      {
            File sf1 = new File("F:\\Downloads\\Other\\Pht" + k);
            sFiles.add(sf1);
            k++;
            File sf2 = new File("F:\\Downloads\\Other\\Pht" + k);
            k++;
            sFiles.add(sf2);
            rngs.add(0);
            rngs.add(13);
            rngs.add(13);
            rngs.add(26);
            LI.add(0);
            LI.add(0);

            br1 = new BufferedWriter(new OutputStreamWriter(new BufferedOutputStream(new FileOutputStream(sf1))));
            br2 = new BufferedWriter(new OutputStreamWriter(new BufferedOutputStream(new FileOutputStream(sf2))));

            String line = null;

            while ((line = bufferedReader.readLine()) != null) 
            {
                //Split the line to get the phrases.
                StringTokenizer linePhrases = new StringTokenizer(line, "|");
                String lpn = linePhrases.nextToken();

                int l = lpn.charAt(0) - 'A';
                if (l < 13) 
                {
                    br1.write(lpn.substring(0, lpn.length() - 1));
                } else 
                {
                    br2.write(lpn.substring(0, lpn.length() - 1));
                }

                for (int j = 0; j < 48; j++) 
                {
                    lpn = linePhrases.nextToken();
                    l = lpn.charAt(1) - 'A';
                    if (l < 13) 
                    {
                        br1.write(lpn.substring(1, lpn.length() - 1));
                    } 
                    else 
                    {
                        br2.write(lpn.substring(1, lpn.length() - 1));
                    }
                }
                lpn = linePhrases.nextToken();
                l = lpn.charAt(1) - 'A';
                if (l < 13) 
                {
                    br1.write(lpn.substring(1));
                } 
                else 
                {
                    br2.write(lpn.substring(1));
                }
            }
            br1.close();
            br2.close();
            bufferedReader.close();
        }
        else
        {
            List<String> topPhrasesh = new ArrayList<>(2000000); // top phrases. 1 added for better processing
            List<Integer> topPc = new ArrayList<>(2000000); // count of top phrases
            List<Integer>[] phraselocs = (ArrayList<Integer>[]) new ArrayList[2000]; 
            //stores the locations of strings inside a heap in a way similar to a hashtable or map by using 
            //the hashcode(of thestring) mod phraseloc length as index for faster lookup. useful for modiying specific elements 
            for(int i = 0; i < phraselocs.length; i++)
              phraselocs[i] = new ArrayList();
            
            String line = null;
            int p = 0;

            while ((line = bufferedReader.readLine()) != null) 
            {
                //Split the line to get the phrases.
                StringTokenizer linePhrases = new StringTokenizer(line, "|");
                String lpn = linePhrases.nextToken();

                List<Integer> h = phraselocs[Math.abs(lpn.hashCode()%phraselocs.length)];
                int i = -1;
                
                for(int j : h) 
                {
                    if(topPhrasesh.get(j).equals(lpn))
                    {
                        i = j;
                        break;
                    }
                }
                
                if(i >= 0)
                {
                   topPc.set(i, topPc.get(i)+1); 
                }
                else
                {
                    topPhrasesh.set(p, lpn);
                    phraselocs[Math.abs(lpn.hashCode()%phraselocs.length)].add(p); // use abs just incase the hashcode is negative
                    p++;
                }

                for (int j = 0; j < 48; j++) 
                {
                    lpn = linePhrases.nextToken();
                    
                    h = phraselocs[Math.abs(lpn.hashCode()%2000)];
                    i = -1;
                
                    for(int n : h) 
                    {
                       if(topPhrasesh.get(n).equals(lpn))
                       {
                          i = n;
                          break;
                       }
                    }
                
                    if(i >= 0)
                    {
                       topPc.set(i, topPc.get(i)+1);
                    }
                    else
                    { 
                       topPhrasesh.set(p, lpn);
                       phraselocs[Math.abs(lpn.hashCode()%phraselocs.length)].add(p); // use abs just incase the hashcode is negative
                       p++;
                    }
                    
                }
                lpn = linePhrases.nextToken();
                
                h = phraselocs[Math.abs(lpn.hashCode()%phraselocs.length)];
                i = -1;
                
                for(int j : h) 
                {
                    if(topPhrasesh.get(j).equals(lpn))
                    {
                        i = j;
                        break;
                    }
                }
                
                if(i >= 0)
                {
                   topPc.set(i, topPc.get(i)+1); 
                }
                else
                {
                    topPhrasesh.set(p, lpn);
                    phraselocs[Math.abs(lpn.hashCode()%phraselocs.length)].add(p); // use abs just incase the hashcode is negative
                    p++;
                }
            }
            
            File sf1 = new File(file.getPath()+"1");
            
            br1 = new BufferedWriter(new OutputStreamWriter(new BufferedOutputStream(new FileOutputStream(sf1))));
            for(int l = 0; l < topPhrasesh.size(); l++)
            {
               br1.write(topPhrasesh.get(l)+","+topPc.get(l));
               br1.newLine();
            }
            br1.close();
            sFiles.add(sf1);
        }
      if(sFiles.size() > 1)
      {
      
      for(int i = 0; i < sFiles.size(); i++)
      {
        bufferedReader = new BufferedReader(new InputStreamReader(new BufferedInputStream(new FileInputStream(sFiles.get(i)))));  
            
        if(sFiles.get(i).length() > 1073741824)
        {
            File CF = sFiles.remove(i);
            int left = rngs.remove(i << 1);
            int right = rngs.remove((i << 1) + 1);

            int mid = (left + right) >> 1;
            File sf1 = new File("F:\\Downloads\\Other\\Pht" + k);
            sFiles.add(sf1);
            k++;
            File sf2 = new File("F:\\Downloads\\Other\\Pht" + k);
            k++;
            sFiles.add(sf2);
            int CLI;
            if(mid == left)
            {
                left = 0;
                rngs.add(0);
                rngs.add(13);
                rngs.add(13);
                rngs.add(26);
                CLI = LI.remove(i)+1;
                LI.add(CLI);
                LI.add(CLI);
            }
            else
            {
               rngs.add(left);
               rngs.add(mid);
               rngs.add(mid);
               rngs.add(right);
               CLI = LI.remove(i);
               LI.add(CLI);
               LI.add(CLI);
            }

            br1 = new BufferedWriter(new OutputStreamWriter(new BufferedOutputStream(new FileOutputStream(sf1))));
            br2 = new BufferedWriter(new OutputStreamWriter(new BufferedOutputStream(new FileOutputStream(sf2))));

            String line = null;

            while ((line = bufferedReader.readLine()) != null) 
            {
                int l = line.charAt(CLI) - 'A';
                if (l < mid) 
                {
                    br1.write(line);
                    br1.newLine();
                } 
                else 
                {
                    br2.write(line);
                    br2.newLine();
                }
            }
            br1.close();
            br2.close();
            bufferedReader.close();
            CF.delete();
            i--;  
        }
        else
        {
            List<String> topPhrasesh = new ArrayList<>(2000000); // top phrases. 1 added for better processing
            List<Integer> topPc = new ArrayList<>(2000000); // count of top phrases
            List<Integer>[] phraselocs = (ArrayList<Integer>[]) new ArrayList[2000]; 
            //stores the locations of strings inside a heap in a way similar to a hashtable or map by using 
            //the hashcode(of thestring) mod phraseloc length as index for faster lookup. useful for modiying specific elements 
            for(int j = 0; j < phraselocs.length; j++)
              phraselocs[j] = new ArrayList();
            
            String line = null;
            int j = 0;

            while ((line = bufferedReader.readLine()) != null) 
            {
               List<Integer> h = phraselocs[Math.abs(line.hashCode()%2000)];
                int m = -1;
                
                for(int n : h) 
                {
                    if(topPhrasesh.get(n).equals(line))
                    {
                        m = n;
                        break;
                    }
                }
                
                if(m >= 0)
                {
                   topPc.set(m, topPc.get(m)+1);
                }
                else
                { 
                    topPhrasesh.set(j, line);
                    topPc.set(j, 1);
                    phraselocs[Math.abs(line.hashCode()%phraselocs.length)].add(j); // use abs just incase the hashcode is negative
                    j++;
                }
            }
            bufferedReader.close();
            
            br1 = new BufferedWriter(new OutputStreamWriter(new BufferedOutputStream(new FileOutputStream(sFiles.get(i)))));
            for(int l = 0; l < topPhrasesh.size(); l++)
            {
               br1.write(topPhrasesh.get(l)+","+topPc.get(l));
               br1.newLine();
            }
            br1.close();
        }
      }
      }
    
      return sFiles.toArray(new File[0]);
    }
      
        //Inserts a phrase x with specifed count ct into the heap and modifies it accordingly 
        public void insert(String[] phrases, int[] cs, String x, int ct, int pos)
        {
		while(pos>1 && cs[pos >> 1] > ct)
                {
			phrases[pos] =  phrases[pos >> 1];
                        cs[pos] = cs[pos >> 1];
			pos = pos >> 1;
		}
                phrases[pos] = x;
                cs[pos] = ct;
	}
	
        //Extracts the min phrase of the heap and modifies it accordingly. pos = num elements + 1 of the phrases array
	public String ExtractMin(String[] phrases, int[] cs, int pos)
        {
	    String min = phrases[1];

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
                    k = smallest;
                    smallest <<= 1;
                } else 
                {
                    break;
                }
            }
            phrases[k] = phrases[pos];
            cs[k] = cs[pos];
            phrases[pos] = null;
            cs[pos] = 0;
            return min;
	}
             
    /**
     * Returns the an array that contains the most frequently used phrases.
     * Phrase selection will be based on the number of times it
     * occurred in the file. Time is approximately O(n) + O(nlog(n)) = O(nlog(n))
     * @param file the file to read from
     * @param limit the maximum top most used phrases to be used
     * @return An array of the top most frequently used phrases
     * @throws java.io.IOException if there was an error reading from file
     */
    public String[] getTopPhrases(File file, int limit) throws IOException 
    {
        String[] topPhrasesh = new String[limit]; // top phrases. 1 added for better processing
        int[] topPc = new int[limit]; // count of top phrases
        
        File[] fs = splitFile(file);
               
        int k = 1; //number of top phrases + 1. Initially one
        
        for(int i = 0; i < fs.length; i++)
        {
            BufferedReader br = new BufferedReader(new InputStreamReader(new BufferedInputStream(new FileInputStream(fs[i]))));
            String line = null;
        
            //Read every line of the file.
            while ((line = br.readLine()) != null) 
            {
                StringTokenizer linePhrases = new StringTokenizer(line, ",");
                String a = linePhrases.nextToken();
                int sc = Integer.parseInt(linePhrases.nextToken());
                if (k == topPc.length) 
                {
                    if (sc > topPc[1]) 
                    {
                        ExtractMin(topPhrasesh, topPc, k - 1);
                        insert(topPhrasesh, topPc, a, sc, k - 1);
                    }
                } 
                else 
                {
                    insert(topPhrasesh, topPc, a, sc, k);
                    k++;
                }
            }
        }
        
        String[] topPhrases = new String[k];
        for(int i = k-1; i >= 0; i--)
        {
            if(i < 50)
               System.out.println(topPhrasesh[1]+"="+topPc[1]);
            topPhrases[i] = ExtractMin(topPhrasesh, topPc, i+1);
        }

        return topPhrases;
    }
}
