package com.assignment.tictactoe.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class WelcomePanelController implements Initializable {

    @FXML
    private Pane gamePane;

    @FXML
    private TextField nameText;

    @FXML
    private Button playButton;

    static String humanPlayerName = null;

    public void initialize(URL location, ResourceBundle resources) {
        gamePane.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ENTER) {
                playButton.fire();
            }
        });
    }

    @FXML
    void play(ActionEvent event) throws IOException {
        humanPlayerName = nameText.getText();

        if(!humanPlayerName.isEmpty()){
            gamePane.getChildren().add(FXMLLoader.load(this.getClass().getResource("/view/boardUi.fxml")));
        }else{
            JOptionPane.showMessageDialog(null, "Enter Your Name");
        }

    }

}
