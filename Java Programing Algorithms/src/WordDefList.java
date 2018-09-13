
import java.io.*;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author user
 */
public class WordDefList {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        BufferedReader r = new BufferedReader(new FileReader("C:\\Users\\user\\Desktop\\1700 TOEFL Words.txt"));
        BufferedWriter w = new BufferedWriter(new FileWriter("C:\\Users\\user\\Desktop\\TOEFL Words.txt"));
        
        String s = r.readLine(); int i = 0, j = 0;
        
        while((s = r.readLine()) != null)
        {  
            if(s.contains(", noun") || s.contains(", verb") || s.contains(", adj") || s.contains(", preposition") || s.contains(", adv"))
            {
                if(i != 0) { w.newLine(); }
                w.write(s.substring(0, s.indexOf(',')) + '\t' + s.substring(s.indexOf(',') + 1));
                i++;
            }
            
            else if(s.contains(" (noun") || s.contains(" (verb") || s.contains(" (adjective") || s.contains(" (preposition") || s.contains(" (adverb"))
            {    
                if(i != 0) { w.newLine(); }
                w.write(s.substring(0, s.indexOf(' ')) + '\t'+ s.substring(s.indexOf(' ') + 1));
                j++;
                System.out.println(s);
            }
            
            else if(!s.startsWith("200") && !s.startsWith("1500"))
            {
                //System.out.println(s);
                w.write(s);
            }
        }
        System.out.println(i);
        System.out.println(j);
        
        r.close(); w.close();
    }
}
