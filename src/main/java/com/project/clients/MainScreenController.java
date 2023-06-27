package com.project.clients;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class MainScreenController extends Thread{
    public Button signInButton;
    public Button signUpButton;
    public TextField nameField;
    public PasswordField passwordField;
    public Label nickLabel;
    private ClientGet clientGet;
    private boolean pass = false;
    private boolean waiting = true;
    private ClientSend clientSend;
    private UserClient userClient;

    @FXML
    public void initialize(){
       this.start();
    }

    //вход в уже существующую учетную запись
    @FXML
    public void signInClick(ActionEvent actionEvent) {
        if(!(nameField.getText().equals("") && passwordField.getText().equals("")
                && nameField.getText() != null && passwordField.getText() != null)) {
            clientSend.sendCommand(String.format("@getClient %s %s", nameField.getText(), passwordField.getText()));
            while (waiting){
                System.out.print("");
            }
            waiting = true;
            if(pass){
                nickLabel.setTextFill(Color.BLACK);
                Stage stage = (Stage) signUpButton.getScene().getWindow();
                userClient = new UserClient(nameField.getText(), passwordField.getText());
                userClient.setSocket(clientGet.getSocket());
                nameField.setText("");
                passwordField.setText("");
                stage.close();
                pass = false;
                new ChatScreen(userClient, clientSend, clientGet);
            } else {
                nickLabel.setText("Неверный логин/пароль");
                nameField.setText("");
                passwordField.setText("");
                nickLabel.setTextFill(Color.RED);
            }
        } else {
            nickLabel.setText("Заполните все поля");
            nickLabel.setTextFill(Color.RED);
        }
    }

    //создание учетной записи
    @FXML
    public void signUpClick(ActionEvent actionEvent) {
        if(!(nameField.getText().equals("") && passwordField.getText().equals("")
        && nameField.getText() != null && passwordField.getText() != null)){
            clientSend.sendCommand(String.format("@createClient %s %s", nameField.getText(), passwordField.getText()));
            while (waiting){
                System.out.print("");
            }
            waiting = true;
            if(pass){
                nickLabel.setTextFill(Color.BLACK);
                userClient = new UserClient(nameField.getText(), passwordField.getText(), clientGet.getSocket());
                nameField.setText("");
                passwordField.setText("");
                Stage stage = (Stage) signUpButton.getScene().getWindow();
                stage.close();
                pass = false;
                new ChatScreen(userClient, clientSend, clientGet);
            } else {
                nickLabel.setText("Пользователь с таким логином уже существует");
                nameField.setText("");
                passwordField.setText("");
                nickLabel.setTextFill(Color.RED);
            }
        } else {
            nickLabel.setText("Заполните все поля");
            nickLabel.setTextFill(Color.RED);
        }
    }

    @Override
    public void run(){
        clientSend = new ClientSend();
        clientGet = new ClientGet(clientSend.clientSocket, this);
    }

    public void setPass(boolean pass) {
        this.pass = pass;
    }

    public void setWaiting(boolean waiting) {
        this.waiting = waiting;
    }
}