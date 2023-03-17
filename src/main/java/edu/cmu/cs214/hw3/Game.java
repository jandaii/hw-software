package edu.cmu.cs214.hw3;

import java.util.ArrayList;

/**
 * Game class for the game to manipulate the turn.
 * @author Xuezhen Dai xuezhend
 */
public class Game {
    private int round = 0;
    Board board;
    int turn;
    private Player currentPlayer;
    private Player player1;
    private Player player2;
    /**
     * constructor.
     */
    public Game() {
        
    }

    public void changeSelectMode() {
        Grid[][] cells = board.getGrids();
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j< 5; j++) {
                if (cells[i][j].getPlayerInfo() == currentPlayer.getPlayerId()) {
                    cells[i][j].setSelectable(true);
                } else {
                    cells[i][j].setSelectable(false);
                }
            }
        }
    }

    public void changeAllMovable() {
        Grid[][] cells = board.getGrids();
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j< 5; j++) {
                
                cells[i][j].setMovable(false);
            }
        }
    }

    public boolean ifEnd() {
        if (player1.getWinStatus() == true || player2.getWinStatus() == true) {
            return true;
        }
        return false;
    }

    /**
     * return the current player
     * @return the current player
     */
    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     * return player1
     * @return player 1
     */
    public Player getPlayer1() {
        return player1;
    }

    /**
     * return player2
     * @return player2
     */
    public Player getPlayer2() {
        return player2;
    }


    /**
     * initialize a new game.
     */
    public void initialization() {
        round ++;
        player1 = new Player(1);
        player2 = new Player(2);
        board = new Board();
        Worker worker1 = new Worker(0);
        Worker worker2 = new Worker(1);
        Worker worker3 = new Worker(2);
        Worker worker4 = new Worker(3);
        player1.addWorker(worker1);
        player1.addWorker(worker2);
        player2.addWorker(worker3);
        player2.addWorker(worker4);
        currentPlayer = player1;
    }

    /**
     * return the current board.
     * @return the current board
     */
    public Board getBoard() {
        return board;
    }
    /**
     * change the current Player.
     * @param player
     * @return
     */
    public Player getAnotherPlayer(Player player) {
        if (player == player1) {
            return player2;
        }
        return player1;
    }

    /**
     * there are 2 players, and switch between them.
     */
    public void changeCurrentPlayer() {
        currentPlayer = getAnotherPlayer(currentPlayer);
    }

    /**
     * return the round No.
     * @return
     */
    public int getRound() {
        return round;
    }

    /**
     * current player to move the worker.
     * @param worker
     * @param gridLocation
     * @return
     */
    public boolean move(Worker worker, int[] gridLocation) {
        if (gridLocation == null) {
            return false;
        }
        Grid[][] grids = getBoard().getGrids();
        int row = gridLocation[0] - 1;
        int column = gridLocation[1] - 1;
        Grid goalGrid= grids[row][column];
        if (currentPlayer.moveWorker(worker, goalGrid) == false) {
            return false;
        }
        return true;
    }

    /**
     * set the worker into the grid
     * @param x
     * @param y
     * @param worker
     * @return
     */
    public Game move(int x, int y, Worker worker) {
        if (getBoard().getGrid(x, y) == null) {
            return this;
        }
        Grid location = getBoard().getGrid(x, y);
        worker.setWorker(location);
        return this;
    }

    /**
     * build the tower
     * @param nowWorker
     * @param buildPlace
     * @return
     */
    public boolean build(Worker nowWorker, String buildPlace) {
        Grid buildGrid = this.getBoard().scanGetGrid(buildPlace);
        if (nowWorker.buildTower(buildGrid) == false) {
            System.out.println("It is not a valid build. please try again");
            return false;
        }
        System.out.println("You successfully build a tower on (" + (buildGrid.getRow() + 1) +", " + (buildGrid.getColumn() + 1) + ") and the height now is " + buildGrid.getLayer() );
        return true;
    }

    /**
     * build tower for js UI.
     * @return board
     */
    public Board build(int x, int y, Worker nowWorker) {
        if (getBoard().getGrid(x, y) == null) {
            return getBoard();
        }
        Grid buildGrid = this.getBoard().getGrid(x, y);
        nowWorker.buildTower(buildGrid);
        return getBoard();
    }

    /**
     * set the worker at the right place and return the board
     * @param x
     * @param y
     * @param worker
     * @return
     */
    // set the worker in the right place return the board. or game?
    public Board setWorker(int x, int y, Worker worker) {
        if (getBoard().getGrid(x, y) == null ) {
            return null;
        }
        worker.setWorker(getBoard().getGrid(x, y));
        getBoard().getGrid(x, y).setPlayable(false);
        getBoard().getGrid(x, y).setText(worker.PlayerID());
        getBoard().getGrid(x, y).setCurrentWorker(worker);
        getBoard().getGrid(x, y).setPlayerInfo(getCurrentPlayer().getPlayerId());
        getBoard().getGrid(x, y).setSelectable(false);

        return getBoard();
    }

    public void changeMoveGrid(Worker workerchange) {
        ArrayList<Grid> getArrayGrid = new ArrayList<Grid>();
        getArrayGrid =  workerchange.getValidMoveGridsArray(getBoard().getGrids());
        for (Grid[] j : getBoard().getGrids()) {
            for (Grid m : j) {
                m.setMovable(false);
            }
        }
        for (Grid i : getArrayGrid) {
            i.setMovable(true);
        }
    }
    public void changeBuild(Worker workerchange) {
        ArrayList<Grid> getArrayGrid = new ArrayList<Grid>();
        getArrayGrid =  workerchange.getValidbuildGridsArray(getBoard().getGrids());
        for (Grid[] j : getBoard().getGrids()) {
            for (Grid m : j) {
                m.setBuildable(false);
            }
        }
        for (Grid i : getArrayGrid) {
            i.setBuildable(true);
        }
    }

    public void changeAvailable() {
        for (Grid[] j : getBoard().getGrids()) {
            for (Grid m : j) {
                m.setBuildable(false);
                m.setMovable(false);
                m.setSelectable(false);
            }
        }
    }




}
