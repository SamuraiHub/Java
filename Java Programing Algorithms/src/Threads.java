
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Calendar;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Al-Jarhi
 */
public  class Threads extends Thread implements Runnable {

    private static Socket socket = null;

    private static Calendar C1;

   
    @Override
    public void run() {
        PrintWriter out = null;
        Calendar C = null;
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
            C = Calendar.getInstance();
            String inputline;

           while(true)
           {
               
           }

        } catch (IOException ex) {
            if(ex.getMessage().equals("Connection reset"))
           System.out.println("neeb");
        } 
    }

    public static void main(String[] args) throws UnknownHostException, IOException
    {
     C1 = Calendar.getInstance();
     C1.add(Calendar.MINUTE, 1);
     System.out.println(C1.getTime().toString());
     socket = new Socket("Core", 10101);
     
     Thread t = new Threads();
     t.start();
     
    }
}
