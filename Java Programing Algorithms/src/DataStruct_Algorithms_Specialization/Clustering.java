import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Scanner;

public class Clustering {
    
     static class p2p{
    
    protected p2p(int p1, int p2) {
        this.p1 = p1;
        this.p2 = p2;
    }

    protected int p1;
    protected int p2;
    
    @Override
    public String toString()
    {
     return p1+";"+p2;   
    }
}
    
    
    private static double clustering(int[] x, int[] y, int k) {
        //write your code here
        p2p[] p1 = new p2p[x.length*(x.length-1)/2];
        double[][] distance = new double[x.length][x.length];
        
        int n = 0;
        
        for(int i = 0; i < x.length-1; i++)
        {
            for(int j = i+1; j < x.length; j++)
            {
              p1[n] = new p2p(i, j);
   
              distance[i][j] = Math.sqrt(Math.pow(x[i]-x[j], 2.0)+Math.pow(y[i]-y[j], 2.0));
              distance[j][i] = distance[i][j];
              n++;
            }
        }
        
        Arrays.sort(p1, (p2p o1, p2p o2) -> distance[o1.p1][o1.p2] > distance[o2.p1][o2.p2] ? 1 : distance[o1.p1][o1.p2] == distance[o2.p1][o2.p2] ? 0 : -1);
        //Arrays.sort(distance);
        
        ArrayList<HashSet<Integer>> clus = new ArrayList<>(x.length);
        for(int i = 0; i < x.length; i++)
        {
            clus.add(new HashSet<>());
            clus.get(i).add(i);
        }
        
        int j = 0;
        while(clus.size() > k)
        {
           int m = ClusterContains(clus, p1[j].p1);
           int p = ClusterContains(clus, p1[j].p2);
           
           if(m != p)
           {
             clus.get(m).addAll(clus.get(p));
             clus.get(p).clear();
           
              for(p=p+1; p < clus.size(); p++)
              {
                clus.set(p-1, clus.get(p));
              }
           
              clus.remove(clus.size()-1);
           }
           j++;
        }
        int m = ClusterContains(clus, p1[j].p1);
        int p = ClusterContains(clus, p1[j].p2);
        while(m == p)
        {
            j++;
            m = ClusterContains(clus, p1[j].p1);
            p = ClusterContains(clus, p1[j].p2);
        }
        
       
        return distance[p1[j].p1][p1[j].p2];
    }
    
    public static int ClusterContains(ArrayList<HashSet<Integer>> clus, int p)
    {
        int t = -1;
        
        for(int m = 0; clus.get(m).size() > 0; m++)
        {
               if(clus.get(m).contains(p))
               {
                   t = m;
                   break;
               }
        }
        
        return t;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] x = new int[n];
        int[] y = new int[n];
        for (int i = 0; i < n; i++) {
            x[i] = scanner.nextInt();
            y[i] = scanner.nextInt();
        }
        int k = scanner.nextInt();
        System.out.println(clustering(x, y, k));
    }
}

