/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package desktopapplication1;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.regex.*;
import javax.swing.JFrame;
/**
 *
 * @author Muaz
 */
public class Recieve extends Thread {

   private static BufferedReader In = null;
    protected static byte[] byteArray; 
 protected static BufferedInputStream bis; 
 protected static BufferedOutputStream bos; 
 protected static int in; 
 protected static BufferedReader inm = null;
 protected static PrintWriter outm = null;
 private JFrame download;   
 Recieve(BufferedReader p_In) {
           In = p_In;
    }

    @Override
    public void run() {
        try {
            
            String fromServer;
            while ((fromServer = In.readLine()) != null) {
                     
                Helpers.OutputText("Server: " + fromServer);
                if (fromServer.equals("OK")) {
                    fromServer = In.readLine();
                Helpers.OutputText("Server: " + fromServer);
                }
                if (fromServer.equals("Bye.")) {
                   Client.Finalize();
                    break;
                }
                
                //search results
                if (fromServer.startsWith("FOUND"))
                {
                    
                    //get sraech results
                 List searchResults = Collections.synchronizedList(new ArrayList( ));

                String tempStr;
                 while (!((tempStr=In.readLine()).endsWith("."))) {
                     
                       searchResults.add(tempStr)  ;
                       Helpers.OutputText("Server: " + tempStr);
                        }
                
               
                //open download window
                if (download == null  ) {
            JFrame mainFrame = DesktopApplication1.getApplication().getMainFrame();
            download = new Download(searchResults); 
            download.setLocationRelativeTo(mainFrame);
        }
        DesktopApplication1.getApplication().show(download);
                }
                
                
                
            }
        } catch (IOException ex) {
           Helpers.OutputText( "Error: " + ex.getMessage() + "\r\n" + ex.getStackTrace());
           if(ex.getMessage().equals("Connection reset"))       
           { 
           Client c = new Client();
            c.Out = null; 
           c.In = null;
           c.client = null;
           }        
           }
    }
 
   
}
