package DataStruct_Algorithms_Specialization;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.Random;

public class DistPreprocessSmall {
    private static class Impl {
        // See the descriptions of these fields in the starter for friend_suggestion
        int n;
        ArrayList<Integer>[][] adj;
        ArrayList<Long>[][] cost;
        Long[][] distance;
        ArrayList<Integer> workset;
        final Long INFINITY = Long.MAX_VALUE / 4;
        
        Entry[][] queue;
        int[] size; // size(+1) of each queue 
        //indexes of nodes in the priority queues (if they are visited)
        int dst[][];
 
        // Position of the node in the node ordering
        int[] rank;
        // Level of the node for level heuristic in the node ordering
        int[] level;

        Impl(int n) {
            this.n = n;
            workset = new ArrayList<>();
            rank = new int[n];
            level = new int[n];
            distance = new Long[][] {new Long[n], new Long[n]};
            for (int i = 0; i < n; ++i) {
                distance[0][i] = distance[1][i] = INFINITY;
                level[i] = 0;
                rank[i] = n;
            }
            queue = new Entry[2][n < 30 ? n : n/10];
            size = new int[2];
            dst = new int[2][n];
        }
        
        // Add the shortcuts corresponding to contracting node v. Return v's importance.
        long shortcut(int v, ArrayList<Integer> scs, int[] Neighbors) {
                        
                        scs.clear();
                        ArrayList<Integer> in = adj[1][v];
                        ArrayList<Integer> ot = adj[0][v];
                        long shortcuts = 0L;
                        //long shortcutCover = 0L;
                        ArrayList<Integer> X = new ArrayList<>();
                        ArrayList<Integer> Y = new ArrayList<>();
                        Neighbors[v] = 0;
                        
                        for (int k = 0; k < ot.size(); k++) 
                        {
                            int y = ot.get(k);
                            
                            if(level[y] >= level[v])
                               Y.add(k);
                            else
                            {
                                //ot.remove(k);
                                //cost[0][v].remove(k);
                                //k--;
                                Neighbors[v]++;
                            }
                        }
                        
                        for (int j = 0; j < in.size(); j++) 
                        {
                            int x = in.get(j);
                            
                            if(level[x] >= level[v])
                               X.add(j);
                            else
                            {
                                //in.remove(j);
                                //cost[1][v].remove(j);
                                Neighbors[v]++;
                                //j--;
                            }
                        }
                        
                        if(!Y.isEmpty()) 
                        {
                            for (int j = 0; j < X.size(); j++) 
                            {
                                int x = in.get(X.get(j));
                                for (int k = 0; k < Y.size(); k++) 
                                {
                                    if(Objects.equals(ot.get(k), in.get(j)))
                                        continue;
                                    
                                    int y = ot.get(Y.get(k));
                                    long dist = cost[1][v].get(X.get(j)) + cost[0][v].get(Y.get(k));
                                    
                                    int io = adj[0][x].indexOf(y);
                                    
                                    if(io != -1)
                                    {
                                      long cc = Math.min(cost[0][x].get(io), dist);
                                      cost[0][x].set(io, cc);
                                      io = adj[1][y].indexOf(x);
                                      cost[1][y].set(io, cc);
                                    }
                                    else if (dist <= distance(adj[0], cost[0], x, y, v, dist)) 
                                    {
                                        shortcuts++;
                                        scs.add(X.get(j));
                                        scs.add(Y.get(k));
                                    }
                                }
                            }
                        }

            // Compute the correct values for the above heuristics before computing the node importance
            Long importance = Neighbors[v] + ((shortcuts << 1) - adj[0][v].size() - adj[1][v].size());
            return importance;
        }
        
        public static void insert(Entry[] mF, Entry x, int position)
        {
	        int pos = position;
		while(pos>1 && mF[pos >> 1].cost > x.cost)
                {
			mF[pos]=mF[pos >> 1];
			pos = pos >> 1;
		}
                mF[pos] = x;
	}
	
	public static Entry extractMin(Entry[] mF, int position)
        {
		Entry min = mF[1];
		position--;
			
                int k = 1, smallest = 2;
                while(smallest < position)
                {
		   if(smallest+1 < position && mF[smallest].cost > mF[smallest+1].cost)
                   {
                       smallest++;
		   }
		   if(mF[position].cost > mF[smallest].cost)
                   {
                     mF[k] = mF[smallest];
                     k = smallest;
                     smallest <<=1;
		   }
                   else
                       break;
                }
                mF[k] = mF[position];
                mF[position]=null;
		return min;
	}
        
