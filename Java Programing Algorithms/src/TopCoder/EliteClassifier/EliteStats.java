/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package TopCoder.EliteClassifier;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Aljarhi
 */
public class EliteStats {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
        // TODO code application logic here
        
        BufferedReader r = new BufferedReader(new FileReader("F:/Stuff/Muaz/Programming/Java/Java Programing Algorithms/src/"
                + "TopCoder/EliteClassifier/DataTraining.csv"));
        
        BufferedWriter w = new BufferedWriter(new FileWriter("F:/Stuff/Muaz/Programming/Java/Java Programing Algorithms/src/"
                + "TopCoder/EliteClassifier/EliteData.csv"));
        
        String l; int i = 0; int[] t = new int[5487];
        
        while((l = r.readLine()) != null)
        {
           String[] field = l.split(",");
           
            if(field[10].equals("1"))
           {
               w.write(l); w.newLine();
              /* t[i] = 0;
               //System.out.println(l);
               BufferedReader r1 = new BufferedReader(new FileReader("F:/Stuff/Muaz/Programming/Java/Java Programing Algorithms/src/"
                + "TopCoder/EliteClassifier/DataTraining.csv"));
               while((l = r1.readLine()) != null)
               {
                   String[] field1 = l.split(",");
                  if(field[1].equals(field1[1]) && field[2].equals(field1[2]) && field[3].equals(field1[3]) &&
                          field[4].equals(field1[4]) && Math.abs(Double.valueOf(field[6]) - Double.valueOf(field1[6])) < 2
                          && !field[7].equals("NULL") && !field1[7].equals("NULL") && 
                          Math.abs(Double.valueOf(field[7]) - Double.valueOf(field1[7])) < 3)
                  {
                    t[i]++;  
                  }
               }
               System.out.println(t[i]); 
               i++; */
           } 
        }
       w.close();
       // System.out.println(i);

    }
}
