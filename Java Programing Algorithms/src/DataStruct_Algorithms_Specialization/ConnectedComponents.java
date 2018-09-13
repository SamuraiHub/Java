import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Scanner;

public class ConnectedComponents {
    private static int numberOfComponents(ArrayList<Integer>[] adj) {
        int result = 1;
        //write your code here
        LinkedList<Integer> r = new LinkedList<>();
        r.addAll(adj[0]);
        HashSet<Integer> v = new HashSet<>();
        v.add(0);
        v.addAll(adj[0]);
        
        while(!r.isEmpty())
        {
            int e = r.removeFirst();
     
            for(Integer n : adj[e])
            {
               if(!v.contains(n))
               {
                  v.add(n);
                  r.add(n);
               }
            }
        }
        
        for(int i = 1; i < adj.length; i++)
        {
            if(!v.contains(i))
            {
                result++;  
                v.add(i);
                r.addAll(adj[i]);
                v.addAll(adj[i]);
                
                while (!r.isEmpty()) 
                {
                    int e = r.removeFirst();

                    for(Integer n : adj[e])
                    {
                        if (!v.contains(n)) 
                        {
                            v.add(n);
                            r.add(n);
                        }
                    }
                }
            }
        }
        
        return result;
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
        System.out.println(numberOfComponents(adj));
    }
}

