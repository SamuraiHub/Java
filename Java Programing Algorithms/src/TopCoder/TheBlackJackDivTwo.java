/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Administrator
 */
public class TheBlackJackDivTwo {

public  TheBlackJackDivTwo(){

}
    /**
     * @param args the command line arguments
     */
    public int score(String[] cards)
    {
     int score = 0;
     for(int i = 0; i<cards.length; i++)
     {
      if(Character.isDigit(cards[i].charAt(0)))
      {

        score = score + Character.getNumericValue(cards[i].charAt(0));
      }
      else
      {
       if(cards[i].charAt(0) == 'T' || cards[i].charAt(0) == 'J' || cards[i].charAt(0) == 'Q' || cards[i].charAt(0) == 'K')
       {
         score = score + 10;
       }
       else
       {
        score = score + 11;
       }
      }
     }
     return score;
    }
    public static void main(String[] args) {
        // TODO code application logic here
        TheBlackJackDivTwo TBJT = new TheBlackJackDivTwo();
        String[] cards = {"3S", "KC", "AS", "7C", "TC", "9C", "4H", "4S", "2S"};
        int s = TBJT.score(cards);
        System.out.println(s);
    }

}
