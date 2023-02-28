package edu.cmu.cs214.hw3;
/**
 * Grid class for store the status and information related to the grid.
 * @author Xuezhen Dai (xuezhend)
 */
public class Grid {
    private int row;
    private int column;
    private Tower tower;

    private boolean isOccupied;
    public Grid(int row, int column) {
        this.row = row;
        this.column = column;
        isOccupied = false;
        tower = null;
    }
    /**
     * get row number.
     * @return row
     */
    public int getRow() {
        return row;
    }

    /**
     * get column number.
     * @return column
     */
    public int getColumn() {
        return column;
    }

    /**
     * get layer number.
     * @return layer
     */
    public int getLayer() {
        if (tower == null) {
            return 0;
        }
        return tower.getLayer();
    }

    /**
     * build layer.
     */
    public void addLayer() {
        if (tower == null) {
            tower = new Tower(0);
        }
        if (tower.getLayer() < 2) {
            tower.buildBlock();
        } else if (tower.getLayer() == 2) {
            tower.buildDome();
        }
    }
    /**
     * set if the grid is occupied.
     * @param isOccupied
     */
    public void setOccupied(boolean isOccupied) {
        this.isOccupied = isOccupied;
    }

    /**
     * get the occupying status.
     * @return boolean if occupied
     */
    public boolean getOccupied() {
        return isOccupied;
    }

    /**
     * toString method, to make sure the print.
     * No need to test.
     * @return String
     */
    public String toString(){
        return String.valueOf(row) + String.valueOf(column);
    }
}
