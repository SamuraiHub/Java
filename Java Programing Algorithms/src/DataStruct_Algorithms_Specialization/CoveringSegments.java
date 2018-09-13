import java.util.*;

public class CoveringSegments {

    private static int[] optimalPoints(Segment[] segments) {
        //write your code here
        int[] tpoints = new int[segments.length];
        
        Arrays.sort(segments, new Segment(0,0)); int j = 0, m = 0; boolean t = false;
        
        for (int i = 1; i < segments.length; i++) {
            
            if(segments[i].start <= segments[j].end)
            {
                j = segments[j].end <= segments[i].end ? j : i;
                t = true;
            }
            else
            {
               tpoints[m] = t ? segments[j].end : segments[j].start;
                m++;
                j = i;
                t = false;
            }
        }
        
        tpoints[m] = t ? segments[j].end : segments[j].start;
        m++;
        
        int[] points = new int[m];
        
        System.arraycopy(tpoints, 0, points, 0, m);
            
        return points;
    }

    private static class Segment implements Comparator<Segment>{
        int start, end;

        Segment(int start, int end) {
            this.start = start;
            this.end = end;
        }

        public int compare(Segment o1, Segment o2) {
            return Integer.compare(o1.start, o2.start);
        }
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        Segment[] segments = new Segment[n];
        for (int i = 0; i < n; i++) {
            int start, end;
            start = scanner.nextInt();
            end = scanner.nextInt();
            segments[i] = new Segment(start, end);
        }
        int[] points = optimalPoints(segments);
        System.out.println(points.length);
        for (int point : points) {
            System.out.print(point + " ");
        }
    }
    

}
 
