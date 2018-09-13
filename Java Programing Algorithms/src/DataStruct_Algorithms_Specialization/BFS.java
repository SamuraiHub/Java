import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class BFS {
    private static int distance(ArrayList<Integer>[] adj, int s, int t) {
        //write your code here
               ArrayDeque<Integer> r = new ArrayDeque<>(adj.length/2);
        boolean[] v = new boolean[adj.length];
        int[] d = new int[adj.length];
                    
        r.add(s);
        v[s] = true;
        d[s] = 0;
        
        while (!r.isEmpty()) 
        {
            int e = r.remove();

            for (int V : adj[e]) 
            {
                if (!v[V]) 
                {
                    r.add(V);
                    d[V] = d[e]+1;
                    v[V] = true;
                    if(V == t)
                    {
                        return d[V];
                    }
                } 
            }
        }
        return -1;
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
            adj[y - 1].add(x - 1);
        }
        int x = scanner.nextInt() - 1;
        int y = scanner.nextInt() - 1;
        System.out.println(distance(adj, x, y));
    }
}

