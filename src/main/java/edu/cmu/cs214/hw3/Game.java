package edu.cmu.cs214.hw3;

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
        Worker worker1 = new Worker();
        Worker worker2 = new Worker();
        Worker worker3 = new Worker();
        Worker worker4 = new Worker();
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

}
