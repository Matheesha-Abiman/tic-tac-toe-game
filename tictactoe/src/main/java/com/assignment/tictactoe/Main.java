package com.assignment.tictactoe;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Main extends Application {

    @Override
    public void start(Stage stage) {
        try {
            Parent loader = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/welcomePanel.fxml")));

            Scene scene = new Scene(loader);
            stage.setScene(scene);
            stage.setTitle("Tic-Tac-Toe");
            stage.setResizable(true);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public static void main(String[] args) {

        launch(args);
    }
}
