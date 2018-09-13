package server;

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.regex.*;
/*
This is an implementation of the “Small File Sharing Protocol” (SFSP.)
 */

public class SFSP extends Thread {
private Socket socket = null;
    
//constructor
    public SFSP() {
     
    }
  
        //
    public String processInput(String p_IP, String p_theInput) {
     
        if (p_theInput.equalsIgnoreCase("STATUS")) {
            return status();
        }
        if (p_theInput.contains("LIST CREATE")) {
            return listCreate(p_IP, p_theInput);
        }
        if (p_theInput.contains("LIST ADD")) {
            return listCreate(p_IP, p_theInput);
        }

        if (p_theInput.equalsIgnoreCase("LIST CLEAR")) {
            return listClear(p_IP);
        }
       
        if (p_theInput.equalsIgnoreCase("Quit")) {
            return quit();
        }
        if (p_theInput.contains("SEARCH")) {
            return search(p_theInput);
        }
        return "";
    }

     
    String status() {
        return "STATUS:\r\nUSERS: " +
                Helpers.numofUsers() + "\r\nFILES: " +
                Helpers.numofFiles() + "\r\n.";
    }

    String listCreate(String p_IP, String p_command) {
        Client userx = Helpers.GetUserbyIP(p_IP);
        
            String[] filz = (p_command.substring(p_command.indexOf(":") + 1, p_command.length() - 1)).split(",");
       
            userx.Addfiles(filz);
        return "SHARING " + userx.filelist.size() + " FILES.";
    }

    String listClear(String p_IP) {
        Client userx = Helpers.GetUserbyIP(p_IP);
        userx.Clearfiles();
        return "SHARING " + userx.filelist.size() + " FILES.";
    }

    String search( String p_command ) {
        String Return = null;
        String term = p_command.replace("SEARCH", "");
        term=term.replace("'", "");
        term=term.trim();
        
        Object[] resultFiles = Helpers.SearchFiles(term);
        if(resultFiles.length == 0)
        {
      
        String[] terms = term.split(" ");
        for(int i=0;i<terms.length;i++){
         resultFiles= Helpers.SearchFiles(terms[i]);
        
        if(resultFiles.length > 0) 
        {
        Return = "ERROR: excessive input.";
        return Return ;
        }
        }
        
         Return = "FOUND 0 Files.";
        
        }
        else
        {
            Return =   "FOUND "+resultFiles.length+" FILES:";
        for(int i=0;i<resultFiles.length;i++){
            Return += "\r\n" + resultFiles[i].toString();
        }
        Return +="\r\n.";
        }
        return Return ;
    }
    String quit() {
        //KnockKnockServer.remUser( user );
               
        return "Bye.";
    }


}

 