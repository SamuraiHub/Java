

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Stack;
import java.util.TimeZone;
import java.util.TreeSet;
import java.util.Vector;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Administrator
 */

public class CCL {

    /**
     * @param args the command line arguments
     */
    public CCL(){

    }

public int[] connect8Pixels(int[] image,int[] cp, int Wh, int pixel, int ts)
{
  int height = image.length/Wh;
  int pi = pixel/Wh;
  int pj = pixel%Wh;

  String Binary  = Integer.toBinaryString(image[pi*Wh+pj]);
     int r = Integer.valueOf(Binary.substring(0, 8), 2);
     int g = Integer.valueOf(Binary.substring(8, 16), 2);
     int b = Integer.valueOf(Binary.substring(16, Binary.length()), 2);

      for(int i1 = pi-1; i1<=pi+1; i1++)
      {
       for(int j1 = pj-1; j1<=pj+1; j1++)
       {
        if(i1>=0 && i1<height && j1>=0 && j1<Wh && (i1 != pi || j1 != pj))
        {
     Binary  = Integer.toBinaryString(image[i1*Wh+j1]);
     int r1 = Integer.valueOf(Binary.substring(0, 8), 2);
     int g1 = Integer.valueOf(Binary.substring(8, 16), 2);
     int b1 = Integer.valueOf(Binary.substring(16, Binary.length()), 2);
     if(Math.abs(r1-r) + Math.abs(g1-g) + Math.abs(b1-b) <= ts)
     {
       cp[i1*Wh+j1] = cp[pi*Wh+pj];
       cp = connect8Pixels(image,cp,Wh,i1*Wh+j1,ts);
     }
       }
      }
     }

  return cp;
}

public int[] connect4Pixels(int[] image,int[] cp, int Wh, int pixel, int ts)
{
  int height = image.length/Wh;
  int pi = pixel/Wh;
  int pj = pixel%Wh;

  String Binary  = Integer.toBinaryString(image[pi*Wh+pj]);
     int r = Integer.valueOf(Binary.substring(0, 8), 2);
     int g = Integer.valueOf(Binary.substring(8, 16), 2);
     int b = Integer.valueOf(Binary.substring(16, Binary.length()), 2);


        if(pi>=0 && pi<height && pj-1>=0 && pj-1<Wh)
        {
     Binary  = Integer.toBinaryString(image[pi*Wh+pj-1]);
     int r1 = Integer.valueOf(Binary.substring(0, 8), 2);
     int g1 = Integer.valueOf(Binary.substring(8, 16), 2);
     int b1 = Integer.valueOf(Binary.substring(16, Binary.length()), 2);
     if(Math.abs(r1-r) + Math.abs(g1-g) + Math.abs(b1-b) <= ts)
     {
       cp[pi*Wh+pj-1] = cp[pi*Wh+pj];
       cp = connect4Pixels(image,cp,Wh,pi*Wh+pj-1,ts);
     }
       }

       if(pi>=0 && pi<height && pj+1>=0 && pj+1<Wh)
        {
     Binary  = Integer.toBinaryString(image[pi*Wh+pj+1]);
     int r1 = Integer.valueOf(Binary.substring(0, 8), 2);
     int g1 = Integer.valueOf(Binary.substring(8, 16), 2);
     int b1 = Integer.valueOf(Binary.substring(16, Binary.length()), 2);
     if(Math.abs(r1-r) + Math.abs(g1-g) + Math.abs(b1-b) <= ts)
     {
       cp[pi*Wh+pj+1] = cp[pi*Wh+pj];
       cp = connect4Pixels(image,cp,Wh,pi*Wh+pj+1,ts);
     }
       }

        if(pi-1>=0 && pi-1<height && pj>=0 && pj<Wh)
        {
     Binary  = Integer.toBinaryString(image[(pi-1)*Wh+pj]);
     int r1 = Integer.valueOf(Binary.substring(0, 8), 2);
     int g1 = Integer.valueOf(Binary.substring(8, 16), 2);
     int b1 = Integer.valueOf(Binary.substring(16, Binary.length()), 2);
     if(Math.abs(r1-r) + Math.abs(g1-g) + Math.abs(b1-b) <= ts)
     {
       cp[(pi-1)*Wh+pj] = cp[pi*Wh+pj];
       cp = connect4Pixels(image,cp,Wh,(pi-1)*Wh+pj,ts);
     }
       }

        if(pi+1>=0 && pi+1<height && pj>=0 && pj<Wh)
        {
     Binary  = Integer.toBinaryString(image[(pi+1)*Wh+pj]);
     int r1 = Integer.valueOf(Binary.substring(0, 8), 2);
     int g1 = Integer.valueOf(Binary.substring(8, 16), 2);
     int b1 = Integer.valueOf(Binary.substring(16, Binary.length()), 2);
     if(Math.abs(r1-r) + Math.abs(g1-g) + Math.abs(b1-b) <= ts)
     {
       cp[(pi+1)*Wh+pj] = cp[pi*Wh+pj];
       cp = connect4Pixels(image,cp,Wh,(pi+1)*Wh+pj,ts);
     }
       }
  return cp;
}

public int[] cuda_ccl(int[] image, int W, int degree_of_connectivity, int threshold)
{
  int height = image.length/W;
  int [] cci = new int[image.length];

  for(int i = 0; i< image.length; i++)
  {
   cci[i] = -1;
  }

  for(int i = 0; i <height; i++)
  {
      for(int j = 1; j<W; j++)
      {
       if(cci[i*W+j] == -1)
       {
        cci[i*W+j] = i*W+j;
       if(degree_of_connectivity == 8)
       {
         cci = connect8Pixels(image,cci,W,i*W+j,threshold);

       }
       else
       cci = connect4Pixels(image,cci,W,i*W+j,threshold);
      }
  }
  }
  return cci;
}
   public static void main(String[] args) throws IOException {

     /*  BufferedReader r = new BufferedReader(new FileReader("G:/Stuff/Muaz/Programming/Java/Java Programing Algorithms/src/TopCoder/enzymes.txt"));

       int a = Integer.parseInt(r.readLine());
       int[] ls = new int[14];

       for(int i = 0; i < a; i++)
       {
         String[] s = r.readLine().split(" ");
            String rs = s[1];

            if(rs.contains("M") || rs.contains("R") || rs.contains("W") || rs.contains("S") || rs.contains("Y") || rs.contains("B") ||
                    rs.contains("K") || rs.contains("V") || rs.contains("H") || rs.contains("D") || rs.contains("X") || rs.contains("N"))
              System.out.println(rs);
       }

       for(int i = 0; i < 14; i++)
       {
          // System.out.println((i+2) + ": " + ls[i]);
       } */
       
       TreeSet<String> t = new TreeSet<String>(); t.add("1"); t.add("2"); t.add("3"); t.add("5"); t.add("9");
       
       String[] l =  t.toArray(new String[10]); System.out.println(l[3]);
      
   }

private static String readLine(FileInputStream in) throws IOException
{
    int i;

    StringBuilder l = new StringBuilder();

    while ((i = in.read()) != -1) {
      char c = (char)i;
      if (c == '\n') break;
      if (c == '\r') ;
      else l.append(c);
    }
    if (l.length() == 0) return null;
    return l.toString();
}

private static List<Character> getArabicChars() {
    final List<Character> chars = new ArrayList<Character>();
    for (int codePoint = 1536; codePoint <= 1791; codePoint++) {
        chars.add((char) codePoint);
    }
    return chars;
}

    public static int month(String month)
    {
      if(month.equals("January"))
      {
         return 1;
      }
     else if(month.equals("February"))
      {
         return 2;
      }
      else if(month.equals("March"))
      {
       return 3;
      }
      else if(month.equals("April"))
      {
        return 4;
      }
      else if(month.equals("May"))
      {
        return 5;
      }
      else if(month.equals("June"))
      {
        return 6;
      }
      else if(month.equals("July"))
      {
         return 7;
      }
      else if(month.equals("August"))
      {
         return 8;
      }
      else if(month.equals("September"))
      {
        return 9;
      }
      else if(month.equals("October"))
      {
        return 10;
      }
      else if(month.equals("November"))
      {
        return 11;
      }
      else if(month.equals("December"))
      {
        return 12;
      }
      else
      {
        return -1;
      }
    }
}
