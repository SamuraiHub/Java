/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package AdHocNetwork;

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Muaz
 */
public class ReceiveBroadcastedFile extends Thread implements Runnable
{
    private MulticastSocket socket;

    private BufferedReader in;

    private InetAddress group;

public ReceiveBroadcastedFile()
{
   super("ReceiveBroadcastedFile");
}

    @Override
public void run()
{
    try
    {
        byte[] buf = new byte[64*1024];

         socket = new MulticastSocket(5544);
         group = InetAddress.getByName("230.0.0.1");
         socket.joinGroup(group);

         // receive request

         DatagramPacket packet = new DatagramPacket(buf, buf.length);
         socket.receive(packet);
         
           byte[] sadr = new byte[2];
                byte[] dadr = new byte[2];
                byte[] sp = new byte[2];
                byte[] dp = new byte[2];
                byte[] data = new byte[(64*1024) - 8];

          System.arraycopy(buf, 0, sadr, 0, 2); // sender address
                System.arraycopy(buf, 2, sp, 0, 2); // sender port
                System.arraycopy(buf, 4, dadr, 0, 2); // receiver address
                System.arraycopy(buf, 6, dp, 0, 2); // receiver port
                System.arraycopy(buf, 8, data, 0, (64*1024) - 8); // recieved data

         String received = new String(data);
         System.out.println("Quote of the Moment: " + received);

         //send aknowledgement

         String s = "file recieved";

         buf = new byte[64*1024];

           System.arraycopy(socket.getLocalAddress().getHostAddress().getBytes(), 0, buf, 0, 2);
            System.arraycopy(String.valueOf(socket.getLocalPort()).getBytes(), 0, buf, 2, 2);
            System.arraycopy(sadr, 0, buf, 4, 2);
            System.arraycopy(sp, 0, buf, 6, 2);
            System.arraycopy(s.getBytes(), 0, buf, 8, s.getBytes().length);

      packet = new DatagramPacket(buf, buf.length, InetAddress.getByAddress(sadr),
                        Integer.parseInt(new String(sp)));
         socket.send(packet);

        //recieve the aknowledgment form sender which indicates that he recieved your aknowledgement

        buf = new byte[64*1024];
                packet = new DatagramPacket(buf, buf.length);
                socket.receive(packet);

                sadr = new byte[2];
                dadr = new byte[2];
                sp = new byte[2];
                dp = new byte[2];
                data = new byte[(64*1024) - 8];

                System.arraycopy(buf, 0, sadr, 0, 2); // sender address
                System.arraycopy(buf, 2, sp, 0, 2); // sender port
                System.arraycopy(buf, 4, dadr, 0, 2); // receiver address
                System.arraycopy(buf, 6, dp, 0, 2); // receiver port
                System.arraycopy(buf, 8, data, 0, (64*1024) - 8); // recieved data

         System.out.println(new String(data));

         socket.leaveGroup(group);
    }
     catch (IOException e) {

    }
          socket.close();
}
}
