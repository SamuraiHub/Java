import java.util.ArrayList;
import java.util.Scanner;

public class NegativeCycle {
    private static int negativeCycle(ArrayList<Integer>[] adj, ArrayList<Integer>[] cost) {
        // write your code here
        ArrayList<Integer> r = new ArrayList<>(adj.length);
        boolean[] v = new boolean[adj.length];
        int[] vi = new int[adj.length];
        int[] cs = new int[adj.length];
        
        for(int j = 0; j < adj.length; j++)
        {
            if(v[j])
              continue;
                
            r.add(j);
              
            while (!r.isEmpty()) 
            {
                int e = r.get(r.size()-1);
                v[e] = true;
                
                while (vi[e] < adj[e].size() && v[adj[e].get(vi[e])]) 
                {
                    if(r.contains(adj[e].get(vi[e])) && cs[adj[e].get(vi[e])] > cs[e]+cost[e].get(vi[e]))
                    {
                       return 1;  
                    }

                    vi[e]++;
                }
                
                if (vi[e] == adj[e].size()) 
                {
                    r.remove(r.size()-1);
                } 
                else 
                {
                    r.add(adj[e].get(vi[e]));
                    cs[adj[e].get(vi[e])] = cs[e]+cost[e].get(vi[e]);
                    vi[e]++;
                }
            }
        }
        
        return 0;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        ArrayList<Integer>[] adj = (ArrayList<Integer>[])new ArrayList[n];
        ArrayList<Integer>[] cost = (ArrayList<Integer>[])new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<Integer>();
            cost[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < m; i++) {
            int x, y, w;
            x = scanner.nextInt();
            y = scanner.nextInt();
            w = scanner.nextInt();
            adj[x - 1].add(y - 1);
            cost[x - 1].add(w);
        }
        System.out.println(negativeCycle(adj, cost));
    }
}

