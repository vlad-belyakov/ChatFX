package com.project.clients;

import com.project.services.Client;

import java.net.Socket;

public class UserClient extends Client {
    public UserClient(String name, String password, Socket socket) {
        super(name, password, socket);
    }

    public UserClient(String name, String password){
        super(name, password);
    }
}
