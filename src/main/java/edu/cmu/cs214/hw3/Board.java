package edu.cmu.cs214.hw3;

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

}
