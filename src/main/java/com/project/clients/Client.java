package com.project.clients;

public class Client {
    private String name;
    private String password;


    public void setPassword(String password){
        this.password = password;
    }

    protected String getPassword(){
        return password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
