import java.io.*;
import java.util.*;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Muaz Aljarhi
 */
public class TransSCRefs {
    
    public static void main(String[] args) throws IOException
    {
      BufferedReader br = new BufferedReader(new InputStreamReader(new BufferedInputStream(new FileInputStream("F:\\Muaz\\NBHM\\SC\\SCT1.txt"))));
        List<String> lines = new ArrayList(100);
        String s = null;
        while((s = br.readLine()) != null)
        {
           String[] ST = s.split("[ ]");
           lines.add(ST[0] + "," +
           ST[1].substring(7, 11)+ST[1].substring(4, 6)+ST[1].substring(1, 3) + "," +
           ST[4].substring(7, 11)+ST[4].substring(4, 6)+ST[4].substring(1, 3) + "," +
           ST[5].substring(1)
           );
        }
        br.close();
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new BufferedOutputStream(new FileOutputStream("F:\\Muaz\\NBHM\\SC\\SC2.txt"))));
        for(int i = 0; i < lines.size(); i++)
        {
            bw.write(lines.get(i));
            bw.newLine();
        }
        bw.close();
    }
}
