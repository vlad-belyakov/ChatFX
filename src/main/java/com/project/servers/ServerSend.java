package com.project.servers;


import java.io.*;
import java.net.Socket;

public class ServerSend implements Runnable{
    public static boolean isServerSending;
    private final Socket clientSocket;

    public ServerSend(Socket clientSocket){
        this.clientSocket = clientSocket;
        Thread serverSendThread = new Thread(this, "ServerSendThread");
        serverSendThread.start();
    }

    @Override
    public void run() {
        /*if (ClientSend.isClientSending) {
            try {
                BufferedReader getS = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                String request = getS.readLine();
                for (String s : ServerGet.users.keySet()) {
                    PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(ServerGet.users.get(s).getOutputStream())), true);
                    out.println(request);
                    System.out.println(request);
                    serSending = true;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }*/
    }
}

