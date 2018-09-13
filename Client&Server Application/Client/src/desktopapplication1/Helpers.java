/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package desktopapplication1;

import java.io.*;
import java.net.*;
import java.util.*;
import java.net.InetAddress;

/**
 *
 * @author Muaz
 */
public class Helpers {

    private static java.awt.TextArea lblOut;
    private static String strUsername;
    private static boolean cl;
    private static String tosend;
    // initialize the class with the output form control
    public static void init(java.awt.TextArea p_label) {
        lblOut = p_label;
    }

    //writes message to form
    public static void OutputText(String txt) {
        lblOut.append("\r\n" + txt);
    }

//get ip address of local machine
    public static String MyIP() {
        String strIP = "";
        try {
            InetAddress addr = InetAddress.getLocalHost();
            strIP = addr.getHostAddress();
        } catch (UnknownHostException e) {
        }
        return strIP;
    }

//initiate handshake
    public static boolean InitHandshake(String p_username) {
    
        cl = false;
        strUsername = p_username;
        Client.Send("SFSP V1.0\r\nUser " + p_username);
        return true;
    }
    
    public static boolean GetStatus() {
          
        tosend= "STATUS";
        Client.Send(tosend);
       return true; 
    }
    
    public static boolean AddFiles(String p_filelist ) {
       
        if(p_filelist.trim().length() == 0)
        {

        return true;
        }
        if (!cl)
        {
        
        tosend= "LIST CREATE:\r\n";
        tosend += p_filelist +".";
        Client.Send(tosend);
        cl = true;
        }
        else { 
        tosend= "LIST ADD:\r\n";
        tosend += p_filelist +".";
        Client.Send(tosend);
        }
        return true;
    } 
    
        public static boolean Search(String p_term ) {
        if(p_term.trim().length() == 0)
        {

        return true;
        }
         tosend= "SEARCH '"+p_term+"'";
        Client.Send(tosend);
        return true;
    } 
     
      public static boolean ClearFiles( ) {
         
          tosend= "LIST CLEAR";
        Client.Send(tosend);
        return true;
    } 
      
      public static boolean Quit( ) {
    
          tosend= "Quit";
        Client.Send(tosend);
         
        return true;
    } 
}
