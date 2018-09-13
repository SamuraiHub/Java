package GoogleCodeJamContests;

/**
 *
 * @author Muaz Al-Jarhi
 */
import java.io.*;
import java.util.*;

public class ThemePark {

    /**
     * @param args the command line arguments
     */

    private static BufferedReader reader;
    private static BufferedWriter writer;
    private static Deque<Integer> riders;
    private static int R;
    private static int K;
    private static int N;
    private static int rg;

    public static int rollerCoaster()
    {
      int euros = 0; //profit roller coaster makes today

      int fg = riders.remove(); // goup infront of the queue

      for(int i = 0; i<R; i ++)
      {
         int people = 0; // number of people who will ride in the current round

          int j = 0; // used for checking that no more than the number of groups are riding

         do
        {
          people = people + fg;

          riders.add(fg);

          fg = riders.remove();

          j++;
        }
         while(people + fg <= K && j < riders.size()+1);

         euros = euros + people;
      }

      return euros;
    }

    public static void main(String[] args) throws IOException {
        // TODO code application logic here

        reader = new BufferedReader(new FileReader("H:/Muaz/Java Programing Algorithms/test/GoogleCodeJamContests/Theme Park/C-large.in"));
        writer = new BufferedWriter(new FileWriter("H:/Muaz/Java Programing Algorithms/test/GoogleCodeJamContests/Theme Park/C-large.out"));

        int n = 1;

        int T = Integer.parseInt(reader.readLine());

        char ch = (char)reader.read();

      for(int i = 0; i<T; i++)
      {
       R = 0;
       K = 0;
       N = 0;
       riders = new ArrayDeque<Integer>();
       rg = 0;

       while(ch >= '0' && ch <= '9')
       {
          R = R * 10 + (ch - '0');

         ch = (char)reader.read();
       }

        ch = (char)reader.read(); // read next char after space

       while(ch >= '0' && ch <= '9')
       {
          K = K * 10 + (ch - '0');

         ch = (char)reader.read();
       }

        ch = (char)reader.read(); // read next char after space

         while(ch >= '0' && ch <= '9')
       {
          N = N * 10 + (ch - '0');

         ch = (char)reader.read(); 
       }

         ch = (char)reader.read(); //  read next char after currage return
        
       for(int j =0; j<N; j++)
       {
         while(ch >= '0' && ch <= '9')
       {
          rg = rg * 10 + (ch - '0');

         ch = (char)reader.read();
       }
         riders.add(rg);

         rg = 0;

         ch = (char)reader.read();
       }

          writer.write("Case #" + n + ": " + rollerCoaster() + "\n");
          writer.flush();
          n++;
     }
       reader.close();
       writer.close();
    }
}
