package com.project.services;

import com.project.servers.ServerClient;
import java.net.Socket;
public class CommandsListener {

    //создание серверной версии клиента
    @Command("createClient")
    public boolean createClient(Object[] args){
        String nickname = (String) args[0];
        String password = (String) args[1];
        Socket socket = (Socket) args[2];
        if(!ClientServiceImpl.getInstance().identification(nickname)) {
            ServerClient client = new ServerClient(nickname, password, socket);
            ClientServiceImpl.getInstance().create(client);
            return true;
        } else {
            return false;
        }
    }

    //проверка существования клиента
    @Command("getClient")
    public boolean getClient(Object[] args){
        String nickname = (String) args[0];
        String password = (String) args[1];
        return ClientServiceImpl.getInstance().identification(nickname) && ClientServiceImpl.getInstance().authentification(nickname, password);
    }

    //подтверждение
    @Command("getTrue")
    public boolean getTrue(Object[] objects){
        return true;
    }

    //опровержение
    @Command("getFalse")
    public boolean getFalse(Object[] objects){
        return false;
    }
}
