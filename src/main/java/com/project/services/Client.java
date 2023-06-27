package com.project.services;

import java.net.Socket;

public abstract class Client {

    private String name;
    private String password;
    private Socket socket;

    public Client(String name, String password, Socket socket){
        this.name = name;
        this.password = password;
        this.socket = socket;
    }

    public Client(String name, String password){
        this.name = name;
        this.password = password;
    }


    public String getPassword(){
        return password;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSocket(Socket socket){
        this.socket = socket;
    }

    public Socket getSocket(){
        return socket;
    }
}
