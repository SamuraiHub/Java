import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class InverseBWT {
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
    
    //Reconstructs a String containing A C G T and $ at the end from its Burrows–Wheeler Transform
    String inverseBWT(String bwt) 
    {
        char[] result = new char[bwt.length()];
        // write your code here
        // to store sorted indicies of the chars of the Burrows–Wheeler Transform
        ArrayList<Integer>[] CFBWT = new ArrayList[4];
        for(int i = 0; i < 4; i++)
        {
            CFBWT[i] = new ArrayList<Integer>(bwt.length()/2);
        }
        int s = 0;
        
        // uses count sort
        for(int i =0; i < bwt.length(); i++)
        {
            switch (bwt.charAt(i)) 
            {
                case 'A':
                    CFBWT[0].add(i);
                    break;
                case 'C':
                    CFBWT[1].add(i);
                    break;
                case 'G':  
                    CFBWT[2].add(i);
                    break;
                case 'T': 
                    CFBWT[3].add(i);
                    break;
                case '$':
                s = i;    
                default:
                    break;
            }
        }
        
        int[] SFBWT = new int[bwt.length()]; // the final sorted array (single)
        
        int j=1;
        // adds the sorted indicies to the single array
        for(int i =0; i <4; i++)
        {
           for(int k = 0; k < CFBWT[i].size(); k++) 
           {
               SFBWT[j] = CFBWT[i].get(k);
               j++;
           }
        }
       
        int l = bwt.length()-1;
        
        for(int i = 0; i < l; i++)
        {
           s = SFBWT[s];
           result[i] = bwt.charAt(s);
        }
        result[l] = '$';

        return String.valueOf(result);
    }

    static public void main(String[] args) throws IOException {
        new InverseBWT().run();
    }

    public void run() throws IOException {
        FastScanner scanner = new FastScanner();
        String bwt = scanner.next();
        System.out.println(inverseBWT(bwt));
    }
}
