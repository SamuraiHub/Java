import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Scanner;

public class StronglyConnected {
    private static int numberOfStronglyConnectedComponents(ArrayList<Integer>[] adj) {
        //write your code here
        ArrayList<Integer> r = new ArrayList<>(adj.length/2); //temp graph path
        int[] v = new int[adj.length]; // visited nodes 
        Arrays.fill(v,-1);
        int[] vi = new int[adj.length]; // indexes of of adjacent node from current node
        boolean[] op = new boolean[adj.length]; // true for all nodes on path, false for a node not on path
        ArrayList<HashSet<Integer>> cc = new ArrayList<>(adj.length); // strongly connected nodes
        int[] lcp = new int[adj.length]; // nodes with lowest path index in connected components
        ArrayList<Integer> p = new ArrayList<>(adj.length/2); //temp graph path
        
        int sc = 0;
        int index = 0;
        for(int j = 0; j < adj.length; j++)
        {            
            if(v[j] > -1)
            {
              continue;
            }   
            r.add(j);
            lcp[j] = index;
            v[j] = index;
            index++;
            op[j] = true;
            p.add(j);

            while (!r.isEmpty()) 
            {
                int e = r.get(r.size()-1);
                
                while (vi[e] < adj[e].size() && v[adj[e].get(vi[e])] != -1) 
                {
                    if (op[adj[e].get(vi[e])]) 
                    {
                       lcp[e] = Math.min(lcp[e], v[adj[e].get(vi[e])]);
                    }
                    
                    vi[e]++;
                }
                if(vi[e] < adj[e].size())
                {
                    r.add(adj[e].get(vi[e]));
                    p.add(adj[e].get(vi[e]));
                    v[adj[e].get(vi[e])] = index;
                    lcp[adj[e].get(vi[e])] = index;
                    index++;
                    op[adj[e].get(vi[e])] = true;
                    vi[e]++;
                    continue;
                }

                if(lcp[e] == v[e])
                { 
                   int w;
                   cc.add(new HashSet<>());
                   do
                   {
                       w = p.remove(p.size()-1);
                       op[w] = false;
                       cc.get(sc).add(w);
                   }
                   while(w != e);
                   sc++;
                }
                
                e = r.remove(r.size()-1);
                
                if(r.size() > 0)
                       lcp[r.get(r.size()-1)] = Math.min(lcp[e], lcp[r.get(r.size()-1)]);
            }
        }
     
        return sc;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        ArrayList<Integer>[] adj = (ArrayList<Integer>[])new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < m; i++) {
            int x, y;
            x = scanner.nextInt();
            y = scanner.nextInt();
            adj[x - 1].add(y - 1);
        }
        System.out.println(numberOfStronglyConnectedComponents(adj));
    }
}

