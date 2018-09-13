import java.util.*;

public class ShortestPaths {

    private static void shortestPaths(ArrayList<Integer>[] adj, ArrayList<Integer>[] cost, int s, long[] distance, int[] reachable, int[] shortest) {
      //write your code here
       ArrayList<Integer> r = new ArrayList<>(adj.length);
       int[] vi = new int[adj.length];
               
       r.add(s);
       distance[s] = 0;   
       
            while (!r.isEmpty()) 
            {
                int e = r.get(r.size()-1);
                reachable[e] = 1;

                while (vi[e] < adj[e].size()) 
                {
                    if (shortest[adj[e].get(vi[e])] != 0) 
                    {
                        if (shortest[e] == 0) 
                        {
                            shortest[adj[e].get(vi[e])] = 0;
                            vi[adj[e].get(vi[e])] = 0;
                            break;
                        }
                        
                        int k = r.indexOf(adj[e].get(vi[e]));
                        if (k != -1) 
                        {
                            if (distance[adj[e].get(vi[e])] > distance[e] + cost[e].get(vi[e])) 
                            {
                                //distance[adj[e].get(vi[e])] = distance[e] + cost[e].get(vi[e]);
                                
                                for (int i = k; i < r.size()-1; i++) 
                                {
                                    //distance[r.get(i+1)] = distance[r.get(i)] + cost[r.get(i)].get(vi[r.get(i)]-1);
                                    shortest[r.get(i)] = 0;
                                    vi[r.get(i)] = 0;
                                }
                                shortest[e] = 0;
                                vi[e] = -1;
                            }
                        } 
                        else if (distance[adj[e].get(vi[e])] > distance[e] + cost[e].get(vi[e])) 
                        {
                            distance[adj[e].get(vi[e])] = distance[e] + cost[e].get(vi[e]);
                                
                            vi[adj[e].get(vi[e])] = 0;
                            break;
                        }
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
                    vi[e]++;
                }
            }
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
        Long t = System.currentTimeMillis();
        Random R = new Random(t);
        
        for (int i = 0; i < m; i++) {
            int x, y, w;
            x = scanner.nextInt();
            y = scanner.nextInt();
            w = scanner.nextInt();
            adj[x - 1].add(y - 1);
            cost[x - 1].add(w);
        }

        int s = scanner.nextInt()-1;
        long distance[] = new long[n];
        int reachable[] = new int[n];
        int shortest[] = new int[n];
        for (int i = 0; i < n; i++) {
            distance[i] = Long.MAX_VALUE;
            reachable[i] = 0;
            shortest[i] = 1;
        }
        shortestPaths(adj, cost, s, distance, reachable, shortest);
        for (int i = 0; i < n; i++) {
            if (reachable[i] == 0) {
                System.out.println('*');
            } else if (shortest[i] == 0) {
                System.out.println('-');
            } else {
                System.out.println(distance[i]);
            }
        }
    }

}

