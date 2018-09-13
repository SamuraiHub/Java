
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;
import java.nio.charset.Charset;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Administrator
 */
public class NeuralNetwork {

    /**
     * @param args the command line arguments
     */


    public static void main(String[] args) throws IOException {
        // TODO code application logic here

        BufferedReader r = new BufferedReader(new InputStreamReader(
                new FileInputStream("F:\\Stuff\\Muaz\\Programming\\Android\\T&C List Manager\\src\\"
                + "All Natural Foods.txt"), Charset.forName("UTF-8")));
        
        String s;
        
        while((s = r.readLine()) != null)
        {
            if(s.startsWith("﻿FRUITS"))
            {
                System.out.println("Fruits");
            }
            
            else if(s.startsWith("VEGETABLES"))
            {
              System.out.println("Vegetables");          
            }
            
            else if(s.startsWith("HERBS"))
            {
              System.out.println("Herbs");          
            }
            
            else if(s.startsWith("NUTS"))
            {
              System.out.println("nuts");          
            }
            
            else if(s.startsWith("CEREALS"))
            {
              System.out.println("Cereals");          
            }
            
            else if(s.startsWith("MEAT"))
            {
              System.out.println("meat");          
            }
            
            else
            {
              System.out.println(s);
            }
        }
    }

}
