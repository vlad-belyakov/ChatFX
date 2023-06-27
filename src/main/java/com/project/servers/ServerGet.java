package com.project.servers;

import com.project.services.ClientServiceImpl;
import com.project.services.Command;
import com.project.services.CommandsListener;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

class ServerGet implements Runnable{

    private Socket gettedClientSocket;
    private BufferedReader in;
    private ServerSend serverSend;
    private ServerClient serverClient;
    private Map<String, Method> methodMap = new HashMap<>();
    private CommandsListener cl = new CommandsListener();



    //автоматическое добавление методов с аннотацией Command

    public ServerGet(Socket socket){
        gettedClientSocket = socket;
        for (Method method : cl.getClass().getDeclaredMethods()){
            if(method.isAnnotationPresent(Command.class)){
                Command command = method.getAnnotation(Command.class);
                methodMap.put(command.value(), method);
            }
        }
    }

    //логика получения сообщений/команд от пользователей
    @Override
    public void run(){
        try {
            serverSend = new ServerSend(gettedClientSocket);
            in = new BufferedReader(new InputStreamReader(gettedClientSocket.getInputStream()));
            while (true) {
                String msg = in.readLine();
                if (msg != null && !msg.equals("")) {
                    if(msg.startsWith("@")) {
                        msg = msg.replace("@", "");
                        String[] args = msg.split(" ");
                        Object[] nArgs = new Object[3];
                        nArgs[0] = args[1];
                        nArgs[1] = args[2];
                        nArgs[2] = gettedClientSocket;
                        Method method = methodMap.get(args[0]);
                        if ((boolean) method.invoke(cl, new Object[]{nArgs})) {
                            serverSend.cmdSend("@getTrue");
                            serverClient = ClientServiceImpl.getInstance().read(args[1]);
                        } else {
                            serverSend.cmdSend("@getFalse");
                        }
                    }  else {
                        serverSend.msgSend(msg, serverClient.getName());
                    }
                }
            }
        } catch (IOException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        } finally {
            try {
                gettedClientSocket.close();
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
