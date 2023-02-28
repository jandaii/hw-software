package edu.cmu.cs214.hw3;
import java.util.Scanner;

/**
 * UI class for System.in and System.out.
 * @author Xuezhen Dai (andrew ID: xuezhend)
 */
public class UI {
    Player currentPlayer;
    public Game gameNow;

    private boolean ifMoved = false;
    private Worker nowWorker;

    /**
     * the constructor.
     * @param game
     */
    public UI(Game game) {
        // initialize the game.
        // initialize the players and workers.
        System.out.println("Game start");
        gameNow = game;
        boolean ifNewRound = true;
        while (ifNewRound) {
            ifNewRound = newRound(game);
        }
    }

    /**
     * start a new round and return if this is successful.
     * There are scanner in this method, cannot be tested.
     * @param game
     * @return boolean if this action is success.
     */
    //should to be private but for testing made into public
    public boolean newRound(Game game) {
        gameNow.initialization();
        // put the workers into the grid.
        
        Scanner sc = new Scanner(System.in);
        currentPlayer = game.getCurrentPlayer();
        System.out.println("This is round " + game.getRound());
        System.out.println("------------------------------------------------------------");
        initialSettingWorker(game, sc);

        //move and build process
        oneTurnAction(game, sc);
        System.out.println("Do you want to start a new round?(Y/Yes,N/No)");
        String ifnewRound = sc.nextLine().trim();
        if (ifnewRound.equalsIgnoreCase("Y") || (ifnewRound).equalsIgnoreCase("Yes")) {
            return true;
        }
        return false;
    }

    /**
     * Every turn the players need to finish.
     * System.in cannot be tested.
     * @param game
     * @param sc
     */
    private void oneTurnAction(Game game, Scanner sc) {
        while (true) {
            // move action.
            if (ifMoved == false) {
                if (currentPlayer.ifMovable(game.getBoard()) == false) {
                    currentPlayer = game.getAnotherPlayer(currentPlayer);
                    System.out.println("The player " + currentPlayer + " has won!");
                    break;
                }
                System.out.println("Player " + currentPlayer +" now move your worker");
                System.out.println("Please choose a valid worker to move.");
                String workerInfo = sc.nextLine().trim();
                if (workerInfo == null) {
                    System.out.println("Please choose a worker!");
                    continue;
                }
                try {
                    int workerGridInfo = Integer.parseInt(workerInfo) - 1;
                    nowWorker = currentPlayer.getWorkers().get(workerGridInfo);
                    System.out.println("Available grids for current worker is " + nowWorker.getValidMoveGrids(game.getBoard().getGrids()));
                    System.out.println("Now please enter a grid.(row, column)");
                    String gridInfo = sc.nextLine().trim();
                    int[] GridInfo = locationScanner(gridInfo);
                    if (GridInfo == null) {
                        System.out.println("You need to enter the valid value (row, column),eg (3, 3)");
                    }
                if (gameNow.move(nowWorker,GridInfo) == false) {
                    System.out.println("This is not a valid move.");
                    continue;
                }
                if (gameNow.ifEnd() == true) {
                    System.out.println("The player " + currentPlayer + " has won!");
                    break;
                }
                ifMoved = true;
                } catch (Exception e) {
                    System.out.println("Please enter a valid worker.");
                    continue;
                }
                
            }
            // build action.
            System.out.println("You have successfully moved your worker.");
            System.out.println("Player " + currentPlayer +" now build your tower");
            
            System.out.println("Please now choose a valid grid to build your tower");
            String buildPlace = sc.nextLine().trim();
            if (gameNow.build(nowWorker, buildPlace) == false) {
                continue;
            } 
            nowWorker = null;
            currentPlayer = game.getAnotherPlayer(currentPlayer);
            System.out.println("Now is Player " + currentPlayer + " is playing");
            ifMoved = false;
        }
    }

    /**
     * for set the workers into the board.
     * There is a Scanner, can't test.
     * @param game
     * @param sc
     */
    private void initialSettingWorker(Game game, Scanner sc) {
        System.out.println("Player1 please set your workers.");
        for (int i = 0; i < 2; i++) {
            int j = 0;
            while (true) {
                System.out.println("Please set your worker"+(j+1)+" location. like row, column");
                String input = sc.nextLine().trim();
                Grid location = gameNow.getBoard().scanGetGrid(input);
                if (location == null) {
                    System.out.println("there are something wrong about your input, please input like \"3, 2 \"" + input);
                    continue;
                } else if (currentPlayer.getWorkers().get(j).setWorker(location) == false ) {
                    System.out.println("There is already a worker now, please pick another one");
                    continue;
                } else {
                    j++;
                    System.out.println("Successfully set your worker at " + location);
                }
                if (j == 2) {
                    break;
                }
            }
            currentPlayer = game.getAnotherPlayer(currentPlayer);
            System.out.println("Now it's player " + currentPlayer + " turn");
        }
    }

    /**
     * extract the location from String.
     * need to be private (non-static) but set into public for testing.
     * @param response
     * @return
     */
    public static int[] locationScanner(String response) {
        String[] grid = response.split(",");
        int [] gridInt = new int[2];
        for (int i = 0; i < 2;i ++) {
            try {
                gridInt[i] = Integer.valueOf(grid[i]);
            } catch (Exception e) {
                return null;
            }
        }
        return gridInt;
    }


 }
