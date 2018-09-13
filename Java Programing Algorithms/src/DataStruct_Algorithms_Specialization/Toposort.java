import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

public class Toposort {
        
    private static Integer[] toposort(ArrayList<Integer>[] adj) {
        //write your code here
        int cs[] = new int[adj.length];
        Integer[] order = new Integer[adj.length];
        int[] vi = new int[adj.length];
        LinkedList<Integer> r = new LinkedList<>();
        boolean[] v = new boolean[adj.length];
        
        for(int j = 0; j < adj.length; j++)
        {
            order[j] = j;
            if(v[j])
              continue;
                
            r.add(j);
              
            while (!r.isEmpty()) 
            {
                int e = r.getLast();

                while (vi[e] < adj[e].size() && v[adj[e].get(vi[e])]) 
                {
                    vi[e]++;
                }

                if (vi[e] == adj[e].size()) 
                {
                    v[e] = true;
                    cs[e] = 0;
                    for (int i = 0; i < adj[e].size(); i++) 
                    {
                       cs[e] = Math.max(cs[e], cs[adj[e].get(i)]);
                    }
                    cs[e] += 1;
                    
                    r.removeLast();
                } 
                else 
                {
                    r.addLast(adj[e].get(vi[e]));
                }
            }
        }
               
        Arrays.sort(order, (Integer o1, Integer o2) -> cs[o1] < cs[o2] ? 1 : cs[o1] == cs[o2] ? 0 : -1);
          
        return order;
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();

        
        ArrayList<Integer>[] adj = (ArrayList<Integer>[])new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<Integer>();
        }

        for (int i = 0; i < m; i++) 
        {
            int x, y;
            x = scanner.nextInt();
            y = scanner.nextInt();
            adj[x - 1].add(y - 1);
        }

        Integer[] order = toposort(adj);
        for (int x : order) {
            System.out.print((x + 1) + " ");
        }
        System.out.println();  
    }
}