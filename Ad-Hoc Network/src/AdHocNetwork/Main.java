/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package adhocnetwork;

import AdHocNetwork.BroadcastFile;
import AdHocNetwork.ReceiveBroadcastedFile;
import AdHocNetwork.Sender;
import java.io.IOException;
/**
 *
 * @author Muaz
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        Receiver r = new Receiver(); r.start(); 
    }

}
