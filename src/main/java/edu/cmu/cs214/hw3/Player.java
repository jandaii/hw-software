package edu.cmu.cs214.hw3;

import java.util.ArrayList;
import java.util.List;
public class Player {
    List<Worker> playerListWorker = new ArrayList<Worker>();
    int playerId;
    boolean ifWin;

    public Player(int id) {
        playerId = id;
        ifWin = false;
    }
    /**
     * add workers into the player.
     * @param worker
     */
    void addWorker(Worker worker) {
        playerListWorker.add(worker);
    }

    /**
     * return the worker list.
     * @return worker list. 
     */
    public List<Worker> getWorker() {
        return playerListWorker;
    }
    /**
     * move the worker to the new grid
     * @param worker
     * @param grid
     * @return if the move is successful
     */
    public boolean moveWorker(Worker worker, Grid grid) {
        if (worker.setLocation(grid) == true) {
            return true;
        }
        return false;
    }

    /**
     * get if the worker won
     * @param worker
     * @return if the worker won
     */
    public boolean getWinStatus() {
        for (Worker worker: playerListWorker) {
            if (worker.getIfWin() == true) {
                return true;
            }
        }
        return false;
        
    }

    /**
     * choose a worker to build a new tower
     * @param worker
     * @param newGrid
     * @return if the worker successfully build a new tower
     */
    public boolean buildTower(Worker worker, Grid newGrid) {
        if (worker.buildTower(newGrid)) {
            return true;
        }
        return false;
    }

    /**
     * check if the player is still movable, if false, the other player wins.
     * @param board
     * @return boolean if the player is still movable
     */
    public boolean ifMovable(Board board) {
        Grid[][] grids = board.getGrids();
        for (Worker worker: playerListWorker) {
            if (worker.isMovable(grids) == true) {
                return true;
            }
        }
        return false;
    }


    @Override
    public String toString() {
        return String.valueOf(playerId);
    }

    
}
