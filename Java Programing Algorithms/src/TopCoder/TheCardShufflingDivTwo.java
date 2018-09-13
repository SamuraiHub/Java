/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Administrator
 */
public class TheCardShufflingDivTwo {

    public TheCardShufflingDivTwo (){

    }

    public int shuffle(int n, int m)
    {

      int[] md = new int[n];
      int[] dl;

      if(n%2 == 0)
      dl = new int[(n/2)];
       else
      dl = new int[(n/2)+1];

      int[] dr = new int[(n/2)];
      for(int i = 0; i<n; i++)
      {
       md[i] = i+1;
      }

      for(int i =0; i<m; i++)
      {
       for(int j = 0; j<n; j++)
       {
        if(j%2 == 0)
       {
        dl[(j/2)] = md[j];
       }
       else
       {
        dr[(j/2)] = md[j];
       }
       }
        for(int j = 0; j<dl.length; j++)
        {
         md[n-1-j] = dl[dl.length-1-j];
        }
        for(int j = 0; j<(n/2); j++)
        {
         md[(n/2)-1-j] = dr[(n/2)-1-j];
        }
      }
       return md[0];
    }

    public static void main(String[] args) {
        // TODO code application logic here
        TheCardShufflingDivTwo tcs2 = new TheCardShufflingDivTwo();
        System.out.println(tcs2.shuffle(17, 9));
    }

}
