
import java.io.*;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Administrator
 */
public class Data_Partition {

    /**
     * @param args the command line arguments
     */


    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        
        BufferedReader r  = new BufferedReader(new FileReader("F:/Stuff/Downloads/GRE/Word Lists/Splitted Lists/List 1.csv"));
        BufferedReader r1 = new BufferedReader(new FileReader("F:/Stuff/Downloads/GRE/WDL.txt"));
        BufferedWriter w  = new BufferedWriter(new FileWriter("F:/Stuff/Downloads/GRE/Word Lists/Splitted Lists/List 1.txt"));
         String s; int k = 0;
          
          for(int i = 0; i < 0 * 110; i++)
          r1.readLine();
              
          while((s = r.readLine()) != null)
          {
              String  w1 = r1.readLine(); int i = w1.indexOf(' '); //"Aback - adv.";
              if(s.equals(w1.substring(0, i)))
              {
                  i+=3; //String w3 = r.readLine();
                w.write(s+"\t"+w1.substring(i, w1.indexOf(' ', i)+1)+ r.readLine() + " ");
                String w2 = r.readLine();
                w.write(w2.substring(0, w2.length()-2)+"\r\n");
                r.readLine();
              }
              else
              {
                  System.out.println("Not equal: " + s + " , " + w1.substring(0, i));
                  w.write(s+"\t"+r.readLine()+" ");
                String w2 = r.readLine();
                w.write(w2.substring(0, w2.length()-2)+"\r\n");
                r.readLine(); k++;
              }
          }
          System.out.println(k); 
                  
          r.close();
          w.close();
    }

}
