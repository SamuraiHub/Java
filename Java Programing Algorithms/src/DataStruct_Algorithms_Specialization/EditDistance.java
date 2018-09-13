import java.util.*;

class EditDistance {
  public static int EditDistance(String s, String t) {
    //write your code here
    int[] ed = new int[s.length()*t.length()];
    
    ed[0] = t.charAt(0) == s.charAt(0) ? 0: 1;
    //System.out.print(ed[0] + ", ");
    
    for(int i = 1; i < s.length(); i++)
    {
        ed[i*t.length()] = t.charAt(0) == s.charAt(i) && ed[(i-1)*t.length()] == i ? ed[(i-1)*t.length()]: ed[(i-1)*t.length()] + 1;
    }
    for(int j = 1; j < t.length(); j++)
    {
        ed[j] = t.charAt(j) == s.charAt(0) && ed[j-1] == j ? ed[j-1]: ed[j-1] + 1;
        //System.out.print(ed[j] + ", ");
    }
    //System.out.println();
    for(int i = 1; i < s.length(); i++)
    {
        //System.out.print(ed[i*t.length()] + ", ");
        for(int j = 1; j < t.length(); j++)
        {
            int m = Math.min(ed[(i-1)*t.length()+j], ed[i*t.length()+j-1]);
            
            if(ed[(i-1)*t.length()+j-1] <= m)
            {
                if(s.charAt(i) == t.charAt(j))
                ed[i*t.length()+j] = ed[(i-1)*t.length()+j-1];
                else
                ed[i*t.length()+j] = ed[(i-1)*t.length()+j-1] + 1;    
            }
            else
                ed[i*t.length()+j] = m+1;
            
            //System.out.print(ed[i*t.length()+j] + ", ");
        }
        //System.out.println();
    }
    
    return ed[s.length()*t.length()-1];
  }
  public static void main(String args[]) {
    Scanner scan = new Scanner(System.in);

    String s = scan.next();
    String t = scan.next();

    System.out.println(EditDistance(s, t));
  }

}
