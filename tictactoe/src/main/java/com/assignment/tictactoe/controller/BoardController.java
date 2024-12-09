package com.assignment.tictactoe.controller;

import com.assignment.tictactoe.service.AiPlayer;
import com.assignment.tictactoe.service.BoardImpl;
import com.assignment.tictactoe.service.HumanPlayer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class BoardController {

    @FXML
    private Label aiScoreLabel;

    @FXML
    private GridPane boardGridPane;

    @FXML
    private Button button0_0;

    @FXML
    private Button button0_1;

    @FXML
    private Button button0_2;

    @FXML
    private Button button1_0;

    @FXML
    private Button button1_1;

    @FXML
    private Button button1_2;

    @FXML
    private Button button2_0;

    @FXML
    private Button button2_1;

    @FXML
    private Button button2_2;

    @FXML
    private Button exitButton;

    @FXML
    private AnchorPane gameUiAnchorPane;

    @FXML
    private Pane gameUiPane;

    @FXML
    private Label howWinLabel;

    @FXML
    private Button replayButton1;

    @FXML
    private Label userScoreLabel;

    private BoardImpl boardImpl = new BoardImpl();
    private static Button[][] buttons = new Button[3][3];

    private int userScore = 0;
    private int aiScore = 0;

    public static Button[][] getButtons() {
        return buttons;
    }

    @FXML
    public void initialize() {
        replayButton1.setVisible(false);
        howWinLabel.setVisible(false);

        buttons[0][0] = button0_0;
        buttons[0][1] = button0_1;
        buttons[0][2] = button0_2;
        buttons[1][0] = button1_0;
        buttons[1][1] = button1_1;
        buttons[1][2] = button1_2;
        buttons[2][0] = button2_0;
        buttons[2][1] = button2_1;
        buttons[2][2] = button2_2;

        updateScoreLabels();
    }

    @FXML
    void makeMove(ActionEvent event) {
        Button clickedButton = (Button) event.getSource();
        int row = -1, col = -1;

        if (clickedButton.getText().isEmpty()) {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (buttons[i][j] == clickedButton) {
                        row = i;
                        col = j;
                    }
                }
            }

            HumanPlayer humanPlayer = new HumanPlayer(boardImpl, clickedButton);
            humanPlayer.move(row, col);

            checkGameStatus();

            AiPlayer aiPlayer = new AiPlayer(boardImpl);
            aiPlayer.move(row, col);

            checkGameStatus();
        } else {
            System.out.println("Invalid move");
        }
    }

    private void checkGameStatus() {
        String result = boardImpl.checkForWinner();

        if (result != null) {
            loadResponse(result);
            disableButtons();
            enableReplayButton();
        }
    }

    public void loadResponse(String result) {
        howWinLabel.setVisible(true);

        if (result.equals("X")) {
            howWinLabel.setText("   "+WelcomePanelController.humanPlayerName +" "+ "You Won");
            userScore++;
        } else if (result.equals("O")) {
            howWinLabel.setText("   "+WelcomePanelController.humanPlayerName +" "+ "You Lost");
            aiScore++;
        } else {
            howWinLabel.setText("     It's a Draw");
        }

        updateScoreLabels();
    }

    public void disableButtons() {
        for (Button[] row : buttons) {
            for (Button cell : row) {
                cell.setDisable(true);
            }
        }
    }

    public void enableReplayButton() {
        replayButton1.setVisible(true);
    }

    @FXML
    void replay(ActionEvent event) {
        for (Button[] row : buttons) {
            for (Button cell : row) {
                cell.setText("");
                cell.setDisable(false);
            }
        }
        replayButton1.setVisible(false);
        howWinLabel.setVisible(false);
        boardImpl.resetBoard();
    }

    @FXML
    void exit(ActionEvent event) {

        System.exit(0);
    }

    private void updateScoreLabels() {
        userScoreLabel.setText("     User: " + userScore);
        aiScoreLabel.setText("       AI  : " + aiScore);
    }
}
