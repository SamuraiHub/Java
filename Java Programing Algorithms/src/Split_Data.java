
import java.io.*;
;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Muaz Aljarhi
 */
public class Split_Data {
    
    
    public static void main(String[] args) throws IOException
    {
        BufferedReader R = new BufferedReader(new FileReader("C:/Users/Muaz Aljarhi/Downloads/example_data.csv"));
        BufferedWriter W1 = new BufferedWriter(new FileWriter("C:/Users/Muaz Aljarhi/Downloads/train_data.csv"));
        BufferedWriter W2 = new BufferedWriter(new FileWriter("C:/Users/Muaz Aljarhi/Downloads/test_data.csv"));
        
        String s; int i = 1; 
        
        while((s = R.readLine()) != null)
        {
            if(i%3 == 0) { W2.write(s); W2.newLine();}
            else{ W1.write(s); W1.newLine();}
            i++;
        }
        W1.close();
        W2.close();
    }
    
}
