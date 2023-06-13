package com.project.clients;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;

public class ChatScreen{

    ChatScreen(){
        FXMLLoader fxmlLoader = new FXMLLoader(ChatScreen.class.getResource("chat-screen.fxml"));
        Stage stage = new Stage();
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 600, 400);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage.setTitle("Chati");
        InputStream iconStream = getClass().getResourceAsStream("chatIcon.png");
        Image image = new Image(iconStream);
        stage.getIcons().add(image);
        stage.setScene(scene);

        stage.show();
    }

}