        // Preprocess the graph
        void preprocess() {
            // This priority queue will contain pairs (importance, node) with the least important node in the head
            Entry[] q = new Entry[n];
            Entry[] q1 = new Entry[n];
            // Implement this method yourself
            int pos = n-1;
            int pos1 = 0;
            int lv = 0;
            int r = 0;
            ArrayList<Integer>[] sc = new ArrayList[n];
            int[] Neighbors = new int[n];
            
            for(int i = 0; i < n; i++)
            {
                sc[i] = new ArrayList<>();
                q[i] = new Entry(shortcut(i,sc[i], Neighbors), i);
                //insert(q, new Entry(shortcut(i,sc[i]), i), i+1);
            }
                         
            while(pos > 0)
            {
                Entry e = q[pos]; 
                //Entry e = extractMin(q, pos);
                pos--;
                int m = e.node;
                if(level[m] > lv)
                {     
                   q1[pos1] = e; 
                   pos1++;
                }
                else
                {
                    rank[m] = r;
                    r++;

                    for (int j = 0; j < sc[m].size(); j += 2) 
                    {
                        int X = sc[m].get(j);
                        int Y = sc[m].get(j + 1);

                        long dist = cost[1][m].get(X) + cost[0][m].get(Y);
                        int x = adj[1][m].get(X);
                        int y = adj[0][m].get(Y);
                        add_edges(x, y, dist);
                    }

                    adj[1][m].stream().filter((in1) -> (level[in1] == level[m])).forEach((in1) -> 
                    {
                        level[in1] = level[m] + 1;
                    });
                    adj[0][m].stream().filter((ot1) -> (level[ot1] == level[m])).forEach((ot1) -> 
                    {
                        level[ot1] = level[m] + 1;
                    });
                }
                if(pos == -1)
                {
                    lv++;
                    if(lv == 1)
                        break;
                    
                    pos = pos1;
                    q = q1;
                    for(int i = 0; i < pos1; i++)
                    {
                      int e1 = q[i].node;  
                      q[i].cost = shortcut(e1,sc[e1], Neighbors);
                      //insert(q, en, i);
                    }
                    q1 = new Entry[pos1];
                    pos1 = 0;
                }
            }
            /*for(int i = 0; i < n; i++)
            {
                ArrayList<Integer> in = adj[1][i];
                ArrayList<Integer> ot = adj[0][i];
                for(int j = 0; j < in.size(); j++)
                {
                    if(rank[i] > rank[in.get(j)])
                    {
                        in.remove(j);
                        cost[1][i].remove(j);
                        j--;
                    }
                }
                for(int j = 0; j < ot.size(); j++)
                {
                    if(rank[i] > rank[ot.get(j)])
                    {
                        ot.remove(j);
                        cost[0][i].remove(j);
                        j--;
                    }
                }
            }*/
        }
        
    private long distance(ArrayList<Integer>[] adj1, ArrayList<Long>[] cost1, int s, int t, int v, long dist) 
    {

        Entry[] mF = new Entry[n < 30 ? n : n/10];
        int position = 1;
        int dst1[] = new int[n];
        Arrays.fill(dst1, -1);
        
        insert(mF, new Entry(0L,s), dst1, position);
        position++;
        int k = 0;
        while (position > 1) 
        {
            Entry e = extractMin(mF, dst1, position);
            if(e.node== t)
                return e.cost;
            if(e.cost >= dist)
                return INFINITY;
            
            k++;
            if(k == 3)
                return INFINITY;
            
            position--;
            for (int V = 0; V < adj1[e.node].size(); V++) 
            {
                int n1 = adj1[e.node].get(V);
                if(n1 == v || level[n1] < level[v])
                    continue;
                
                long pd = e.cost+cost1[e.node].get(V);
                
                if (dst1[n1] > 0) 
                {
                    if(pd < mF[dst1[n1]].cost) 
                    {
                        int pos = dst1[n1];
                        while (pos > 1 && mF[pos / 2].cost > pd) 
                        {
                            mF[pos] = mF[pos / 2];
                            dst1[mF[pos].node] = pos;
                            pos = pos / 2;
                        }
                        mF[pos] = new Entry(pd, n1);
                        dst1[mF[pos].node] = pos;
                    }
                } 
                else if(dst1[n1] == -1)
                {
                    insert(mF, new Entry(pd, n1), dst1, position);
                    position++;
                }
            }
        }
        
        return INFINITY;
    }
        
