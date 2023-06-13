package com.project.clients;

import com.project.services.ClientServiceImpl;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

public class MainScreenController {
    public Button signInButton;
    public Button signUpButton;
    public TextField nameField;
    public PasswordField passwordField;
    public Label nickLabel;

    public void signInClick(ActionEvent actionEvent) {
        if(!(nameField.getText().equals("") && passwordField.getText().equals(""))) {
            if(ClientServiceImpl.getInstance().check(nameField.getText())){
                nickLabel.setTextFill(Color.BLACK);
                Stage stage = (Stage) signUpButton.getScene().getWindow();
                stage.close();
                ChatScreen chatScreen = new ChatScreen();
            }
        }
    }

    public void signUpClick(ActionEvent actionEvent) {
        if(!(nameField.getText().equals("") && passwordField.getText().equals(""))){
            if(!(ClientServiceImpl.getInstance().check(nameField.getText()))){
                Client client = new Client();
                client.setName(nameField.getText());
                client.setPassword(passwordField.getText());
                ClientServiceImpl.getInstance().create(client, nameField.getText());
                nickLabel.setTextFill(Color.BLACK);
                Stage stage = (Stage) signUpButton.getScene().getWindow();
                stage.close();
                ChatScreen chatScreen = new ChatScreen();
            } else {
                nameField.setText("");
                passwordField.setText("");
                nickLabel.setTextFill(Color.RED);
            }
        }
    }


    /*public void checkConnection(Socket gettedClientSocket){
        appendServerInputTextArea("Состояние: " + gettedClientSocket.isConnected());
        appendServerInputTextArea("IP: " + gettedClientSocket.getLocalSocketAddress());
        appendServerInputTextArea("Порт: " + gettedClientSocket.getLocalPort());
    }*/

}