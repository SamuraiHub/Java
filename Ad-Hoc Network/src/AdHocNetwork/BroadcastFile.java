/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package AdHocNetwork;

/**
 *
 * @author Muaz
 */
import java.io.*;

import java.net.*;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BroadcastFile extends Thread implements Runnable
{
    private MulticastSocket socket;

    private BufferedReader in;

public BroadcastFile()
{
    super("BroadcastFile");
 
}

@Override
public void run()
{
   try {

    socket = new MulticastSocket();

    byte[] buf = new byte[64*1024];

    in = new BufferedReader(new FileReader("C:/Users/extend/Documents/one-liners.bc"));

    String s, f = "";

    while((s = in.readLine()) != null)
    {
      f = f+s+"\r\n";
    }
    String sadr = "169.254.40.16";

    //broadcast the file

    System.arraycopy(sadr.getBytes(), 0, buf, 0, 2); // sender address
            System.arraycopy("3734".getBytes(), 0, buf, 2, 2); // sender port
            System.arraycopy("0".getBytes(), 0, buf, 4, 4); // receiver id =  0
            System.arraycopy(s.getBytes(), 0, buf, 8, f.getBytes().length); // data to be sent

    InetAddress group = InetAddress.getByName("230.0.0.1");

                DatagramPacket packet = new DatagramPacket(buf, buf.length,

                    group, 5544);

                socket.send(packet);

                while(true)
                {
                    //recieve the acknowledgement form the reciever

                    buf = new byte[64*1024];
                    packet = new DatagramPacket(buf, buf.length);
                    socket.receive(packet);

                byte[] Sadr = new byte[2];
                byte[] Dadr = new byte[2];
                byte[] sp = new byte[2];
                byte[] dp = new byte[2];
                byte[] data = new byte[(64*1024) - 8];

                System.arraycopy(buf, 0, Sadr, 0, 2); // sender address
                System.arraycopy(buf, 2, sp, 0, 2); // sender port
                System.arraycopy(buf, 4, Dadr, 0, 2); // receiver address
                System.arraycopy(buf, 6, dp, 0, 2); // receiver port
                System.arraycopy(buf, 8, data, 0, (64*1024) - 8); // recieved data

                    System.out.println("Recived: "+ new String(data));

                    //Send to back to the reciever tht you recieved his  acknowledgement

                    s = "Ok.";
            System.arraycopy(sadr.getBytes(), 0, buf, 0, 2); // sender address
            System.arraycopy("3734".getBytes(), 0, buf, 2, 2); // sender port
            System.arraycopy(Sadr, 0, buf, 4, 2); // receiver address
            System.arraycopy(sp, 0, buf, 6, 2); // reciever port
            System.arraycopy(s.getBytes(), 0, buf, 8, s.getBytes().length); // data to be sent

                    InetAddress adr = packet.getAddress();
                    int port = packet.getPort();

                    packet = new DatagramPacket(buf, buf.length, adr, port);
                    socket.send(packet);

                }

    }
    catch (IOException e) {

    }
        socket.close();
}

}
