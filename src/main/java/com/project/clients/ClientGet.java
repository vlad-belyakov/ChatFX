package com.project.clients;

import com.project.servers.ServerSend;

import java.io.*;
import java.net.Socket;



public class ClientGet implements Runnable{

    Socket clientSocket;
    BufferedReader in;

    public ClientGet(Socket clientSocket){
        this.clientSocket = clientSocket;
        Thread clientGetThread = new Thread(this,"ClientGetThread");
        clientGetThread.start();
    }

    @Override
    public void run() {
        while (ServerSend.isServerSending){
            try {
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                String message;
                while ((message = in.readLine()) != null) {
                    System.out.println(message);
                }
                ServerSend.isServerSending = false;
            }catch (IOException e) {
                e.printStackTrace();
            }
        }
    }



}
