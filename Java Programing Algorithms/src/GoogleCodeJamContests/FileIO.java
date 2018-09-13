
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package GoogleCodeJamContest;

import java.io.*;


/**
 *
 * @author Administrator
 */
public class FileIO{

private File outFile;
private File inFile;
private BufferedReader br;
private BufferedWriter bw;

    /**
     * @param args the command line arguments
     */
public FileIO(String inFileName, String outFileName){
        try {
            inFile = new File(inFileName);
   BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(inFile)));
            outFile = new File(outFileName);
    BufferedWriter bw = new BufferedWriter(new FileWriter(outFile));
        } catch (Exception e) {

        }
}



}
