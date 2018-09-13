import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Scanner;

public class Reachability {
    private static int reach(ArrayList<Integer>[] adj, int x, int y) {
        //write your code here
        LinkedList<Integer> r = new LinkedList<>();
        r.addAll(adj[x]);
        HashSet<Integer> v = new HashSet<>();
        v.add(x);
        v.addAll(adj[x]);
        
        while(!r.isEmpty())
        {
            int e = r.removeFirst();
            if(e == y)
                return 1;
            
            for(Integer i : adj[e])
            {
               if(!v.contains(i))
               {
                  v.add(i);
                  r.add(i);
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
        System.out.println(reach(adj, x, y));
    }
}

