package com.project.clients;

import java.io.*;
import java.net.*;
import java.util.HashMap;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
//109.252.80.40

public class ClientSend implements Runnable{
    public static Socket clientSocket;
    public static HashMap<String, Socket> data;

    public ClientSend(){
        try {
            clientSocket = new Socket("127.0.0.1", 4001);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Thread clientSendThread = new Thread(this, "ClientSendThread");
        clientSendThread.start();
    }

    @Override
    public void run() {
        try {
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            out.println("связь налажена");
            while (true) {
                if(true){
                } else {
                    System.out.println("Пользователь вышел из приложения");
                    break;
                }
            }
            out.close();
            System.out.println("Поток вывода закрыт");
            clientSocket.close();
            System.out.println("Сокет закрыт");
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}

