package edu.cmu.cs214.hw3;
/**
 * Grid class for store the status and information related to the grid.
 * @author Xuezhen Dai (xuezhend)
 */
public class Grid {
    private int row;
    private int column;
    private String text;
    private boolean playable;
    private boolean selectable;
    private boolean buildable;
    private boolean movable;
    private Worker worker;
    //layer: get layer.
    private int playerInfo;
    private Tower tower;

    private boolean isOccupied;
    public Grid(int row, int column) {
        this.row = row;
        this.column = column;
        this.playable = true;
        this.playerInfo = -1;
        this.text = "";
        this.selectable = true;
        this.movable = false;
        this.buildable = false;
        isOccupied = false;
        tower = null;
    }

    public void setBuildable(boolean build) {
        this.buildable = build;
    }




    public void setMovable(boolean move) {
        this.movable = move;
    }

    public void setPlayerInfo(int i) {
        playerInfo = i;
    }

    public void setCurrentWorker(Worker worker) {
        this.worker = worker;
    }

    public Worker getCurrentWorker() {
        return this.worker;
    }

    public int getPlayerInfo() {
        return playerInfo;
    }

    public void setPlayable(boolean ifPlayable) {
        this.playable = ifPlayable;
    }
    /**
     * get row number.
     * @return row
     */
    public int getRow() {
        return row;
    }

    public void setText(String text) {
        this.text = text;
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
        if (tower.getLayer() < 3) {
            tower.buildBlock();
        } else if (tower.getLayer() == 3) {
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

    public void setSelectable(boolean selectable) {
        this.selectable = selectable;
    }

    /**
     * toString method, to  make sure the print.
     * No need to test.
     * @return String
     */
    @Override
    public String toString(){
    return """
        {
            "text": "%s",
            "playable": %b,
            "x": %d,
            "y": %d ,
            "layer": %d,
            "player": %d,
            "selectable" : %b,
            "movable" : %b,
            "buildable" : %b
        }
        """.formatted(this.text, this.playable, this.row, this.column,getLayer(),this.playerInfo,this.selectable,this.movable,this.buildable);
}
}
