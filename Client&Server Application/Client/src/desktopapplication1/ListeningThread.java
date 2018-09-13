/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package desktopapplication1;

import java.io.IOException;
import java.net.*;
import java.io.*;
import sun.nio.ch.DatagramSocketAdaptor;


/**
 *
 * @author Muaz
 */
public class ListeningThread extends Thread {
    
    private File theFile;
    private FileInputStream fileReader;
    private int fileLength, currentPos, bytesRead, toPort;
    private byte[]  msg, buffer;
    private String toHost,initReply;
    private InetAddress toAddress;

    private DatagramSocket thesocket;

    public ListeningThread() throws IOException {
        thesocket = new DatagramSocket(10102);

	msg = new byte[8192];
	buffer = new byte[8192];
	thesocket = new DatagramSocket();
    }

    @Override
    public void run() {
        
        try {
           
            DatagramPacket recieved = new DatagramPacket(new byte[256],256);
            boolean finished = false;
            while(!finished){
            
                thesocket.receive(recieved );
               String tempStr= recieved.toString();
               tempStr = tempStr.substring(10);
               Helpers.OutputText("tempStr");
               String IP = tempStr.substring(0,tempStr.indexOf("File"));
               String fileName = tempStr.substring(tempStr.indexOf("'")+1, tempStr.length()-1);
               sendFile(theFile);
            }
            
        } catch (Exception ex) {
            
        }
    }
    
       

    /**
     * Sends a file to the bound host.
     * Reads the contents of the specified file, and sends it via UDP to the host 
     * and port specified at when the object was created.
     *
     * @param theFile  the file to send
     */
    public void sendFile(File theFile) throws IOException{
	// Init stuff
	fileReader = new FileInputStream(theFile);
	fileLength = fileReader.available();

		
		while (currentPos<fileLength){
		    //System.out.println("Will read at pos: "+currentPos);
		    bytesRead = fileReader.read(msg);
		    send(msg);
		    //System.out.println("Bytes read: "+bytesRead);
		    currentPos = currentPos + bytesRead;
		}
    }
    

    private void send(byte[] message) throws IOException {
	DatagramPacket packet = 
            new DatagramPacket(message, message.length);
	thesocket.send(packet);
    }	
}
