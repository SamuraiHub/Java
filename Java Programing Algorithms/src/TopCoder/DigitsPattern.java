/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package TopCoder;
import java.util.*;

/**
 *
 * @author Muaz Al-Jarhi
 */
public class DigitsPattern {

    /**
     * @param args the command line arguments
     */

    public DigitsPattern()
    {

    }

    public String[] recreate(String[] targetPattern)
    {
        String[] resultingPattern = new String[targetPattern.length];

        List<Integer> seqResult = new ArrayList<Integer>();


        for(int i=0; i<targetPattern.length; i++)
      {
          resultingPattern[i] = "";
          for(int j=0; j<targetPattern[i].length(); j++)
          {
              resultingPattern[i] = resultingPattern[i] + ".";
          }
        }


        int l = targetPattern.length-1;
        int w = targetPattern[0].length()-1;
        int k = 0;

        boolean[] dt = new boolean[targetPattern.length];

        for(int i=0; i<targetPattern.length; i++)
            dt[i] = true;

  while (true)
  {
     boolean t = false;

      if(dt[0] == true)
      {
          dt[0] = false;

      if(targetPattern[0].charAt(0) != '.')
      {
      int a = 0;

                 if(targetPattern[0].charAt(1) != '.')
                 {
                     a++;
                 }

                 if(targetPattern[1].charAt(0) != '.')
                 {
                     a++;
                 }
       if(Math.abs((targetPattern[0].charAt(0) - '0')-(a+1)) <= k || (targetPattern[0].charAt(0) - '0') > a-k)
                  {
                    t = true;
                    targetPattern[0] = "."+targetPattern[0].substring(1);
                    resultingPattern[0] = 1+ resultingPattern[0].substring(1);
                    seqResult.add(0); seqResult.add(0);

                      if(resultingPattern[0].charAt(1) != '.')
                 {
                     resultingPattern[0] = resultingPattern[0].substring(0, 1)+(resultingPattern[0].charAt(1)-'0'+1)+resultingPattern[0].substring(2);
                 }

                 if(resultingPattern[1].charAt(0) != '.')
                 {
                     resultingPattern[1] = (resultingPattern[1].charAt(0)-'0'+1)+resultingPattern[1].substring(1);
                 }

      }
      else
      dt[0] = true;
      }

      for(int j=1; j<targetPattern[0].length()-1; j++)
          {
              if(targetPattern[0].charAt(j) != '.')
              {
                  int a = 0;

                 if(targetPattern[0].charAt(j-1) != '.')
                 {
                     a++;
                 }

                 if(targetPattern[0].charAt(j+1) != '.')
                 {
                     a++;
                 }

                 if(targetPattern[1].charAt(j) != '.')
                 {
                     a++;
                 }

                  if(Math.abs((targetPattern[0].charAt(j) - '0')-(a+1)) <= k || (targetPattern[0].charAt(j) - '0') > a-k)
                  {
                    t = true;
                    targetPattern[0] = targetPattern[0].substring(0, j)+"."+targetPattern[0].substring(j+1);
                    resultingPattern[0] = resultingPattern[0].substring(0, j)+1+ resultingPattern[0].substring(j+1);
                    seqResult.add(0); seqResult.add(j);

                      if(resultingPattern[0].charAt(j-1) != '.')
                 {
                     resultingPattern[0] = resultingPattern[0].substring(0, j-1)+(resultingPattern[0].charAt(j-1)-'0'+1)+resultingPattern[0].substring(j);
                 }

                 if(resultingPattern[0].charAt(j+1) != '.')
                 {
                     resultingPattern[0] = resultingPattern[0].substring(0, j+1)+(resultingPattern[0].charAt(j+1)-'0'+1)+resultingPattern[0].substring(j+2);
                 }

                 if(resultingPattern[1].charAt(j) != '.')
                 {
                     resultingPattern[1] = resultingPattern[1].substring(0, j)+(resultingPattern[1].charAt(j)-'0'+1)+resultingPattern[1].substring(j+1);
                 }

                  }
                 else
                 dt[0] = true;
              }
          }

if(targetPattern[0].charAt(w) != '.')
{
     int a = 0;

                 if(targetPattern[0].charAt(w-1) != '.')
                 {
                     a++;
                 }

                 if(targetPattern[1].charAt(w) != '.')
                 {
                     a++;
                 }
       if(Math.abs((targetPattern[0].charAt(w) - '0')-(a+1)) <= k || (targetPattern[0].charAt(w) - '0') > a-k)
                  {
                    t = true;
                    targetPattern[0] = targetPattern[0].substring(0,w)+".";
                    resultingPattern[0] = resultingPattern[0].substring(0,w) + 1;
                    seqResult.add(0); seqResult.add(w);

                      if(resultingPattern[0].charAt(w-1) != '.')
                 {
                     resultingPattern[0] = resultingPattern[0].substring(0, w-1)+(resultingPattern[0].charAt(w-1)-'0'+1)+resultingPattern[0].charAt(w);
                 }

                 if(resultingPattern[1].charAt(w) != '.')
                 {
                     resultingPattern[1] = resultingPattern[1].substring(0,w) + (resultingPattern[1].charAt(w)-'0'+1);
                 }

      }
      else
      dt[0] = true;
      }
      }
      for(int i=1; i<targetPattern.length-1; i++)
      {
          if(dt[i] == true)
          {
              dt[i] = false;
          if(targetPattern[i].charAt(0) != '.')
          {
             int a = 0;

                 if(targetPattern[i-1].charAt(0) != '.')
                 {
                     a++;
                 }

                 if(targetPattern[i+1].charAt(0) != '.')
                 {
                     a++;
                 }

                 if(targetPattern[i].charAt(1) != '.')
                 {
                     a++;
                 }

                  if(Math.abs((targetPattern[i].charAt(0) - '0')-(a+1)) <= k || (targetPattern[i].charAt(0) - '0') > a-k)
                  {
                    t = true;
                    targetPattern[i] = "."+targetPattern[i].substring(1);
                    resultingPattern[i] = 1+ resultingPattern[i].substring(1);
                    seqResult.add(i); seqResult.add(0);

                      if(resultingPattern[i-1].charAt(0) != '.')
                 {
                     resultingPattern[i-1] = (resultingPattern[i-1].charAt(0)-'0'+1)+resultingPattern[i-1].substring(1);
                 }

                 if(resultingPattern[i+1].charAt(0) != '.')
                 {
                     resultingPattern[i+1] = (resultingPattern[i+1].charAt(0)-'0'+1)+resultingPattern[i+1].substring(1);
                 }

                 if(resultingPattern[i].charAt(1) != '.')
                 {
                     resultingPattern[i] = resultingPattern[i].substring(0,1)+(resultingPattern[i].charAt(1)-'0'+1)+resultingPattern[i].substring(2);
                 }

                  }
                  else
                  dt[i] = true;
          }

          for(int j=1; j<targetPattern[i].length()-1; j++)
          {
              if(targetPattern[i].charAt(j) != '.')
              {
                 int a = 0;

                 if(targetPattern[i].charAt(j-1) != '.')
                 {
                     a++;
                 }

                 if(targetPattern[i].charAt(j+1) != '.')
                 {
                     a++;
                 }

                 if(targetPattern[i-1].charAt(j) != '.')
                 {
                     a++;
                 }

                 if(targetPattern[i+1].charAt(j) != '.')
                 {
                     a++;
                 }

                  if(Math.abs((targetPattern[i].charAt(j) - '0')-(a+1)) <= k || (targetPattern[i].charAt(j) - '0') >a-k)
                  {
                    t = true;
                    targetPattern[i] = targetPattern[i].substring(0, j)+'.'+targetPattern[i].substring(j+1);
                    resultingPattern[i] = resultingPattern[i].substring(0, j)+1+ resultingPattern[i].substring(j+1);
                    seqResult.add(i); seqResult.add(j);

                      if(resultingPattern[i].charAt(j-1) != '.')
                 {
                     resultingPattern[i] = resultingPattern[i].substring(0, j-1)+(resultingPattern[i].charAt(j-1)-'0'+1)+resultingPattern[i].substring(j);
                 }

                 if(resultingPattern[i].charAt(j+1) != '.')
                 {
                     resultingPattern[i] = resultingPattern[i].substring(0, j+1)+(resultingPattern[i].charAt(j+1)-'0'+1)+resultingPattern[i].substring(j+2);
                 }

                 if(resultingPattern[i-1].charAt(j) != '.')
                 {
                     resultingPattern[i-1] = resultingPattern[i-1].substring(0, j)+(resultingPattern[i-1].charAt(j)-'0'+1)+resultingPattern[i-1].substring(j+1);
                 }

                 if(resultingPattern[i+1].charAt(j) != '.')
                 {
                     resultingPattern[i+1] = resultingPattern[i+1].substring(0, j)+(resultingPattern[i+1].charAt(j)-'0'+1)+resultingPattern[i+1].substring(j+1);
                 }

                  }
                  else
                  dt[i] = true;
              }
          }

          if(targetPattern[i].charAt(w) != '.')
          {
            int a = 0;

                 if(targetPattern[i-1].charAt(w) != '.')
                 {
                     a++;
                 }

                 if(targetPattern[i+1].charAt(w) != '.')
                 {
                     a++;
                 }

                 if(targetPattern[i].charAt(w-1) != '.')
                 {
                     a++;
                 }

                  if(Math.abs((targetPattern[i].charAt(w) - '0')-(a+1)) <= k || (targetPattern[i].charAt(w) - '0') > a-k)
                  {
                    t = true;
                    targetPattern[i] = targetPattern[i].substring(0, w)+".";
                    resultingPattern[i] = resultingPattern[i].substring(0, w)+1;
                    seqResult.add(i); seqResult.add(w);

                      if(resultingPattern[i-1].charAt(w) != '.')
                 {
                    resultingPattern[i-1] = resultingPattern[i-1].substring(0, w)+(resultingPattern[i-1].charAt(w)-'0'+1);
                 }

                 if(resultingPattern[i+1].charAt(w) != '.')
                 {
                     resultingPattern[i+1] = resultingPattern[i+1].substring(0, w)+(resultingPattern[i+1].charAt(w)-'0'+1);
                 }

                 if(resultingPattern[i].charAt(w-1) != '.')
                 {
                     resultingPattern[i] = resultingPattern[i].substring(0, w-1)+(resultingPattern[i].charAt(w-1)-'0'+1)+resultingPattern[i].charAt(w);
                 }

                  }
                  else
                  dt[i] = true;
          }
      }
      }
      if(dt[l] == true)
      {
          dt[l] = false;
      if(targetPattern[l].charAt(0) !='.')
      {

        int a = 0;

                 if(targetPattern[l-1].charAt(0) != '.')
                 {
                     a++;
                 }

                 if(targetPattern[l].charAt(1) != '.')
                 {
                     a++;
                 }
       if(Math.abs((targetPattern[l].charAt(0) - '0')-(a+1)) <= k || (targetPattern[l].charAt(0) - '0') > a-k)
                  {
                    t = true;
                    targetPattern[l] = "."+targetPattern[l].substring(1);
                    resultingPattern[l] = 1+ resultingPattern[l].substring(1);
                    seqResult.add(l); seqResult.add(0);

                      if(resultingPattern[l].charAt(1) != '.')
                 {
                     resultingPattern[l] = resultingPattern[l].substring(0, 1)+(resultingPattern[l].charAt(1)-'0'+1)+resultingPattern[l].substring(2);
                 }

                 if(resultingPattern[l-1].charAt(0) != '.')
                 {
                     resultingPattern[l-1] = (resultingPattern[l-1].charAt(0)-'0'+1)+resultingPattern[l-1].substring(1);
                 }

      }
        else
        dt[l] = true;
      }

       for(int j=1; j<targetPattern[l].length()-1; j++)
          {
              if(targetPattern[l].charAt(j) != '.')
              {
                 int a = 0;

                 if(targetPattern[l].charAt(j-1) != '.')
                 {
                     a++;
                 }

                 if(targetPattern[l].charAt(j+1) != '.')
                 {
                     a++;
                 }

                 if(targetPattern[l-1].charAt(j) != '.')
                 {
                     a++;
                 }

                  if(Math.abs((targetPattern[l].charAt(j) - '0')-(a+1)) <= k ||(targetPattern[l].charAt(j) - '0') >a-k)
                  {
                    t = true;
                    targetPattern[l] = targetPattern[l].substring(0, j)+"."+targetPattern[l].substring(j+1);
                    resultingPattern[l] = resultingPattern[l].substring(0, j)+1+ resultingPattern[l].substring(j+1);
                    seqResult.add(l); seqResult.add(j);

                      if(resultingPattern[l].charAt(j-1) != '.')
                 {
                     resultingPattern[l] = resultingPattern[l].substring(0, j-1)+(resultingPattern[l].charAt(j-1)-'0'+1)+resultingPattern[l].substring(j);
                 }

                 if(resultingPattern[l].charAt(j+1) != '.')
                 {
                     resultingPattern[l] = resultingPattern[l].substring(0, j+1)+(resultingPattern[l].charAt(j+1)-'0'+1)+resultingPattern[l].substring(j+2);
                 }

                 if(resultingPattern[l-1].charAt(j) != '.')
                 {
                     resultingPattern[l-1] = resultingPattern[l-1].substring(0, j)+(resultingPattern[l-1].charAt(j)-'0'+1)+resultingPattern[l-1].substring(j+1);
                 }

                  }
                 else
                 dt[l] = true;
              }
          }

      if(targetPattern[l].charAt(w) != '.')
      {

         int a = 0;

                 if(targetPattern[l-1].charAt(w) != '.')
                 {
                     a++;
                 }

                 if(targetPattern[l].charAt(w-1) != '.')
                 {
                     a++;
                 }
         if(Math.abs((targetPattern[l].charAt(w) - '0')-(a+1)) <= k || (targetPattern[l].charAt(w)- '0') > a-k)
         {
                    t = true;
                    targetPattern[l] = targetPattern[l].substring(0,w)+".";
                    resultingPattern[l] = resultingPattern[l].substring(0,w) + 1;
                    seqResult.add(l); seqResult.add(w);

                      if(resultingPattern[l].charAt(w-1) != '.')
                 {
                     resultingPattern[l] = resultingPattern[l].substring(0, w-1)+(resultingPattern[l].charAt(w-1)-'0'+1)+resultingPattern[l].charAt(w);
                 }

                 if(resultingPattern[l-1].charAt(w) != '.')
                 {
                     resultingPattern[l-1] = resultingPattern[l-1].substring(0,w) + (resultingPattern[l-1].charAt(w)-'0'+1);
                 }

      }
         else
         dt[l] = true;
      }
      }
      if(t == false)
      {
          boolean n = false;
           for(int i=0; i<targetPattern.length; i++)
           {
               if(dt[i] == true)
               {
                 n = true;
                 break;
               }
          }
         if(n == true)
           k++;
         else
           break;
      }
  }

     // from beg to end
     /*  for(int i=0; i<targetPattern.length; i++)
      {
           if(dt[i] == true)
           {
           for(int j=0; j<targetPattern[i].length(); j++)
          {

              if(targetPattern[i].charAt(j) != '.')
              {
                    resultingPattern[i] = resultingPattern[i].substring(0, j)+"1"+ resultingPattern[i].substring(j+1);

                      if(j-1>=0 && resultingPattern[i].charAt(j-1) != '.')
                 {
                     resultingPattern[i] = resultingPattern[i].substring(0, j-1)+(resultingPattern[i].charAt(j-1)-'0'+1)+resultingPattern[i].substring(j);
                 }

                 if(j+1<targetPattern[i].length() && resultingPattern[i].charAt(j+1) != '.')
                 {
                     resultingPattern[i] = resultingPattern[i].substring(0, j+1)+(resultingPattern[i].charAt(j+1)-'0'+1)+resultingPattern[i].substring(j+2);
                 }

                 if(i-1>=0 && resultingPattern[i-1].charAt(j) != '.')
                 {
                     resultingPattern[i-1] = resultingPattern[i-1].substring(0, j)+(resultingPattern[i-1].charAt(j)-'0'+1)+resultingPattern[i-1].substring(j+1);
                 }

                 if(i+1<targetPattern.length && resultingPattern[i+1].charAt(j) != '.')
                 {
                     resultingPattern[i+1] = resultingPattern[i+1].substring(0, j)+(resultingPattern[i+1].charAt(j)-'0'+1)+resultingPattern[i+1].substring(j+1);
                 }
              }
           }
       }
        } */

       /*
        int[] ret = new int[seqResult.size()];
    int i = 0;
    for (Integer e : seqResult)
        ret[i++] = e.intValue(); */

    return resultingPattern;
  
}

