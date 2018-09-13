package DataStruct_Algorithms_Specialization;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;

public class FriendSuggestion {
    private static class Impl {
        // Number of nodes
        int n;
        // adj[0] and cost[0] store the initial graph, adj[1] and cost[1] store the reversed graph.
        // Each graph is stored as array of adjacency lists for each node. adj stores the edges,
        // and cost stores their costs.
        ArrayList<Integer>[][] adj;
        ArrayList<Integer>[][] cost;
        //distance[0] and distance[1] correspond to distance estimates in the forward and backward searches.
        Long[][] distance;
        // Two priority queues, one for forward and one for backward search.
        Entry[][] queue;
        int[] size; // size(+1) of each queue 
        //indexes of nodes in the priority queues (if they are visited)
        int dst[][];
        
        // List of nodes preprocessed by either forward or backword searches
        ArrayList<Integer> workset;
        final Long INFINITY = Long.MAX_VALUE / 4;

        Impl(int n) {
            this.n = n;

            workset = new ArrayList<Integer>(n/2);
            distance = new Long[][] {new Long[n], new Long[n]};
            for (int i = 0; i < n; ++i) {
                distance[0][i] = distance[1][i] = INFINITY;
            }
            queue = new Entry[2][n < 30 ? n : n/10];
            size = new int[2];
            dst = new int[2][n];
        }

        // Reinitialize the data structures before new query after the previous query
        void clear() {
            for (int v : workset) {
                distance[0][v] = distance[1][v] = INFINITY;
            }
            workset.clear();
            Arrays.fill(queue[0], null);
            Arrays.fill(queue[1], null);
            size[0] = 1;
            size[1] = 1;
            Arrays.fill(dst[0], -1);
            Arrays.fill(dst[1], -1);
        }

        // Try to relax the distance from direction side to node v using value dist.
        void visit(int side, int v, Long dist) 
        {
            // Implement this method yourself
            if(dst[side][v] == -1)
            {
              distance[side][v] = dist;
              if(size[side] == queue[side].length)
              {
                Entry[] newQueue = new Entry[size[side] + (3 * size[side] >> 2)];
                System.arraycopy(queue[side], 0, newQueue, 0, size[side]);
                queue[side] = newQueue;
              }
              insert(queue[side], new Entry(dist, v), dst[side], size[side]);
              size[side]++;
              workset.add(v);
            }
            else if(dist < distance[side][v])
            {
              distance[side][v] = dist;  
              changePriority(queue[side], dst[side], v, dist); 
            }
        }

        // Returns the distance from s to t in the graph.
        Long query(int s, int t) 
        {
            clear();
            //List of total diatances between start and end passing through each node
            long TotalDistance = INFINITY;
            visit(0, s, 0L);
            visit(1, t, 0L);
            //Implement the rest of the algorithm yourself
              Entry e = extractMin(queue[0], dst[0], size, 0);
              
              Entry f = extractMin(queue[1], dst[1], size, 1);
              
              if(f.node == e.node)
              {
                  return 0L;
              }
              
              for (int V = 0; V < adj[0][e.node].size(); V++) 
              {
                 long dist = e.cost+cost[0][e.node].get(V); 
                    visit(0, adj[0][e.node].get(V), dist);
              }
              
              for (int V = 0; V < adj[1][f.node].size(); V++) 
              {
                 long dist = f.cost+cost[1][f.node].get(V);
                     visit(1, adj[1][f.node].get(V), dist);
              }
              
              if(dst[0][f.node] != -1 && distance[1][f.node]+distance[0][f.node] < TotalDistance)
                  TotalDistance = distance[1][f.node]+distance[0][f.node];
             
            while(size[0] > 1 && size[1] > 1)
            {
              e = extractMin(queue[0], dst[0], size, 0);
              
              if(dst[1][e.node] != -1 && distance[0][e.node]+distance[1][e.node] < TotalDistance)
                  TotalDistance = distance[0][e.node]+distance[1][e.node];
              
              
              if(dst[1][e.node] == 0)
              {  
                 return TotalDistance;
              }
              
              for (int V = 0; V < adj[0][e.node].size(); V++) 
              {
                long dist = e.cost+cost[0][e.node].get(V); 
                visit(0, adj[0][e.node].get(V), dist);
              }
               
              f = extractMin(queue[1], dst[1], size, 1);
              
              if(dst[0][f.node] != -1 && distance[1][f.node]+distance[0][f.node] < TotalDistance)
                  TotalDistance = distance[1][f.node]+distance[0][f.node];

              if(dst[0][f.node] == 0)
              {  
                 return TotalDistance;
              }
                            
              for (int V = 0; V < adj[1][f.node].size(); V++) 
              {
                 long dist = f.cost+cost[1][f.node].get(V);
                 visit(1, adj[1][f.node].get(V), dist);
              }
            }
              return -1L;
        }

