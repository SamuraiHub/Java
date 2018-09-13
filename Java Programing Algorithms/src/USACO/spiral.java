/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package USACO;

/*
ID: muaz.al1
LANG: JAVA
TASK: spiral
*/
import java.io.*;
import java.util.*;

public class spiral {
  public static void main (String [] args) throws IOException {
    // Use BufferedReader rather than RandomAccessFile; it's much faster
    BufferedReader f = new BufferedReader(new FileReader("test.in"));
                                                  // input file name goes above
    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("test.out")));
    // Use StringTokenizer vs. readLine/split -- lots faster
    StringTokenizer st = new StringTokenizer(f.readLine());
						  // Get line, break into tokens
    int i1 = Integer.parseInt(st.nextToken());    // first integer

    

    for(int i = 0; i< i1; i++)
    {

    }

    out.println(i1);                           // output result
    out.close();                                  // close the output file
    System.exit(0);
  }
}
