/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package adhocnetwork;

/**
 *
 * @author Muaz
 */
import java.io.*;

import java.net.*;


public class Receiver extends Thread implements Runnable
{
    private DatagramSocket socket;

    public Receiver() throws IOException {

        super("Reciever");
    }


    @Override
    public void run() {

            try {

                socket = new DatagramSocket(4446);

                byte[] buf = new byte[64*1024];

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

                String s = new String(data);

                System.out.println(s);

                //send aknowledgement

                s = "Recieved: " + packet.getData();

                buf = new byte[64*1024];
                String Sadr = "192.168.1.25";

            System.arraycopy(Sadr.getBytes(), 0, buf, 0, 2);
            System.arraycopy("4446".getBytes(), 0, buf, 2, 2);
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
                
            } catch (IOException e) {

            }

        socket.close();

    }

}
