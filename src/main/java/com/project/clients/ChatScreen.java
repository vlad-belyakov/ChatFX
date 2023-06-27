package com.project.clients;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;

public class ChatScreen {
    public ChatScreen(UserClient userClient, ClientSend clientSend, ClientGet clientGet) {

        FXMLLoader fxmlLoader = new FXMLLoader(ChatScreen.class.getResource("chat-screen.fxml"));
        Stage stage = new Stage();
        Scene scene;
        try {
            scene = new Scene(fxmlLoader.load(), 600, 400);
        } catch (
                IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        InputStream iconStream = getClass().getResourceAsStream("chatIcon.png");
        Image image = new Image(iconStream);
        stage.getIcons().add(image);
        stage.setOnCloseRequest(windowEvent -> System.exit(0));
        stage.setScene(scene);
        ChatScreenController chatScreenController = fxmlLoader.getController();
        chatScreenController.userClient = userClient;
        chatScreenController.clientSend = clientSend;
        clientGet.setChatScreenController(chatScreenController);
        stage.setTitle(userClient.getName());
        stage.show();
    }
}
