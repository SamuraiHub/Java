/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataStruct_Algorithms_Specialization;

import java.util.Arrays;
import java.util.Random;

/**
 *
 * @author Muaz Aljarhi
 */
public class HeapComparisonTest {
    
    
    
    static class Entry implements Comparable<Entry>
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
            
            @Override
            public String toString()
            {
                return String.valueOf(cost);
            }
    }
    
        public static void insert(Entry[] mF, Entry x, int position)
        {
	        int pos = position;
		while(pos>1 && mF[pos/2].cost > x.cost)
                {
			mF[pos]=mF[pos/2];
			pos = pos/2;
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
                     smallest *=2;
		   }
                   else
                       break;
                }
                mF[k] = mF[position];
                mF[position]=null;
		return min;
	}
        
        public static void main(String[] args)
        {
          Random R = new Random();
          int n = 300000;
          long[] cost = new long[n];
          for(int j = 0; j < n; j++)
          {
              cost[j] = R.nextInt(1000000);
          }
          
          //FibonacciHeap fh = new FibonacciHeap();
          Entry[] qs = new Entry[n];
          Entry[] bh = new Entry[n+1];
          long[] a1 = new long[n];  
          long[] a2 = new long[n];          
          
          long t1 = System.nanoTime();
          
          for(int i = 0; i < 200; i++)
          {
              for(int j = 0; j < n; j++)
              {
                qs[j] = new Entry(cost[j], j);
              }
              Arrays.sort(qs);
              for(int j = 0; j < n; j++)
              {
                a1[j] = qs[j].cost;
              }
          }
          
          long t2 = System.nanoTime();
          
          System.out.println("FibonacciHeap Time: "+(t2-t1)/1000000000.0);
          
          t1 = System.nanoTime();
          
          for(int i = 0; i < 200; i++)
          {
              for(int j = 0; j < n; j++)
              {
                insert(bh, new Entry(cost[j], j), j+1);
              }
              int m = 0;
              for(int j = n+1; j > 1; j--)
              {
                  a2[m] = extractMin(bh, j).cost;
                  m++;
              }
          }
          
          t2 = System.nanoTime();
          
          System.out.println("BinaryHeap Time: "+(t2-t1)/1000000000.0);
          

          System.out.println("Heaps Exteractions are Equal: "+(Arrays.equals(a1, a2) ? "TRUE!" : "FALSE!"));        
        }
}
