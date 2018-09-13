import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Scanner;

public class Acyclicity {
    public static int acyclic(ArrayList<Integer>[] adj) 
    {
        //write your code here
        LinkedList<Integer> r = new LinkedList<>();
        boolean[] v = new boolean[adj.length];
        int[] vi = new int[adj.length];
        
        for(int j = 0; j < adj.length; j++)
        {
            if(v[j])
              continue;
                
            r.add(j);
            v[j] = true;
              
            while (!r.isEmpty()) 
            {
                int e = r.getLast();
                
                while (vi[e] < adj[e].size() && v[adj[e].get(vi[e])]) 
                {
                    if (r.contains(adj[e].get(vi[e]))) 
                    {
                        return 1;
                    }
                    vi[e]++;
                }
                
                if (vi[e] == adj[e].size()) 
                {
                    r.removeLast();
                } 
                else 
                {
                    r.addLast(adj[e].get(vi[e]));
                    v[adj[e].get(vi[e])] = true;
                }
            }
        }
        
        return 0;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        ArrayList<Integer>[] adj = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < m; i++) {
            int x, y;
            x = scanner.nextInt();
            y = scanner.nextInt();
            adj[x - 1].add(y - 1);
        }
        System.out.println(acyclic(adj));
    }
}