    public int getDistanceBetweenPatterns(String[] TP, String[] RP)
    {
        int S = 0;

       for(int i=0; i<TP.length; i++)
      {
           for(int j=0; j<TP[i].length(); j++)
          {
               if(TP[i].charAt(j) != '.')
               {
                   S = S + Math.abs(TP[i].charAt(j) - RP[i].charAt(j));
               }
           }
        }
        return S;
    }


    public static void main(String[] args) {
        // TODO code application logic here

       long start = System.nanoTime();

        DigitsPattern DP1 = new DigitsPattern();

String[] targetPattern =
{".......................",
"......................4",
".....................51",
"....................115",
"...................5233",
"..................51544",
"...................2453",
".5..................515",
"543....2.............34",
"5452..342.............4",
"331..51244.........3...",
".5....215....5.........",
".......2....532........",
".............2.........",
".......................",
"..4....................",
".......................",
".......................",
".......................",
".......................",
".......................",
".......................",
".......................",
".......................",
".......................",
".......................",
".......................",
".......................",
".......................",
".......................",
".......................",
".......................",
".......................",
".......................",
".......................",
".......................",
"....2..................",
"...212.................",
"..45234................",
".2342324...............",
"135143325....2...5.....",
"5344233555..532........",
"434311252.542323.......",
".4222521.54214315......",
"..25322.4423153133.....",
"...113...35132414......",
"....4.....235545.......",
"............322........",
".............4.........",
"......................."
};

String[] TP  =
{".......................",
"......................4",
".....................51",
"....................115",
"...................5233",
"..................51544",
"...................2453",
".5..................515",
"543....2.............34",
"5452..342.............4",
"331..51244.........3...",
".5....215....5.........",
".......2....532........",
".............2.........",
".......................",
"..4....................",
".......................",
".......................",
".......................",
".......................",
".......................",
".......................",
".......................",
".......................",
".......................",
".......................",
".......................",
".......................",
".......................",
".......................",
".......................",
".......................",
".......................",
".......................",
".......................",
".......................",
"....2..................",       
"...212.................",
"..45234................",
".2342324...............",
"135143325....2...5.....",
"5344233555..532........",
"434311252.542323.......",
".4222521.54214315......",
"..25322.4423153133.....",
"...113...35132414......",
"....4.....235545.......",
"............322........",
".............4.........",
"......................."
};

String[] RP = DP1.recreate(targetPattern);

for(int i = 0; i<targetPattern.length; i++)
{
    System.out.println(RP[i]);
}

int s = DP1.getDistanceBetweenPatterns(TP, RP)+1;

System.out.println("Score = " + s);

double EstTime = (System.nanoTime() - start)/(Math.pow(10, 9));

System.out.println("Run Time = " + EstTime);
    }

}


