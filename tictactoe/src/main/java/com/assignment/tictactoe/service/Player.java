package com.assignment.tictactoe.service;

import javafx.scene.control.Button;

import java.awt.*;

public abstract class Player {
    protected BoardImpl boardImpl;

    public Player(BoardImpl boardImpl) {
        this.boardImpl = boardImpl;
    }

    public abstract void move(int row, int col);
}
