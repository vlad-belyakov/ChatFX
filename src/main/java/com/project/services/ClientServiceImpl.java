package com.project.services;

import com.project.servers.ServerClient;

import java.net.Socket;
import java.util.*;

public class ClientServiceImpl implements ClientService{

    private ClientServiceImpl(){
    }

    private static final ClientServiceImpl CSI = new ClientServiceImpl();
    private static final HashMap<String, ServerClient> CLIENT_DATABASE = new HashMap<>();


    public static ClientServiceImpl getInstance() {
        return CSI;
    }

    @Override
    public boolean create(ServerClient client){
        CLIENT_DATABASE.put(client.getName(), client);
        return true;
    }

    @Override
    public ServerClient read(String nickname) {
        return CLIENT_DATABASE.get(nickname);
    }

    @Override
    public List<ServerClient> readAll() {
        return new ArrayList<>(CLIENT_DATABASE.values());
    }

    @Override
    public boolean update(ServerClient client, String nickname){
        if(CLIENT_DATABASE.containsKey(nickname)){
            CLIENT_DATABASE.put(nickname,client);
            return true;
        }
        else return false;
    }

    @Override
    public boolean delete(String nickname){
        return CLIENT_DATABASE.remove(nickname) != null;
    }

    @Override
    public boolean identification(String nickname){
        return CLIENT_DATABASE.containsKey(nickname);
    }

    @Override
    public boolean authentification(String nickname, String password) {
        return CLIENT_DATABASE.get(nickname).getPassword().equals(password);
    }
}
