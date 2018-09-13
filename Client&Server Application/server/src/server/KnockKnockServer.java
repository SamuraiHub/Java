package server;

import java.io.*;
import java.net.*;
import java.util.*;
import java.text.*;

public class KnockKnockServer {

    public static void StartServer() throws IOException {
//create server socket
        ServerSocket serverSocket = null;

        try {
            serverSocket = new ServerSocket(10101);
        } catch (IOException e) {
            Helpers.OutputText("Could not listen on port: 10101.");
            System.exit(-1);
        }
        Helpers.OutputText("Server started");
        //start listening on a new thread         
        new ListeningThread(serverSocket).start();
    }

}
