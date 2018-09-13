
import java.io.*;
import java.math.*;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Administrator
 */
public class AlienNumbers {

 private BufferedReader br;
private BufferedWriter bw;


public  AlienNumbers(String inFileName, String outFileName){
        try {
          File    inFile = new File(inFileName);
    br = new BufferedReader(new FileReader(inFile));
        File    outFile = new File(outFileName);
     bw = new BufferedWriter(new FileWriter(outFile));

             }catch (Exception e) {

        }
}
public int ANToInt(String AlN, String SL)
{
 int k = 0;
for(int i = 0; i<AlN.length(); i++)
{
 k = k +((int)Math.pow(SL.length(),(AlN.length()-i-1))*SL.indexOf(AlN.charAt(i)));
}
return k;
}

public String IntToAN(int n, String TL)
{
 String AN = "";

 while(n != 0)
 {
  int r = n%TL.length();
  AN = (String.valueOf(TL.charAt(r))).concat(AN);
  n = n/TL.length();
 }
 return AN;
}
 public void AlNConverter(){
 try{
     
    
     String l1 = br.readLine();

int f = Integer.parseInt(l1);
for(int i = 0; i<f; i++)
{
l1 = br.readLine();
String[] NOI = l1.split(" ");
int n = ANToInt(NOI[0], NOI[1]);

String  an1 = IntToAN(n, NOI[2]);


bw.write("Case #"+(i+1)+": "+an1);
bw.newLine();
}
 br.close();
 bw.close();
 }
catch(Exception e){
  e.printStackTrace();
 }
 }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
     AlienNumbers  AlN1 = new AlienNumbers("H:/Muaz/Java Programing Algorithms/test/GoogleCodeJamContests/AlienNumbers/A-large-practice.in",
        "H:/Muaz/Java Programing Algorithms/test/GoogleCodeJamContests/AlienNumbers/A-large-practice.out");
     AlN1.AlNConverter();

    }

}
