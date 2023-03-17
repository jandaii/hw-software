package edu.cmu.cs214.hw3;

import java.util.Arrays;

public class GameState {

    private final Cell[] cells;

    private GameState(Cell[] cells) {
        this.cells = cells;
    }

    //update the cells
    public static GameState forGame(Game2 game) {
        Cell[] cells = getCells(game);
        return new GameState(cells);
    }

    public Cell[] getCells() {
        return this.cells;
    }

    /**
     * toString() of GameState will return the string representing
     * the GameState in JSON format.
     */
    @Override
    public String toString() {
        return """
                { "cells": %s}
                """.formatted(Arrays.toString(this.cells));
    }

    private static Cell[] getCells(Game2 game) {
        Cell cells[] = new Cell[25];
        Board2 board = game.getBoard();
        for (int x = 0; x <= 4; x++) {
            for (int y = 0; y <= 4; y++) {
                String text = "";
                boolean playable = false;
                int layer = 0;
                int playerInfo = 0;
                Player2 player = board.getCell(x, y);
                if (player == Player2.PLAYER0)
                    text = "X";
                else if (player == Player2.PLAYER1)
                    text = "O";
                else if (player == null) {
                    playable = true;
                }
                cells[5 * y + x] = new Cell(x, y, text, playable,layer, playerInfo);
            }
        }
        return cells;
    }
}

class Cell {
    private final int x;
    private final int y;
    private final String text;
    private final boolean playable;
    private final int layer;
    private final int playerInfo;

    Cell(int x, int y, String text, boolean playable, int layer, int playerInfo) {
        this.x = x;
        this.y = y;
        this.text = text;
        this.playable = playable;
        this.layer = layer;
        this.playerInfo = playerInfo;
    }
    public int getLayer() {
        return layer;
    }
    public int getplayerInfo() {
        return playerInfo;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getText() {
        return this.text;
    }

    public boolean isPlayable() {
        return this.playable;
    }

    @Override
    public String toString() {
        return """
                {
                    "text": "%s",
                    "playable": %b,
                    "x": %d,
                    "y": %d ,
                    "layer": %d,
                    "player": %d
                }
                """.formatted(this.text, this.playable, this.x, this.y,this.layer,this.playerInfo);
    }
}