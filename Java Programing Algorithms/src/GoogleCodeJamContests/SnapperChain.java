/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package GoogleCodeJamContests;

import java.io.*;

/**
 *
 * @author Administrator
 */
public class SnapperChain {

    /**
     * @param args the command line arguments
     */
        private static BufferedReader reader;
        private static BufferedWriter writer;
        private static int K;
        private static int N;

    public static String Snap()
    {
     if(K%2 == 0)
     return "OFF";

     if(K<N)
    {
     return "OFF";
    }
     int t = 1;

    for(int i = 1; i<N; i++)
    {
        t = (int) (t + Math.pow(2, i));

        if(K<t)
        {
          return "OFF";
        }
    }

    if((K+1)%(t+1) == 0)
    return "ON";
    else
    return "OFF";
    }


    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        reader = new BufferedReader(new FileReader("H:/Muaz/Java Programing Algorithms/test/GoogleCodeJamContests/Snapper Chain/A-large.in"));
        writer = new BufferedWriter(new FileWriter("H:/Muaz/Java Programing Algorithms/test/GoogleCodeJamContests/Snapper Chain/A-large.out"));

      int n = 1;

      int T = Integer.parseInt(reader.readLine());

      for(int i = 0; i<T; i++)
      {
       K = 0;
       N = 0;

       char ch = (char)reader.read();

       while(ch >= '0' && ch <= '9')
       {
          N = N * 10 + (ch - '0');

         ch = (char)reader.read();
       }

        ch = (char)reader.read(); // read next char after space

         while(ch >= '0' && ch <= '9')
       {
          K = K * 10 + (ch - '0');

         ch = (char)reader.read();
       }

          writer.write("Case #" + n + ": " + Snap() + "\n");
          writer.flush();
          n++;
      }
       reader.close();
       writer.close();
    }

}
