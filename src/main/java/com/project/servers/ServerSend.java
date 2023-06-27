package com.project.servers;

import com.project.services.ClientServiceImpl;

import java.io.*;
import java.net.Socket;

public class ServerSend implements Runnable {
    private final Socket clientSocket;
    private String msg;
    private ServerClient serverClient;
    private boolean isMsgGot = false;
    private boolean isCmdGot = false;
    private String cmd;

    public ServerSend(Socket clientSocket) {
        this.clientSocket = clientSocket;
        Thread serverSendThread = new Thread(this, "ServerSendThread");
        serverSendThread.start();
    }

    //отправка сервером данных всем пользователям и обработка команд
    @Override
    public void run() {
        while (true) {
            System.out.print("");
            if (isMsgGot) {
                try {
                    for (ServerClient client : ClientServiceImpl.getInstance().readAll()) {
                        if (!client.getName().equals(serverClient.getName())) {
                            Socket s = client.getSocket();
                            PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(s.getOutputStream())), true);
                            out.println(serverClient.getName() + "/" + msg);
                            isMsgGot = false;
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (isCmdGot) {
                PrintWriter out = null;
                try {
                    out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream())), true);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                out.println(cmd);
                isCmdGot = false;
            }
        }
    }

    public void msgSend(String msg, String name) {
        serverClient = ClientServiceImpl.getInstance().read(name);
        isMsgGot = true;
        this.msg = msg;
    }

    public void cmdSend(String cmd) {
        isCmdGot = true;
        this.cmd = cmd;
    }
}

