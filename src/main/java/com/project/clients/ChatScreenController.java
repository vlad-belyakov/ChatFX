package com.project.clients;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ChatScreenController extends Thread{
    public TextArea textArea;
    public TextField textField;
    public Button sendButton;
    public UserClient userClient;
    public ClientSend clientSend;

    @FXML
    private void initialize(){
        textArea.setStyle("-fx-control-inner-background:#0B0C10; -fx-background-color:#0B0C10");
    }

    //обработка отправляемого текста (начинать текст с символа "@" запрещено,
    // подобное используется для отправки команд серверу
    @FXML
    protected void sendButtonActivated() {
        String text;
        if(textField.getText().startsWith("@"))
            text = textField.getText().replace("@", "");
        else text = textField.getText();
        clientSend.sendMessage(text);
        appendTextArea(text, userClient.getName());
        textField.setText("");
    }

    protected void appendTextArea(String msg, String name){
        textArea.appendText(new SimpleDateFormat("E dd.MM.yyyy hh:mm' '").format(Calendar.getInstance().getTime())
                + name
                + ": "
                + msg + "\n");
    }

}
