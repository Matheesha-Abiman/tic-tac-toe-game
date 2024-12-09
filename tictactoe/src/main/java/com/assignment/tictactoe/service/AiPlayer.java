package com.assignment.tictactoe.service;

import com.assignment.tictactoe.controller.BoardController;
import javafx.scene.control.Button;

public class AiPlayer extends Player {

    private final BoardImpl boardImpl;

    public AiPlayer(BoardImpl boardImpl) {
        super(boardImpl);
        this.boardImpl = boardImpl;
    }

    public void move(int row, int col) {
        Piece piece = Piece.O;
        Button[][] aiButtons = BoardController.getButtons();

        int[] bestMove = chooseBestMove();

        if (bestMove != null) {
            int aiRow = bestMove[0];
            int aiCol = bestMove[1];

            if (aiButtons[aiRow][aiCol].getText().isEmpty()) {
                aiButtons[aiRow][aiCol].setText("O");
                aiButtons[aiRow][aiCol].setStyle("-fx-text-fill: #1e1209;");
                aiButtons[aiRow][aiCol].setDisable(true);

                boardImpl.updateMove(aiRow, aiCol, piece);
            } else {
                System.out.println("Invalid Button");
            }
        }
    }

    public int[] chooseBestMove() {
        int topScore = Integer.MIN_VALUE;
        int[] bestMove = null;

        Piece[][] currentBoard = boardImpl.getPieces();

        for (int i = 0; i < currentBoard.length; i++) {
            for (int j = 0; j < currentBoard[i].length; j++) {

                if (currentBoard[i][j] == Piece.EMPTY) {
                    currentBoard[i][j] = Piece.O;

                    int score = minimax(currentBoard, 0, false, false);

                    currentBoard[i][j] = Piece.EMPTY;

                    if (score > topScore) {
                        topScore = score;
                        bestMove = new int[]{i, j};
                    }
                }
            }
        }
        return bestMove;
    }

    public int minimax(Piece[][] currentBoard, int depth, boolean isMaximizing, boolean realMove) {
        Piece winner = boardImpl.checkWinner(realMove);

        if (winner == Piece.O) return 10 - depth; // AI wins
        if (winner == Piece.X) return depth - 10; // human wins
        if (boardImpl.isBoardFull()) return 0; // Draw

        if (isMaximizing) {
            int bestScore = Integer.MIN_VALUE;

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (currentBoard[i][j] == Piece.EMPTY) {
                        currentBoard[i][j] = Piece.O;

                        int score = minimax(currentBoard, depth + 1, false, false);

                        currentBoard[i][j] = Piece.EMPTY;

                        bestScore = Math.max(score, bestScore);
                    }
                }
            }
            return bestScore;
        } else {
            int bestScore = Integer.MAX_VALUE;

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (currentBoard[i][j] == Piece.EMPTY) {
                        currentBoard[i][j] = Piece.X;

                        int score = minimax(currentBoard, depth + 1, true, false);

                        currentBoard[i][j] = Piece.EMPTY;

                        bestScore = Math.min(score, bestScore);
                    }
                }
            }
            return bestScore;
        }
    }

}