        private static Entry extractMin(Entry[] mF, int[] dst, int position)
        {
		Entry min = mF[1];
                dst[mF[1].node] = 0;
		position--;
			
                int k = 1, smallest = 2;
                while(smallest < position)
                {
		   if(smallest+1 < position && mF[smallest].cost > mF[smallest+1].cost)
                   {
                       smallest++;
		   }
		   if(mF[position].cost > mF[smallest].cost)
                   {
                     mF[k] = mF[smallest];
                     dst[mF[k].node] = k;
                     k = smallest;
                     smallest *=2;
		   }
                   else
                       break;
                }
                if(position > 1)
                {
                  mF[k] = mF[position];
                  dst[mF[k].node] = k;
                }
                mF[position]=null;
		return min;
	}

        //adds forward and backword edges for the shortcuts
        void add_edges(int u, int v, Long c) {
            for (int i = 0; i < adj[0][u].size(); ++i) {
                int w = adj[0][u].get(i);
                if (w == v) 
                {
                    long cc = Math.min(cost[0][u].get(i), c);
                    cost[0][u].set(i, cc);
                    int In = adj[1][v].indexOf(u);
                    cost[1][v].set(In, cc);
                    return;
                }
            }
            adj[0][u].add(v);
            cost[0][u].add(c);
            adj[1][v].add(u);
            cost[1][v].add(c);
        }

        void clear() {
            workset.stream().forEach((v) -> {
                distance[0][v] = distance[1][v] = INFINITY;
            });
            workset.clear();
            Arrays.fill(queue[0], null);
            Arrays.fill(queue[1], null);
            size[0] = 1;
            size[1] = 1;
            Arrays.fill(dst[0], -1);
            Arrays.fill(dst[1], -1);
        }

        // See the description of this method in the starter for friend_suggestion
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


        // Returns the distance from s to t in the graph
        long query(int s, int t) {
            clear();
            // Implement the rest of the algorithm yourself
            if (s == t) {
                return 0L;
            }
            //List of total diatances between start and end passing through each node
            long TotalDistance = INFINITY;
            visit(0, s, 0L);
            visit(1, t, 0L);

              Entry e = extractMin(queue[0], dst[0], size, 0);
              Entry f = extractMin(queue[1], dst[1], size, 1);
              
              for (int V = 0; V < adj[0][e.node].size(); V++) 
              {
                 long dist = e.cost+cost[0][e.node].get(V);
                 if(rank[adj[0][e.node].get(V)] >= rank[e.node])
                    visit(0, adj[0][e.node].get(V), dist);    
              }
              
              for (int V = 0; V < adj[1][f.node].size(); V++) 
              {
                 long dist = f.cost+cost[1][f.node].get(V);
                 if(rank[adj[1][f.node].get(V)] >= rank[f.node])
                    visit(1, adj[1][f.node].get(V), dist);
              }
              
              if(dst[0][f.node] != -1 && distance[1][f.node]+distance[0][f.node] < TotalDistance)
                  TotalDistance = distance[1][f.node]+distance[0][f.node];

            while(size[0] > 1 || size[1] > 1)
            {
                if(size[0] > 1)
                {    
                   e = extractMin(queue[0], dst[0], size, 0);
                
                   if(dst[1][e.node] != -1 && distance[0][e.node]+distance[1][e.node] < TotalDistance)
                      TotalDistance = distance[0][e.node]+distance[1][e.node];
                
                   if(distance[0][e.node] < TotalDistance)
                   {
                     for (int V = 0; V < adj[0][e.node].size(); V++) 
                     {
                       long dist = e.cost+cost[0][e.node].get(V);
                       if(rank[adj[0][e.node].get(V)] >= rank[e.node])
                          visit(0, adj[0][e.node].get(V), dist); 
                     }
                   }
                   else
                     size[0] = 1;
                }
                
                if(size[1] > 1)
                {
                   f = extractMin(queue[1], dst[1], size, 1);

                   if(dst[0][f.node] != -1 && distance[1][f.node]+distance[0][f.node] < TotalDistance)
                     TotalDistance = distance[1][f.node]+distance[0][f.node];
                
                   if(distance[1][f.node] < TotalDistance)
                   {
                     for (int V = 0; V < adj[1][f.node].size(); V++) 
                     {
                       long dist = f.cost+cost[1][f.node].get(V);
                       if(rank[adj[1][f.node].get(V)] >= rank[f.node])
                          visit(1, adj[1][f.node].get(V), dist); 
                     }
                   }
                   else
                     size[1] = 1;
                }
            }
                
              return TotalDistance == INFINITY ? -1L : TotalDistance;
        }

