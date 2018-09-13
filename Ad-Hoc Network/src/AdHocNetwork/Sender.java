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

public class Sender extends Thread implements Runnable
{
    private DatagramSocket socket;

    public Sender() throws IOException {

        super("Sender");           
    }


    @Override
    public void run() {

            try {
                socket = new DatagramSocket(3734);

                byte[] buf = new byte[64*1024];

                String s  = "Hello World";
                
                String sadr = "169.254.40.16";
                String dadr = "169.254.32.161";
    
            InetAddress adr = InetAddress.getByName(dadr);

            System.arraycopy(sadr.getBytes(), 0, buf, 0, 2); // sender address
            System.arraycopy("3734".getBytes(), 0, buf, 2, 2); // sender port
            System.arraycopy(dadr.getBytes(), 0, buf, 4, 2); // receiver address
            System.arraycopy("4446".getBytes(), 0, buf, 6, 2); // reciever port
            System.arraycopy(s.getBytes(), 0, buf, 8, s.getBytes().length); // data to be sent

                // send

                DatagramPacket packet = new DatagramPacket(buf, buf.length, adr, 4446);

                socket.send(packet);
                
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

                    s = "Ok.";
                    
                    //Send to back to the reciever tht you recieved his  acknowledgement

                    buf = new byte[64*1024];

            System.arraycopy(sadr.getBytes(), 0, buf, 0, 2); // sender address
            System.arraycopy("3734".getBytes(), 0, buf, 2, 2); // sender port
            System.arraycopy(Sadr, 0, buf, 4, 2); // receiver address
            System.arraycopy(sp, 0, buf, 6, 2); // reciever port
            System.arraycopy(s.getBytes(), 0, buf, 8, s.getBytes().length); // data to be sent

                     packet = new DatagramPacket(buf, buf.length, adr, 4446);
                    socket.send(packet);

            } catch (IOException e) {

            }

        socket.close();

    }




}
