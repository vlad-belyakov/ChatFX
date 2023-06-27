package com.project.servers;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

@SuppressWarnings("ALL")
public class ServerLauncher {

    public static void main(String[] args) {
        ServerSocket serverSocket;
        Socket socket;
        try {
            serverSocket = new ServerSocket(4001);
            int i = 1;
            while (true) {
                socket = serverSocket.accept();
                ServerGet serverGet = new ServerGet(socket);
                Thread serverGetThread = new Thread(serverGet,"ServerGetThread" + i);
                i++;
                serverGetThread.start();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
