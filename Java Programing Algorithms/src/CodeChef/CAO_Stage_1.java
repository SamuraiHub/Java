/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CodeChef;

/**
 *
 * @author Aljarhi
 */
public class CAO_Stage_1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws java.lang.Exception{
        // TODO code application logic here
        java.io.BufferedReader br = new java.io.BufferedReader
                (new java.io.InputStreamReader (System.in));
        int a=Integer.parseInt(br.readLine());
        
        for(int i = 0; i < a; i++)
        {
            String T = br.readLine();
            int S = T.indexOf(' ');
            
            
            int R = Integer.parseInt(T.substring(0, S));
            int C = Integer.parseInt(T.substring(S+1));
            String[] rows = new String[C]; 

           for(int r = 0; r < R; r++)
           {
               rows[i] = br.readLine(); 
           }
           
           for(int r = 1; r < R-1; r++)
           {
               for(int c = 1; c < C-1; c++)
               {
                   if(rows[r].charAt(c) != '#')
                   {
                      
                   }
               }
           }
        }       
    }
}