        class Entry implements Comparable<Entry>
        {
            long cost;
            int node;
          
            public Entry(long cost, int node)
            {
                this.cost = cost;
                this.node = node;
            }

            @Override
            public int compareTo(Entry o) 
            {
                return cost < o.cost ? -1 : cost > o.cost ? 1 : 0;
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
            Entry e = mF[pos]; 
                        while (pos > 1 && mF[pos / 2].cost > distance) 
                        {
                            mF[pos] = mF[pos / 2];
                            dst[mF[pos].node] = pos;
                            pos = pos / 2;
                        }
                        e.cost = distance;
                        mF[pos] = e;
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
    
    public static void run()
    {
        int n = 100000;
        int m = 250000;
        Impl ch = new Impl(n);
        ch.adj = (ArrayList<Integer>[][])new ArrayList[2][];
        ch.cost = (ArrayList<Long>[][])new ArrayList[2][];
        for (int side = 0; side < 2; ++side) {
            ch.adj[side] = (ArrayList<Integer>[])new ArrayList[n];
            ch.cost[side] = (ArrayList<Long>[])new ArrayList[n];
            for (int i = 0; i < n; i++) {
                ch.adj[side][i] = new ArrayList<>();
                ch.cost[side][i] = new ArrayList<>();
            }
        }
        
        long T = System.currentTimeMillis();
        Random R = new Random(1481730090667L);
            
        String t = "95 6 3365 7 33 4861 44 73 6365 36 91 4308 6 50 5427 48 37 9060 61 29 4147 34 36 3831 73 58 6714 23 19 3371 21 42 2431 18 92 8877 51 72 1036 77 14 3198 29 27 4063 7 32 6776 63 "
                + "55 2514 80 77 6947 85 93 3749 6 29 3822 37 3 9151 74 70 5138 26 28 6531 31 34 4277 85 91 3988 94 20 4932 82 54 3000 63 44 2357 52 54 5187 68 89 1219 44 85 701 78 14 7717 46 63 4650 87 59 8147 9 8 6437 42 25 6187 38 28 3742 27 22 8550 36 39 6178 88 28 1773 25 60 1162 62 39 4005 64 "
                + "48 2521 85 42 4916 34 14 4898 98 34 3741 98 22 5922 95 78 8816 36 46 2247 43 4 2073 92 93 5308 46 58 3959 67 34 5911 59 44 5436 90 15 2187 59 11 3052 20 54 3419 91 "
                + "10 1522 87 57 2163 19 68 6057 27 42 2763 17 5 1947 16 50 330 80 81 11285 62 93 5937 64 92 7616 49 74 6300 70 15 1667 40 8 5208 48 55 9179 62 29 2096 24 66 9999 87 57 2163 18 93 8683 6 "
                + "46 8079 98 55 5431 85 50 4199 41 100 8190 44 73 6365 88 52 6846 94 52 6912 97 36 6317 54 30 4202 87 54 2344 67 35 6147 93 94 8772 51 73 1135 71 73 6018 57 67 4843 65 16 3308 6 8 5822 94 "
                + "10 5643 100 13 4457 96 14 4098 20 40 5101 28 87 4839 41 13 3884 62 68 2187 78 51 4839 96 15 5102 100 51 5319 29 91 4574 40 91 5192 62 75 2292 51 29 6638 5 31 1664 53 49 3996 24 8 6931 11 85 2639 74 87 6694 52 37 7748 59 53 5827 36 43 3591 66 61 "
                + "10755 33 80 9187 11 83 1497 16 37 7297 11 76 6232 55 46 6201 86 92 7104 2 11 7226 40 34 4536 88 92 7788 29 89 3788 77 20 2041 3 18 9924 18 16 8800 5 18 2661 35 93 5404";
        
        String[] cc = t.split(" "); 
        int cs = cc.length/3;
        
        String s = "100000 250000";
        
       /* for (int i = 0; i < cs; i++) {
            
            int x = Integer.parseInt(cc[3*i]);
            int y = Integer.parseInt(cc[3*i+1]);
            long c = Long.parseLong(cc[3*i+2]);
            
            ch.adj[0][x - 1].add(y - 1);
            ch.cost[0][x - 1].add(c);
            ch.adj[1][y - 1].add(x - 1);
            ch.cost[1][y - 1].add(c);
            s = s + " " + x + " " + y + " " + c;
        }*/
        
        for (int i = 0; i < m; i++) 
        {
            int x, y;
            long c;
            x = R.nextInt(n)+1;
            y = R.nextInt(n)+1;
            
            while(x == y || ch.adj[0][x - 1].contains(y-1))
            {
               x = R.nextInt(n)+1;
               y = R.nextInt(n)+1; 
            }
            
            c = R.nextInt(200001);
            
            ch.adj[0][x - 1].add(y - 1);
            ch.cost[0][x - 1].add(c);
            ch.adj[1][y - 1].add(x - 1);
            ch.cost[1][y - 1].add(c);
            s = s + " " + x + " " + y + " " + c;
        }
        
        s = s + " " + 30;
        
        System.out.println("Start preprocessing");
        Long t1 = System.nanoTime();
        ch.preprocess();
        Long t2 = System.nanoTime();
        System.out.println("Ready");
        System.out.println("PreProcess Time: "+((t2-t1)/1000000000.0)+"s");
        
        long[] r1 = new long[30];
        
        for (int i = 0; i < 30; i++) {
            int u, v;
            u = R.nextInt(n)+1;
            v = R.nextInt(n)+1;

            s = s + " " + u + " " + v; 
            r1[i] = ch.query(u-1, v-1);
        }
        long[] r2 = FriendSuggestion.Run1(s);
        if(!Arrays.equals(r1, r2))
        {
            System.out.println("Wrong!");
            System.out.println("r: "+T);
            System.out.println("Expected: ");
            System.out.println(Arrays.toString(r2));
            System.out.println("Got: ");
            System.out.println(Arrays.toString(r1));
        }
        else
        {
            System.out.println("Right!");
            //run();
        }
    }

    public static void main(String args[]) {
      /*Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        Impl ch = new Impl(n);
        @SuppressWarnings("unchecked")
        ArrayList<Integer>[][] tmp1 = (ArrayList<Integer>[][])new ArrayList[2][];
        ch.adj = tmp1;
        @SuppressWarnings("unchecked")
        ArrayList<Long>[][] tmp2 = (ArrayList<Long>[][])new ArrayList[2][];
        ch.cost = tmp2;
        for (int side = 0; side < 2; ++side) {
            @SuppressWarnings("unchecked")
            ArrayList<Integer>[] tmp3 = (ArrayList<Integer>[])new ArrayList[n];
            ch.adj[side] = tmp3;
            @SuppressWarnings("unchecked")
            ArrayList<Long>[] tmp4 = (ArrayList<Long>[])new ArrayList[n];
            ch.cost[side] = tmp4;
            for (int i = 0; i < n; i++) {
                ch.adj[side][i] = new ArrayList<Integer>();
                ch.cost[side][i] = new ArrayList<Long>();
            }
        }

        for (int i = 0; i < m; i++) {
            int x, y;
            Long c;
            x = in.nextInt();
            y = in.nextInt();
            c = in.nextLong();
            ch.adj[0][x - 1].add(y - 1);
            ch.cost[0][x - 1].add(c);
            ch.adj[1][y - 1].add(x - 1);
            ch.cost[1][y - 1].add(c);
        }

        ch.preprocess();
        System.out.println("Ready");

        int t = in.nextInt();

        for (int i = 0; i < t; i++) {
            int u, v;
            u = in.nextInt();
            v = in.nextInt();
            System.out.println(ch.query(u-1, v-1));
     }*/
      run();
    }
}
      