        class Entry
        {
            Long cost;
            int node;
          
            public Entry(Long cost, int node)
            {
                this.cost = cost;
                this.node = node;
            }
        }
        
        public void insert(Entry[] mF, Entry x, int dst[], int position)
        {
	        int pos = position;
		while(pos>1 && mF[pos/2].cost > x.cost)
                {
			mF[pos]=mF[pos/2];
                        dst[mF[pos].node] = pos;
			pos = pos/2;
		}
                mF[pos] = x;
                dst[x.node] = pos;
	}
        
        public void changePriority(Entry[] mF, int[] dst, int node, long distance)
        {
            int pos = dst[node];
                        while (pos > 1 && mF[pos / 2].cost > distance) 
                        {
                            mF[pos] = mF[pos / 2];
                            dst[mF[pos].node] = pos;
                            pos = pos / 2;
                        }
                        mF[pos] = new Entry(distance, node);
                        dst[node] = pos;
        }
	
	public Entry extractMin(Entry[] mF, int[] dst, int[] size, int side)
        {
		Entry min = mF[1];
                dst[mF[1].node] = 0;
		size[side]--;
			
                int k = 1, smallest = 2;
                while(smallest < size[side])
                {
		   if(smallest+1 < size[side] && mF[smallest].cost > mF[smallest+1].cost)
                   {
                       smallest++;
		   }
		   if(mF[size[side]].cost > mF[smallest].cost)
                   {
                     mF[k] = mF[smallest];
                     dst[mF[k].node] = k;
                     k = smallest;
                     smallest *=2;
		   }
                   else
                       break;
                }
                if(size[side] > 1)
                {
                  mF[k] = mF[size[side]];
                  dst[mF[k].node] = k;
                }
                mF[size[side]]=null;
		return min;
	}
    }
    
    public static long[] Run1(String s)
    {
        Scanner in = new Scanner(s);
        int n = in.nextInt();
        int m = in.nextInt();
        Impl bidij = new Impl(n);
        bidij.adj = (ArrayList<Integer>[][])new ArrayList[2][];
        bidij.cost = (ArrayList<Integer>[][])new ArrayList[2][];
        for (int side = 0; side < 2; ++side) {
            bidij.adj[side] = (ArrayList<Integer>[])new ArrayList[n];
            bidij.cost[side] = (ArrayList<Integer>[])new ArrayList[n];
            for (int i = 0; i < n; i++) {
                bidij.adj[side][i] = new ArrayList<Integer>();
                bidij.cost[side][i] = new ArrayList<Integer>();
            }
        }

        for (int i = 0; i < m; i++) {
            int x, y, c;
            x = in.nextInt();
            y = in.nextInt();
            c = in.nextInt();
            bidij.adj[0][x - 1].add(y - 1);
            bidij.cost[0][x - 1].add(c);
            bidij.adj[1][y - 1].add(x - 1);
            bidij.cost[1][y - 1].add(c);
        }

        int t = in.nextInt();
        
        long[] r = new long[t];

        for (int i = 0; i < t; i++) {
            int u, v;
            u = in.nextInt();
            v = in.nextInt();
            r[i] = bidij.query(u-1, v-1);
        }
        return r;
    }
    
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        Impl bidij = new Impl(n);
        bidij.adj = (ArrayList<Integer>[][])new ArrayList[2][];
        bidij.cost = (ArrayList<Integer>[][])new ArrayList[2][];
        for (int side = 0; side < 2; ++side) {
            bidij.adj[side] = (ArrayList<Integer>[])new ArrayList[n];
            bidij.cost[side] = (ArrayList<Integer>[])new ArrayList[n];
            for (int i = 0; i < n; i++) {
                bidij.adj[side][i] = new ArrayList<Integer>();
                bidij.cost[side][i] = new ArrayList<Integer>();
            }
        }

        for (int i = 0; i < m; i++) {
            int x, y, c;
            x = in.nextInt();
            y = in.nextInt();
            c = in.nextInt();
            bidij.adj[0][x - 1].add(y - 1);
            bidij.cost[0][x - 1].add(c);
            bidij.adj[1][y - 1].add(x - 1);
            bidij.cost[1][y - 1].add(c);
        }

        int t = in.nextInt();

        for (int i = 0; i < t; i++) {
            int u, v;
            u = in.nextInt();
            v = in.nextInt();
            System.out.println(bidij.query(u-1, v-1));
        } 
    }
}
