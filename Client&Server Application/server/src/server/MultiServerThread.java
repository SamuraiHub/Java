/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

/**
 *
 * @author Muaz
 */
import java.net.*;
import java.io.*;

public class MultiServerThread extends Thread {

    private Socket socket = null;
   
    
    
    public MultiServerThread(Socket socket) {
        super("MultiServerThread");
        this.socket = socket;
    }
       
    @Override
    public void run() {
       

        try {
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                    socket.getInputStream()));

               //out.println("NEEb");
            
            String inputLine, sourceAddr,  outputLine; 
            sourceAddr = socket.getInetAddress().getHostAddress();
                 
            //initiate SFSP Protocol object
            SFSP sfsProtocol = new SFSP();

            int i = 0;


            while(i < 5)
            {
                out.println("FSS KBEAR!");
            }

           
            while ((inputLine = in.readLine()) != null) {

                try {

            


                Helpers.OutputText("[" + sourceAddr + "]Recieved: " + inputLine);
              
                if (inputLine.equals("SFSP V1.0")) {
                     
                     Helpers.OutputText("[" + sourceAddr + "]Recieved: " + inputLine);
                    
                     inputLine =in.readLine().replace("User " , "");
                    
                    // check if user already exists
                      if (Helpers.DoesUsernameExist(sourceAddr, inputLine)) {
                        out.println("ERROR: User is already logged in.");
                        
                        continue;
                    }
                        if (Helpers.DoesIPExist(sourceAddr)) {
                        out.println("ERROR: another user is logged in from the same ip.");
                        
                        continue;
                    } 
                     if (Helpers.IsUsernameValid(inputLine)) {
                        //validates username
                      
                        Helpers.CreateUser(sourceAddr, inputLine);
                        
   
                        out.println("OK");
                        Helpers.OutputText("[" + sourceAddr + "]Sent: OK");
                        out.println("Welcome " + inputLine);
                        Helpers.OutputText("[" + sourceAddr + "]Sent: Welcome," + inputLine);
                    } else {
                        out.println("Error: User Invalid.");
                        Helpers.OutputText("[" + sourceAddr + "]Sent: Error: User Invalid.");
                    }
                }
                   
                else {

                    //get multiline comments
                    if (inputLine.endsWith(":")) {
                        inputLine = inputLine + in.readLine();
                        
                            while (!((inputLine).endsWith("."))) {
                     
                         inputLine = inputLine + in.readLine();
                        }
                     }
                
                       outputLine = sfsProtocol.processInput(sourceAddr, inputLine);
                    out.println(outputLine);
                    Helpers.OutputText("[" + sourceAddr + "]Sent: " + outputLine);
                  if(outputLine.equals("Bye."))
                  {
                Client userx =  Helpers.GetUserbyIP(socket.getInetAddress().getHostAddress());             
                Helpers.userlist.remove(userx);  
                  continue;
                  }         
        }

        }
                catch (IOException e) {
            System.out.println(e.getMessage() + "NOOB");
            
        }
            }
            
            System.out.println("NOOb");

        } catch (IOException e) {
            System.out.println(e.getMessage());

            if(socket.isConnected())
            System.out.println("CLOSED NOOB");
        }
}
}

