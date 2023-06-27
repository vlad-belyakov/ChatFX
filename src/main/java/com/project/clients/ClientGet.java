package com.project.clients;

import com.project.services.Command;
import com.project.services.CommandsListener;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;


public class ClientGet implements Runnable{

    public ChatScreenController chatScreenController;
    private BufferedReader in;
    private final MainScreenController mainScreenController;
    private Socket clientSocket;
    public static Map<String, Method> methodMap = new HashMap<>();
    private static final CommandsListener cl = new CommandsListener();

    //автоматическое добавление методов с аннотацией Command
    static {
        for (Method method : cl.getClass().getDeclaredMethods()){
            if(method.isAnnotationPresent(Command.class)){
                Command command = method.getAnnotation(Command.class);
                methodMap.put(command.value(), method);
            }
        }
    }

    public ClientGet(Socket clientSocket, MainScreenController mainScreenController){
        this.clientSocket = clientSocket;
        this.mainScreenController = mainScreenController;
        Thread clientGetThread = new Thread(this,"ClientGetThread");
        try {
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        clientGetThread.start();
    }

    //получение сообщений от сервера и обработка команд
    @Override
    public void run() {
        String msg;
        while (true){
            try {
                if ((msg = in.readLine()) != null) {
                    if(msg.startsWith("@")){
                        msg = msg.replace("@", "");
                        Method method = methodMap.get(msg);
                        Object[] objects = new Object[0];
                        mainScreenController.setPass((boolean) method.invoke(cl, new Object[]{objects}));
                        mainScreenController.setWaiting(false);
                    } else {
                        String[] message = msg.split("/");
                        StringBuilder mes = new StringBuilder(message[1]);
                        if(message.length >= 3) {
                            for (int i = 2; i <= message.length - 1; i++) {
                                mes.append("/").append(message[i]);
                            }
                        } else {
                            mes = new StringBuilder(message[1]);
                        }
                        msg = mes.toString();
                        chatScreenController.appendTextArea(msg, message[0]);
                    }
                }
            }catch (IOException | InvocationTargetException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    public Socket getSocket(){
        return clientSocket;
    }

    public void setChatScreenController(ChatScreenController chatScreenController) {
        this.chatScreenController =  chatScreenController;
    }
}
