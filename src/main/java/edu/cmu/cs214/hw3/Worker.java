package edu.cmu.cs214.hw3;

/**
 * Worker Class for worker status.
 * @author Xuezhen Dai (andrew ID: xuezhend)
 */
public class Worker {
    private Grid currentGrid;
    private boolean ifWin = false;
    /**
     * constructor
     * @param id
     */
    public Worker() {
    }
    /**
     * set worker initial location.
     * @param id
     * @param newLocation
     */
    public boolean setWorker(Grid newLocation) {
        if (newLocation.getOccupied() == true) {
            return false;
        }
        if (newLocation.getColumn() < 5 && newLocation.getRow() < 5) {
            this.currentGrid = newLocation;
            currentGrid.setOccupied(true);
            return true;
        }
        return false;
        
    }


    /**
     * return the current location
     * @return the current location of List.
     */
    public Grid getLocation() {
        return currentGrid;
    }

    /**
     * set the new location
     * @param row
     * @param column
     * @return if the action is done successfully
     */
    public boolean setLocation(Grid grid) {
        if (grid.getColumn() >= 5 || grid.getRow() >= 5) {
            System.out.println("You need to choose a grid on the board both less than 5");
            //need to remind the user with "You need to choose a grid on the board"
            return false;
        }
        if (checkIfValid(grid) == false) {
            System.out.println("This is not a valid move.");
            //need to remind the user with "This grid is not valid move"
            return false;
        }
        //change the old grid into false
        currentGrid.setOccupied(false);
        currentGrid = grid;
        //change the new grid into true
        currentGrid.setOccupied(true);
        if (currentGrid.getLayer() == 2) {
            ifWin = true;
        }
        return true;
    }
    /**
     * 
     * @param grid
     * @return boolean (if the move is valid)
     */
    private boolean checkIfValid(Grid grid) {
        int oldRow = currentGrid.getRow();
        int oldColumn = currentGrid.getColumn();
        int oldLayer = currentGrid.getLayer();
        int newRow = grid.getRow();
        int newColumn = grid.getColumn();
        int layer = grid.getLayer();
        
        if (grid.getOccupied() == true) {
            System.out.println("This grid has been occupied.");
            return false;
        }
        // There is no need to check this, since the game would end before this.
        // if (layer == 3) {
        //     System.out.println("There have been a dome on the top, you can't move to this gird anymore.");
        //     return false;
        // }
        if (oldColumn - newColumn > 1 || oldColumn - newColumn < -1) {
            return false;
        }
        if (oldRow - newRow > 1 || newRow - newColumn < -1) {
            return false;
        }
        if (oldLayer - layer > 1 || oldLayer - layer < -1) {
            return false;
        }
        return true;
    }

    /**
     * build the tower and return the status( if built successfully)
     * @param grid
     * @return
     */
    public boolean buildTower(Grid grid) {
        if (checkIfValidBuild(grid, currentGrid) == false ) {
            return false;
        }
        grid.addLayer();
        return true;
    }

    private boolean checkIfValidBuild(Grid grid, Grid currentLocation) {
        if (grid.getOccupied() == true) {
            System.out.println("This grid is occupied");
            return false;
        }
        if (grid.getLayer() == 3) {
            System.out.println("This grid had the dome, you cannot build or move to this grid.");
            return false;
        }
        if (Math.abs(grid.getColumn() - currentLocation.getColumn()) > 1 || Math.abs(grid.getRow() - currentLocation.getRow()) > 1) {
            System.out.println("This grid is too far.");
            return false;
        }
        return true;
    }

    /**
     * checks if this player have win.
     * @return boolean if win
     */
    public boolean getIfWin() {
        return ifWin;
    }

    /**
     * return valid moving grids for the worker.
     * @param grids
     * @return String of available grids
     */
    public String getValidMoveGrids(Grid[][] grids) {
        int row = currentGrid.getRow();
        int column = currentGrid.getColumn();
        int currentLayer = currentGrid.getLayer();
        int[] rowArray = {row - 1, row, row + 1};
        int[] columnArray = {column - 1, column, column + 1};
        String output = "";
        for (int i = 0; i < 3;i ++){
            for (int j = 0; j < 3;j++){
                if (rowArray[i] < 0 || columnArray[j] < 0 || rowArray[i] >= 5 || columnArray[j] >= 5 ) {
                    continue;
                }
                if (Math.abs(currentLayer - grids[rowArray[i]][columnArray[j]].getLayer()) >= 2) {
                    continue;
                }
                if ( grids[rowArray[i]][columnArray[j]].getOccupied() == false && grids[rowArray[i]][columnArray[j]].getLayer() != 3) {
                    String validGrids = "(" + (rowArray[i] + 1) +", " +(columnArray[j]+1) +")";
                    output = output +" " + validGrids;
                } 
            }
        }
        return String.valueOf(output);
    }

    public boolean isMovable(Grid[][] grids) {
        String validMove = getValidMoveGrids(grids);
        if (validMove == null || validMove.length() == 0) {
            return false;
        }
        return true;
    }
    
}
