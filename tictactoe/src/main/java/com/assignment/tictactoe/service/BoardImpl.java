package com.assignment.tictactoe.service;

import com.assignment.tictactoe.service.Board;

public class BoardImpl {
    private Piece[][] pieces;

    public BoardImpl() {
        pieces = new Piece[3][3];
        initializeBoard();
    }

    public void initializeBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                pieces[i][j] = Piece.EMPTY;
            }
        }
    }

    public void updateMove(int row, int col, Piece piece) {
        if (isLegalMove(row, col)) {
            pieces[row][col] = piece;
        }

        checkWinner(true);
    }

    public boolean isLegalMove(int row, int col) {
        return row >= 0 && row < 3 && col >= 0 && col < 3 && pieces[row][col] == Piece.EMPTY;
    }

    public Piece checkWinner(boolean realMove) {

        for (int i = 0; i < 3; i++) {
            if (pieces[i][0] != Piece.EMPTY && pieces[i][0] == pieces[i][1] && pieces[i][1] == pieces[i][2]) {
                if (realMove) {
                    passToWinner(pieces[i][0], i, 0, i, 1, i, 2);
                }
                return pieces[i][0];
            }
        }

        for (int i = 0; i < 3; i++) {
            if (pieces[0][i] != Piece.EMPTY && pieces[0][i] == pieces[1][i] && pieces[1][i] == pieces[2][i]) {
                if (realMove) {
                    passToWinner(pieces[0][i], 0, i, 1, i, 2, i);
                }
                return pieces[0][i];
            }
        }

        if (pieces[0][0] != Piece.EMPTY && pieces[0][0] == pieces[1][1] && pieces[1][1] == pieces[2][2]) {
            if (realMove) {
                passToWinner(pieces[0][0], 0, 0, 1, 1, 2, 2);
            }
            return pieces[0][0];
        }

        if (pieces[0][2] != Piece.EMPTY && pieces[0][2] == pieces[1][1] && pieces[1][1] == pieces[2][0]) {
            if (realMove) {
                passToWinner(pieces[0][2], 0, 2, 1, 1, 2, 0);
            }
            return pieces[0][2];
        }

        return null;
    }

    public void passToWinner(Piece winnerPiece, int row1, int col1, int row2, int col2, int row3, int col3) {

    }

    public Piece[][] getPieces() {
        return pieces;
    }

    public boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (pieces[i][j] == Piece.EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }

    public String checkForWinner() {
        Piece winner = checkWinner(false);

        if (winner != null) {
            return winner == Piece.X ? "X" : "O";
        }

        if (isBoardFull()) {
            return "draw";
        }

        return null;
    }

    public void resetBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                pieces[i][j] = Piece.EMPTY;
            }
        }
    }
}
