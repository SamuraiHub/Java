package TopCoder;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Administrator
 */

import java.util.*;
public class MatchMaker {
private ArrayList BestMatches;
private String[] memberElements;
private String[] CurrUserElements;
private ArrayList NumAnswers;
private int j;
private int n;

    /**
     * @param args the command line arguments
     */
    public MatchMaker(){
    BestMatches = new ArrayList<String>();
    NumAnswers = new ArrayList<Integer>();

    }

    public String[] getBestMatches(String[] members, String currentUser, int sf)
    {
       for(int i=0; i<members.length; i++)
    {
          if(members[i].contains(currentUser))
    {
          j = i;
    }

    }
    CurrUserElements =  members[j].split(" ");
    for(int i=0; i<members.length; i++)
    {
    if(i!=j)
    {
     memberElements = members[i].split(" ");
     if(CurrUserElements[2].equals(memberElements[1]))
     {
      for(int k = 3; k<memberElements.length; k++)
      {

       if(CurrUserElements[k].equals(memberElements[k]))
       n++;
      }

      if(n>=sf)
      {
        if(BestMatches.isEmpty())
           {
            NumAnswers.add(n);

          BestMatches.add(memberElements[0]);
           }
        else
        {
          for(int k = NumAnswers.size()-1; k>=0; k--)
          {
           if(n <= (Integer) NumAnswers.get(k))
           {
            NumAnswers.add(k+1, n);

            BestMatches.add(k+1, memberElements[0]);
            break;
           }
           if(k==0 && n > (Integer) NumAnswers.get(k))
           {
             NumAnswers.add(k, n);

            BestMatches.add(k, memberElements[0]);
           }
          }
        }
        }
       n  = 0;
      }
      }
     }
    memberElements = new String[BestMatches.size()];
    if(BestMatches.size() == 0)
    return memberElements;


    for(int i = 0; i<BestMatches.size(); i++)
    {
      memberElements[i] = (String) BestMatches.get(i);
    }
     return memberElements;
    }

    public static void main(String[] args) {
        // TODO code application logic here
        MatchMaker mm = new MatchMaker();
        String[] members = {"BETTY F M A A C C", "TOM M F A D C A", "SUE F M D D D D",
 "ELLEN F M A A C A", "JOE M F A A C A", "ED M F A D D A", "SALLY F M C D A B", "MARGE F M A A C C"};
       String[] bm = mm.getBestMatches(members,"JOE", 1);
       for(int i = 0; i<bm.length; i++)
       {
        System.out.println(bm[i]);
       }
    }

}
