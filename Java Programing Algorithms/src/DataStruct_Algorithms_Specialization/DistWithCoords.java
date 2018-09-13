import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;

public class DistWithCoords {
    private static class Impl {
        // Number of nodes
        int n;
        // Coordinates of nodes
        int[] x;
        int[] y;
        // See description of these fields in the starters for friend_suggestion
        ArrayList<Integer>[][] adj;
        ArrayList<Integer>[][] cost;
        long[][] distance;
        Entry[][] queue;
        int[] size; // size(+1) of each queue 
        //indexes of nodes in the priority queues (if they are visited)
        int dst[][];
        ArrayList<Integer> workset;
        final Long INFINITY = Long.MAX_VALUE / 4;
        // shorted distance possible (including elucidean distance) between some of the coordinates of the nodes
        long[][] StraightDistance;
        
        Impl(int n) {
            this.n = n;
            x = new int[n];
            y = new int[n];
            workset = new ArrayList<Integer>();
            distance = new long[][] {new long[n], new long[n]};
            StraightDistance = new long[][] {new long[n], new long[n]};
            for (int i = 0; i < n; ++i) {
                distance[0][i] = distance[1][i] = INFINITY;
                StraightDistance[0][i] = StraightDistance[1][i] = INFINITY;
            }
            queue = new Entry[2][n < 30 ? n : n/10];
            size = new int[2];
            dst = new int[2][n];
        }

        // See the description of this method in the starters for friend_suggestion
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

        // See the description of this method in the starters for friend_suggestion
        void visit(int side, int v, long dist, long rCost) 
        {
            // Implement this method yourself
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
              
              insert(queue[side], new Entry(rCost, v), dst[side], size[side]);
              size[side]++;
              workset.add(v);
            }
            else if(dist < distance[side][v])
            {
              distance[side][v] = dist;  
              changePriority(queue[side], dst[side], v, dist, rCost); 
              
            }
        }
        
        void StraightDistances(int s, int t)
        {
          for(int i = 0; i < n; i++)  
          {     
              StraightDistance[0][i] = ((long)(Math.sqrt(Math.pow(x[i]-x[s], 2.0)+Math.pow(y[i]-y[s], 2.0))+0.5) >> 1)-
                      ((long)(Math.sqrt(Math.pow(x[t]-x[i], 2.0)+Math.pow(y[t]-y[i], 2.0))+0.5) >> 1);
              StraightDistance[1][i] = -StraightDistance[0][i]; 
          } 
        }
        
        // Returns the distance from s to t in the graph.
        Long query(int s, int t) {
            clear();
            // Implement the rest of the algorithm yourself
            if(s == t)
            {
                return 0L;
            }
        
            long TotalDistance = INFINITY;
            StraightDistances(s, t);
            visit(0, s, 0L, 0L);
            visit(1, t, 0L, 0L);

              Entry e = extractMin(queue[0], dst[0], size, 0);
              Entry f = extractMin(queue[1], dst[1], size, 1);     

              for (int V = 0; V < adj[0][s].size(); V++) 
              {
                 visit(0, adj[0][e.node].get(V), distance[0][e.node]+cost[0][e.node].get(V),
                         e.rCost+cost[0][e.node].get(V)+StraightDistance[1][adj[0][e.node].get(V)]+StraightDistance[0][e.node]);
              }
              
              for (int V = 0; V < adj[1][t].size(); V++) 
              {
                 visit(1, adj[1][f.node].get(V), distance[1][f.node]+cost[1][f.node].get(V),
                         f.rCost+cost[1][f.node].get(V)+StraightDistance[0][adj[1][f.node].get(V)]+StraightDistance[1][f.node]);
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
                 visit(0, adj[0][e.node].get(V), distance[0][e.node]+cost[0][e.node].get(V),
                         e.rCost+cost[0][e.node].get(V)+StraightDistance[1][adj[0][e.node].get(V)]+StraightDistance[0][e.node]);
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
                 visit(1, adj[1][f.node].get(V), distance[1][f.node]+cost[1][f.node].get(V),
                         f.rCost+cost[1][f.node].get(V)+StraightDistance[0][adj[1][f.node].get(V)]+StraightDistance[1][f.node]);
              }
            }
            
