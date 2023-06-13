package com.project.services;

import com.project.clients.Client;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClientServiceImpl implements ClientService{

    private ClientServiceImpl(){
    }

    private static final ClientServiceImpl CSI = new ClientServiceImpl();
    private static final Map<String, Client> CLIENT_DATABASE = new HashMap<>();


    public static ClientServiceImpl getInstance() {
        return CSI;
    }

    @Override
    public boolean create(Client client, String nickname){
        client.setName(nickname);
        CLIENT_DATABASE.put(nickname,client);
        return true;
    }

    @Override
    public Client read(String nickname) {
        return CLIENT_DATABASE.get(nickname);
    }

    @Override
    public List<Client> readAll() {
        return new ArrayList<>(CLIENT_DATABASE.values());
    }

    @Override
    public boolean update(Client client, String nickname){
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
    public boolean check(String nickname){
        if(CLIENT_DATABASE.get(nickname) != null) {
            return true;
        } else return false;
    }
}
