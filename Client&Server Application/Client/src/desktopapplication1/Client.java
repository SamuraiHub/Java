package desktopapplication1;

import java.io.*;
import java.net.*;
import java.util.*;
import java.awt.event.*;
import javax.sound.midi.Receiver;
import java.io.File;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class Client {

    //member vars
    protected static PrintWriter Out;
     protected static FileWriter FileOut;
    protected static BufferedReader In;
     protected static Socket client;
      protected static BufferedReader InFile;
     protected static File file;
     static Socket clientSocket; 
 protected static byte[] byteArray; 
 protected static BufferedInputStream bis; 
 protected static BufferedOutputStream bos; 
 protected static int in; 
 protected static BufferedReader inm = null;
 protected static PrintWriter outm = null;

      //function to connect to server           
        
    public static void Connect(String p_host, String p_port) throws IOException {
   //init vars
   // user inputs port as a string, must convert to integer and validate     
     Out = null;
     In = null;
     client = null;      
      InFile = null;
      FileOut = null; 
       
      try {
            
            int i = Integer.parseInt(p_port.trim());
            if(!(i == 10101))
            {
           Helpers.OutputText("Error: Incorrect Port Number.");
          Out.println("Error: Incorrect Port Number.");   
         System.exit(1);
            }    
             client = new Socket(p_host, i); 
           Out = new PrintWriter(client.getOutputStream(), true);
           In = new BufferedReader(new InputStreamReader(client.getInputStream()));
          
             }  catch (NumberFormatException e) {
         Helpers.OutputText("Error: Invalid Port Number.");
          Out.println("Error: Invalid Port Number.");   
         System.exit(1);
      }
        catch (UnknownHostException e) {
            Helpers.OutputText("Error: host does not exist.");
            Out.println("Error: host does not exist.");
            System.exit(1);
        } catch (IOException e) {
            Helpers.OutputText("Error: Couldn't get I/O for the connection.");
           Out.println("Error: Couldn't get I/O for the connection.");
            System.exit(1);
        }
        Helpers.OutputText("connection with server established!");
        DesktopApplication1View.q = true; 

    }//end connect
    public static void Send(String p_messasge) {
    
        try {
              
            Out.println(p_messasge);
            Helpers.OutputText("Sent: " + p_messasge);
        } catch (Exception e) {
            Helpers.OutputText("Error(while sending): " + e.getMessage());
        } 
        
        
    }
   

    //end send
    //to recieve any messages from the server
    public static void Receive() throws IOException {
        Thread thread = new Recieve(In);
        thread.start();

    }

    public static void Finalize() throws IOException {
     Out.close();
    In.close();
    client.close();
     
    }//end finalize
}//end class