              return -1L;
        }

        class Entry
        {
            Long rCost; //reduced cost
            int node;
          
            public Entry(Long rCost, int node)
            {
                this.node = node;
                this.rCost = rCost;
            }
        }
               
        public void insert(Entry[] mF, Entry x, int dst[], int position)
        {
	        int pos = position;
		while(pos>1 && mF[pos/2].rCost > x.rCost)
                {
			mF[pos]=mF[pos/2];
                        dst[mF[pos].node] = pos;
			pos = pos/2;
		}
                mF[pos] = x;
                dst[x.node] = pos;
	}
        
        public void changePriority(Entry[] mF, int[] dst, int node, long Distance, long rCost)
        {
            int pos = dst[node];
            Entry e = mF[pos]; 
                        while (pos > 1 && mF[pos / 2].rCost > rCost) 
                        {
                            mF[pos] = mF[pos / 2];
                            dst[mF[pos].node] = pos;
                            pos = pos / 2;
                        }
                        e.rCost = rCost;
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
		   if(smallest+1 < size[side] && mF[smallest].rCost > mF[smallest+1].rCost)
                   {
                       smallest++;
		   }
		   if(mF[size[side]].rCost > mF[smallest].rCost)
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
    
    public static boolean contains(int[] a, int[] b, int x, int y)
    {   
        for(int i = 0; i < a.length; i++)
        {
            if(a[i] == x && b[i] == y)
                return true;
        }
        
        return false;
    }
    
    /*public static void run()
    {
        int n = 100;
        int m = 1000;
        Impl DistWithCoords = new Impl(n);
        DistWithCoords.adj = (ArrayList<Integer>[][])new ArrayList[2][];
        DistWithCoords.cost = (ArrayList<Integer>[][])new ArrayList[2][];
        for (int side = 0; side < 2; ++side) {
            DistWithCoords.adj[side] = (ArrayList<Integer>[])new ArrayList[n];
            DistWithCoords.cost[side] = (ArrayList<Integer>[])new ArrayList[n];
            for (int i = 0; i < n; i++) {
                DistWithCoords.adj[side][i] = new ArrayList<Integer>();
                DistWithCoords.cost[side][i] = new ArrayList<Integer>();
            }
        }
        long T = System.currentTimeMillis();
        Random R = new Random(T);
        
        String t = "3097 9733 4950 275 2506 1855 3995 6281 6802 7863 9181 5848 5707 6125 4493 2397 218 7209 5206 2527 2597 7107 9572 8034 2438 3445 8528 5749 4509 1633 5903 1120 4911 7401 9006 9354 9610 6301 5159 3943 6608 4014 983 6970 7933 3377 "
                + "4504 9327 2598 3013 9854 5588 9425 5619 3829 8108 5363 5695 2848 5596 6671 6205 2703 52 9240 2787 5142 2211 4463 2330 2704 5165 9929 7205 7253 9617 8609 6978 9616 1464 385 6742 8784 2932 4449 8303 4697 5851 114 2124 1158 6795 9479 "
                + "7280 1309 9994 7041 9403 6124 1365 717 955 3397 3039 5203 5855 6835 6922 3659 1121 998 7977 5482 3239 5046 6053 572 9390 1477 2709 5169 9837 6205 3776 4447 3508 3808 9664 4414 4073 142 330 7805 7488 5744 1639 7449 7145 3256 2731 5279 "
                + "5058 646 1988 1626 276 1074 7382 5546 5971 5595 1644 6951 2968 812 5793 5234 3229 111 1757 9472 8058 7864 4104 2697 8600 2593 9010 4426 5205 8315 7664 7162 4601 4242 9832 6825 2201 2695 412 4430 1218 3962 2050 858 6355 9624 6037 8941 9204 4551 6734 8304 2244 6742 5592 4825 678 6034 813";
        String[] cc = t.split(" ");
        
        for (int i = 0; i < n; i++) { 
            //int x, y;
            //x = R.nextInt(35000*2+1)-35000;
            //y = R.nextInt(35000*2+1)-35000;
            //while(contains(DistWithCoords.x, DistWithCoords.y, x, y))
            //{
            //   x = R.nextInt(35000*2+1)-35000;
            //   y = R.nextInt(35000*2+1)-35000; 
           // }
            
            DistWithCoords.x[i] = Integer.parseInt(cc[2*i]);
            DistWithCoords.y[i] = Integer.parseInt(cc[2*i+1]);
        }
        
        t = "95 6 3365 7 33 4861 44 73 6365 36 91 4308 6 50 5427 48 37 9060 61 29 4147 34 36 3831 73 58 6714 23 19 3371 21 42 2431 18 92 8877 51 72 1036 77 14 3198 29 27 4063 7 32 6776 63 "
                + "55 2514 80 77 6947 85 93 3749 6 29 3822 37 3 9151 74 70 5138 26 28 6531 31 34 4277 85 91 3988 94 20 4932 82 54 3000 63 44 2357 52 54 5187 68 89 1219 44 85 701 78 14 7717 46 63 4650 87 59 8147 9 8 6437 42 25 6187 38 28 3742 27 22 8550 36 39 6178 88 28 1773 25 60 1162 62 39 4005 64 "
                + "48 2521 85 42 4916 34 14 4898 98 34 3741 98 22 5922 95 78 8816 36 46 2247 43 4 2073 92 93 5308 46 58 3959 67 34 5911 59 44 5436 90 15 2187 59 11 3052 20 54 3419 91 "
                + "10 1522 87 57 2163 19 68 6057 27 42 2763 17 5 1947 16 50 330 80 81 11285 62 93 5937 64 92 7616 49 74 6300 70 15 1667 40 8 5208 48 55 9179 62 29 2096 24 66 9999 87 57 2163 18 93 8683 6 "
                + "46 8079 98 55 5431 85 50 4199 41 100 8190 44 73 6365 88 52 6846 94 52 6912 97 36 6317 54 30 4202 87 54 2344 67 35 6147 93 94 8772 51 73 1135 71 73 6018 57 67 4843 65 16 3308 6 8 5822 94 "
                + "10 5643 100 13 4457 96 14 4098 20 40 5101 28 87 4839 41 13 3884 62 68 2187 78 51 4839 96 15 5102 100 51 5319 29 91 4574 40 91 5192 62 75 2292 51 29 6638 5 31 1664 53 49 3996 24 8 6931 11 85 2639 74 87 6694 52 37 7748 59 53 5827 36 43 3591 66 61 "
                + "10755 33 80 9187 11 83 1497 16 37 7297 11 76 6232 55 46 6201 86 92 7104 2 11 7226 40 34 4536 88 92 7788 29 89 3788 77 20 2041 3 18 9924 18 16 8800 5 18 2661 35 93 5404";
        
        cc = t.split(" "); int cs = cc.length/3;
        
        String s = "100 1000";
        
        for (int i = 0; i < cs; i++) {
            
            int x = Integer.parseInt(cc[3*i]);
            int y = Integer.parseInt(cc[3*i+1]);
            int c = Integer.parseInt(cc[3*i+2]);
            
            DistWithCoords.adj[0][x - 1].add(y - 1);
            DistWithCoords.cost[0][x - 1].add(c);
            DistWithCoords.adj[1][y - 1].add(x - 1);
            DistWithCoords.cost[1][y - 1].add(c);
            s = s + " " + x + " " + y + " " + c;
        }
        
        for (int i = cs; i < m; i++) 
        {
            int x, y, c;
            x = R.nextInt(n)+1;
            y = R.nextInt(n)+1;
            
            while(x == y || DistWithCoords.adj[0][x - 1].contains(y-1))
            {
               x = R.nextInt(n)+1;
               y = R.nextInt(n)+1; 
            }
            
            c = R.nextInt(100001);
            while(c < DistWithCoords.StraightDistance(x-1, y-1))
            {
              c = R.nextInt(100001);  
            }
            
            DistWithCoords.adj[0][x - 1].add(y - 1);
            DistWithCoords.cost[0][x - 1].add(c);
            DistWithCoords.adj[1][y - 1].add(x - 1);
            DistWithCoords.cost[1][y - 1].add(c);
            s = s + " " + x + " " + y + " " + c;
        }
        
        s = s + " " + 30;
        
        long[] r1 = new long[30];
        
        for (int i = 0; i < 30; i++) {
            int u, v;
            u = R.nextInt(n)+1;
            v = R.nextInt(n)+1;
            s = s + " " + u + " " + v;
            r1[i] = DistWithCoords.query(u-1, v-1);
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
            //System.out.println("Right!");
            run();
        }
    }*/

    public static void main(String args[]) {
       Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        Impl DistWithCoords = new Impl(n);
        DistWithCoords.adj = (ArrayList<Integer>[][])new ArrayList[2][];
        DistWithCoords.cost = (ArrayList<Integer>[][])new ArrayList[2][];
        for (int side = 0; side < 2; ++side) {
            DistWithCoords.adj[side] = (ArrayList<Integer>[])new ArrayList[n];
            DistWithCoords.cost[side] = (ArrayList<Integer>[])new ArrayList[n];
            for (int i = 0; i < n; i++) {
                DistWithCoords.adj[side][i] = new ArrayList<Integer>();
                DistWithCoords.cost[side][i] = new ArrayList<Integer>();
            }
        }

        for (int i = 0; i < n; i++) { 
            int x, y;
            x = in.nextInt();
            y = in.nextInt();
            DistWithCoords.x[i] = x;
            DistWithCoords.y[i] = y;
        }

        for (int i = 0; i < m; i++) {
            int x, y, c;
            x = in.nextInt();
            y = in.nextInt();
            c = in.nextInt();
            DistWithCoords.adj[0][x - 1].add(y - 1);
            DistWithCoords.cost[0][x - 1].add(c);
            DistWithCoords.adj[1][y - 1].add(x - 1);
            DistWithCoords.cost[1][y - 1].add(c);
        }

        int t = in.nextInt();

        for (int i = 0; i < t; i++) {
            int u, v;
            u = in.nextInt();
            v = in.nextInt();
            System.out.println(DistWithCoords.query(u-1, v-1));
        }
    }
}
