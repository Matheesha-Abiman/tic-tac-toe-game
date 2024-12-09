package com.assignment.tictactoe.service;

import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import java.awt.*;

public class HumanPlayer extends Player {

    Button button = new Button();
    public HumanPlayer(BoardImpl boardImpl, Button button) {
        super(boardImpl);
        this.button = button;
    }

    @Override
    public void move(int row, int col) {
        Piece piece = Piece.X;

        if(row !=(-1) && col !=(-1)){
            if (button.getText().isEmpty()) {
                button.setText("X");
                button.setStyle("-fx-text-fill: #892931;");
                button.setDisable(true);

                boardImpl.updateMove(row, col, piece);

            } else {
                System.out.println("Invalid Button");
            }
        }
    }
}
