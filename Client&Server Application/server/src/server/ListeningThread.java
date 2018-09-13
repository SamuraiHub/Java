/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.IOException;
import java.net.*;
import sun.security.krb5.internal.UDPClient;

/**
 *
 * @author Muaz
 */
public class ListeningThread extends Thread {

    private ServerSocket thesocket;

    public ListeningThread(ServerSocket p_socket) throws IOException {
        thesocket = p_socket;
    }

    @Override
    public void run() {
        try {
            boolean listening = true;
            while (listening) {
                try {
                    new MultiServerThread(thesocket.accept()).start();
                } catch (IOException ex) {
                   
                }
            }
           // thesocket.close();
        } catch (Exception ex) {
            
        }
    }
}
