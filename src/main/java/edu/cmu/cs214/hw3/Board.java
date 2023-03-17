package edu.cmu.cs214.hw3;

import java.util.Arrays;

/**
 * Board class for the game deck.
 * @author Xuezhen Dai (xuezhend)
 */
public class Board {
    /**
     * player1 play first.
     */

    private static final int ROW = 5;
    private static final int COLUMN = 5;
    
    private Grid[][] grids;

    /**
     * Constructor.
     * Start with an empty board
     */
    public Board() {
        grids = new Grid[ROW][COLUMN];
        //start from 0
        for (int i = 0;i < ROW; i ++) {
            for (int m = 0; m < COLUMN; m ++){
                grids[i][m] = new Grid(i, m);
            }
        }
    }

    /**
     * get the array of the grids of the board.
     * @return array of grids Grid[][]
     */
    public Grid[][] getGrids() {
        return grids;
    }

    /**
     * get a single grid of the board.
     * Getter no need to test.
     * @param x
     * @param y
     * @return grid
     */
    public Grid getGrid(int x, int y) {
        if (x > ROW || x < 0 || y > COLUMN || y < 0) {
            return null;
        }
        return this.grids[x][y];
    }

    /**
     * scan the input and return the grid.
     * @param arg
     * @return Grid
     */
    Grid scanGetGrid(String arg) {
        if (arg == null || arg.length() == 0) {
            return null;
        }
        String[] grid = arg.split(",");
        if (grid.length != 2) {
            return null;
        }
        int[] gridInt = new int[2];
        for (int i = 0; i < 2; i ++) {
            try {
                gridInt[i] = Integer.valueOf(grid[i]) - 1;
                if (gridInt[i] >= 5) {
                    System.out.print(gridInt[i]);
                    System.out.println("There are only 5*5 grids on the board, please choose a right one.");
                    return null;
                }
            } catch (Exception e) {
                return null;
            }
        }
        return getGrids()[gridInt[0]][gridInt[1]];
    }

    private Grid[] getGridss() {
        Grid gridss[] = new Grid[25];
        for(int x = 0;x <=4;x++) {
            for (int y = 0; y<= 4;y++) {
                gridss[5 * y + x] = getGrid(x, y);
            }
        }
        return gridss;
    }

    @Override
    public String toString() {
        return """
                { "cells": %s}
                """.formatted(Arrays.toString(this.getGridss()));
    }

}
