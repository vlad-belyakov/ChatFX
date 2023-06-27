package com.project.clients;

import java.io.*;
import java.net.*;
//109.252.80.40

public class ClientSend implements Runnable{
    public Socket clientSocket;

    private PrintWriter out;

    private String message;

    private boolean messageReady = false;

    public ClientSend(){
        try {
            clientSocket = new Socket(/*ip*/"localhost", 4001);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Thread clientSendThread = new Thread(this, "ClientSendThread");
        try {
            out = new PrintWriter(clientSocket.getOutputStream(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        clientSendThread.start();
    }

    @Override
    public void run() {
        try {
            while (true) {
                System.out.print("");
                if (messageReady) {
                    out.println(message);
                    System.out.println("отправленное сообщение - " + message);
                    messageReady = false;
                }
            }
        }
        finally {
            System.out.println("Пользователь вышел из приложения");
            out.println("Пользователь вышел из приложения");
            out.close();
            System.out.println("Поток вывода закрыт");
            try {
                clientSocket.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Сокет закрыт");
        }
    }

    public void sendMessage(String msg){
        message = msg;
        messageReady = true;
    }

    public void sendCommand(String command){
        out.println(command);
        System.out.println("CS - " + command);
    }
}

