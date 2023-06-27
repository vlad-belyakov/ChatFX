package com.project.servers;

import java.net.Socket;

public class ServerClient extends com.project.services.Client {
    public ServerClient(String name, String password, Socket socket){
        super(name, password, socket);
    }
}
