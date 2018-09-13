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
public class RopeIntranet {

        private static BufferedReader reader;
        private static BufferedWriter writer;
        private static int[] A;
        private static int[] B;
        private static char ch;

    /**
     * @param args the command line arguments
     */

         private static int intersectionPoints()
         {
             int NOI = 0;

             for(int j = 0; j<A.length; j++)
             {
                 for(int k = j+1; k<A.length; k++)
                 {
                   if((A[j] < A[j+1] && B[j] > B[j+1]) || (A[j] > A[j+1] && B[j] < B[j+1]))
                   {
                       NOI++;
                   }
                 }
             }
             return NOI;
        }

    public static void main(String[] args) throws IOException {
        // TODO code application logic here

        reader = new BufferedReader(new FileReader("H:/Muaz/Java Programing Algorithms/test/GoogleCodeJamContests/Rope Intranet/A-small-attempt0.in"));
        writer = new BufferedWriter(new FileWriter("H:/Muaz/Java Programing Algorithms/test/GoogleCodeJamContests/Rope Intranet/A-small-attempt0.out"));

      int n = 1;

       int wA = 0;

       ch = (char)reader.read();

       while(ch >= '0' && ch <= '9')
       {
          wA = wA * 10 + (ch - '0');

         ch = (char)reader.read();
       }

       int T = wA;

       ch = (char)reader.read();

      for(int i = 0; i<T; i++)
      {
       
         wA = 0;

       ch = (char)reader.read();

       while(ch >= '0' && ch <= '9')
       {
          wA = wA * 10 + (ch - '0');

         ch = (char)reader.read();
       }

       ch = (char)reader.read();

       int N = wA;

       A = new int[N];
       B = new int[N];

       for(int j = 0; j<N; j++)
      {
       wA = 0;

       ch = (char)reader.read();

       while(ch >= '0' && ch <= '9')
       {
          wA = wA * 10 + (ch - '0');

         ch = (char)reader.read();
       }

       A[j] = wA;

       wA = 0;

        ch = (char)reader.read(); // read next char after space

         while(ch >= '0' && ch <= '9')
       {
          wA = wA * 10 + (ch - '0');

         ch = (char)reader.read();
       }

         B[j] = wA;

        ch = (char)reader.read();

        ch = (char)reader.read();

      }
          writer.write("Case #" + n + ": " + intersectionPoints() + "\n");
          writer.flush();
          n++;
      }
       reader.close();
       writer.close();
    }
}
