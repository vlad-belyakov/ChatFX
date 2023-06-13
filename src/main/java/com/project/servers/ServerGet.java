package com.project.servers;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

import com.project.clients.Client;

public class ServerGet implements Runnable{

    protected ServerSocket serverSocket;
    private boolean start = true;
    protected static HashMap<Socket, String> users;
    protected String msg;
    protected static Socket gettedClientSocket;

    public ServerGet(){
        Thread serverGetThread = new Thread(this,"ServerGetThread");
        try {
            serverSocket = new ServerSocket(4001);
        } catch (IOException e) {
            e.printStackTrace();
        }
        serverGetThread.start();
    }


    @Override
    public void run(){
        try {
            do{
                gettedClientSocket = serverSocket.accept();
            }while(!gettedClientSocket.isConnected());
            System.out.println("подключился " + gettedClientSocket.getInetAddress() + ":" + gettedClientSocket.getPort());
            BufferedReader in = new BufferedReader(new InputStreamReader(gettedClientSocket.getInputStream()));
            while (start) {
                msg = in.readLine();
                if (msg != null)
                    System.out.println("полученное сообщение: " + msg);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
