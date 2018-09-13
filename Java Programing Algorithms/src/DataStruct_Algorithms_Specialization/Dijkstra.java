import java.util.*;

 class flight_cost{
    
    protected flight_cost(int city, int cost) {
        this.city = city;
        this.cost = cost;
    }

    protected  int city;
    protected int cost;
    
    @Override
    public String toString()
    {
     return city+";"+cost;   
    }
}

public class Dijkstra {
    
    	public static void insert(flight_cost[] mF, flight_cost x, int dst[], int position)
        {
	        int pos = position;
		while(pos>1 && mF[pos/2].cost > x.cost)
                {
			mF[pos]=mF[pos/2];
                        dst[mF[pos].city] = pos;
			pos = pos/2;
		}
                mF[pos] = x;
                dst[x.city] = pos;
	}
	
	public static flight_cost extractMin(flight_cost[] mF, int[] dst, int position)
        {
		flight_cost min = mF[1];
                dst[mF[1].city] = 0;
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
                     dst[mF[k].city] = k;
                     k = smallest;
                     smallest *=2;
		   }
                   else
                       break;
                }
                if(position > 1)
                {
                  mF[k] = mF[position];
                  dst[mF[k].city] = k;
                }
                mF[position]=null;
		return min;
	}
    
    private static int distance(ArrayList<Integer>[] adj, ArrayList<Integer>[] cost, int s, int t) {

        flight_cost[] mF = new flight_cost[adj.length+1];
        int position = 1;
        int dst[] = new int[adj.length];
        Arrays.fill(dst, -1);
        
        insert(mF, new flight_cost(s,0), dst, position);
        position++;
        
        while (position > 1) 
        {
            flight_cost e = extractMin(mF, dst, position);
            position--;
            if(e.city == t)
                return e.cost;
                       
            for (int V = 0; V < adj[e.city].size(); V++) 
            {
                int pd = e.cost+cost[e.city].get(V);
                
                if (dst[adj[e.city].get(V)] > 0) 
                {
                    if(pd < mF[dst[adj[e.city].get(V)]].cost) 
                    {
                        int pos = dst[adj[e.city].get(V)];
                        while (pos > 1 && mF[pos / 2].cost > pd) 
                        {
                            mF[pos] = mF[pos / 2];
                            dst[mF[pos].city] = pos;
                            pos = pos / 2;
                        }
                        mF[pos] = new flight_cost(adj[e.city].get(V), pd);
                        dst[mF[pos].city] = pos;
                    }
                } 
                else if(dst[adj[e.city].get(V)] == -1)
                {
                    insert(mF, new flight_cost(adj[e.city].get(V), pd), dst, position);
                    position++;
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
        ArrayList<Integer>[] cost = (ArrayList<Integer>[])new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
            cost[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            int x, y, w;
            x = scanner.nextInt();
            y = scanner.nextInt();  
            w = scanner.nextInt();
            adj[x - 1].add(y - 1);
            cost[x - 1].add(w);
        }

        int x = scanner.nextInt()-1;
        int y = scanner.nextInt()-1;
    
        System.out.println(distance(adj, cost, x, y));

    }
}

