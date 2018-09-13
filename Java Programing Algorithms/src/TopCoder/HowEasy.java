/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Muaz Al-Jarhi
 */
public class HowEasy {

    /**
     * @param args the command line arguments
     */
private String[] words;
private boolean wrd;
private int k;
private int nc;
private int WordAv;



   public HowEasy(){
    k = 0;
    nc = 0;
    WordAv = 0;
    wrd = false;
   }

public int pointVal(String problemStatement){

 words = problemStatement.split(" ");
 for(int i = 0; i<words.length; i++)
 {
  wrd = true;
  if(words[i].length() != 0)
  k++;

  for(int j = 0; j<words[i].length(); j++)
  {
  if(((Character.isDigit(words[i].charAt(j)))) || (words[i].charAt(j) == '.' && (j<words[i].length()-1 ||
          words[i].length() == 1)))
  {
   wrd = false;
   k--;
   break;
  }
  }
  if(wrd == true)
  {
   nc = nc + words[i].length();
   if(words[i].endsWith("."))
   nc--;
  }
  }
 if(k!=0)
 WordAv = nc/k;
    
  if(WordAv <= 3)
  {
  return 250;
  }
  else
  {

      if(WordAv == 4 || WordAv == 5)
      {
          return 500;
      }
      else
      return 1000;
  }
 }

    public static void main(String[] args) {
        // TODO code application logic here
        HowEasy HowEasy1 = new HowEasy();
       int pv1  = HowEasy1.pointVal("Implement a class H5 which contains some method.");
       System.out.println(pv1);
       System.out.println(HowEasy1.k);
       System.out.println(HowEasy1.nc);
       System.out.println(HowEasy1.WordAv);
    }

}
