import java.util.ArrayList;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Scanner;

public class Bipartite {
    private static int bipartite(ArrayList<Integer>[] adj) {
        //write your code here
        ArrayDeque<Integer> r = new ArrayDeque<>(adj.length/2);
        boolean[] v = new boolean[adj.length];
        HashSet<Integer> p1 = new HashSet<>();
        HashSet<Integer> p2 = new HashSet<>();
                      
        r.add(0);
        v[0] = true;
        p1.add(0);
        
        while (!r.isEmpty()) 
        {
            int e = r.removeFirst();

            for (int V : adj[e]) 
            {
                if (!v[V]) 
                {
                    r.add(V);
                    if (p1.contains(e)) 
                    {
                        p2.add(V);
                    } 
                    else if (p2.contains(e)) 
                    {
                        p1.add(V);
                    }
                    v[V] = true;
                } 
                else if ((p1.contains(e) && p1.contains(V)) || (p2.contains(e) && p2.contains(V))) 
                {
                    return 0;
                }
            }
        }
        
        return 1;
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
        System.out.println(bipartite(adj));
    }
}

