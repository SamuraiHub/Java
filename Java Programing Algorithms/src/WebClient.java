/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Aljarhi
 */

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

public class WebClient extends Thread implements Runnable
{
    private static Socket sock; private static boolean ok;

    final static int BUF_SIZE = 2048;

    @Override
    public void run() {
        
        try {
              InputStream is = new BufferedInputStream(sock.getInputStream());
        PrintStream ps = new PrintStream(sock.getOutputStream()); byte[] buf = new byte[BUF_SIZE];
         for (int i = 0; i < BUF_SIZE; i++) {
            buf[i] = 0;
        }

           ps.print("GET / HTTP/1.1\r\n");

                     int nread = 0, r = 0;

            while (nread < BUF_SIZE) {
                r = is.read(buf, nread, BUF_SIZE - nread);
                if (r == -1) {
                    /* EOF */
                    break;
                }
                nread += r;
            }

           System.out.println(new String(Arrays.copyOfRange(buf, 0, nread)));

        } catch (IOException ex) {
            if(ex.getMessage().equals("Connection reset"))
           System.out.println("neeb");
        }
        finally {
            try {
                sock.close();
            } catch (IOException ex) {
                Logger.getLogger(WebClient.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

  public static void main(String[] args) {
    try {
      InetAddress addr;
                          
      sock = new Socket("192.168.1.3", 80);
      addr = sock.getInetAddress();
      System.out.println("Connected to " + addr);

      System.out.println(addr.getHostName());
      
      WebClient client = new WebClient(); client.start();
      
    } catch (IOException e) {
      System.out.println("Can't connect to " + args[0]);
      System.out.println(e);
    }
  }

}
