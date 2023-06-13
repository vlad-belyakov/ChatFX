package com.project.services;

import com.project.clients.Client;

import java.util.List;

public interface ClientService {

    boolean create(Client client, String nickname);

    Client read(String nickname);

    List<Client> readAll();

    boolean update(Client client, String nickname);

    boolean delete(String nickname);

    boolean check(String nickname);
}
