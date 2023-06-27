package com.project.services;

import com.project.servers.ServerClient;

import java.util.List;

public interface ClientService {

    boolean create(ServerClient client);

    ServerClient read(String nickname);

    List<ServerClient> readAll();

    boolean update(ServerClient client, String nickname);

    boolean delete(String nickname);

    boolean identification(String nickname);

    boolean authentification(String nickname, String password);
}
