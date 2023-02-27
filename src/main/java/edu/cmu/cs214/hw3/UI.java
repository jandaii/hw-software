package edu.cmu.cs214.hw3;
import java.util.Scanner;
public class UI {
    private Player currentPlayer;
    private Game gameNow;

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
        buildTower(game, sc);
        System.out.println("Do you want to start a new round?(Y/Yes,N/No)");
        String ifnewRound = sc.nextLine().trim();
        if (ifnewRound.equalsIgnoreCase("Y") || (ifnewRound).equalsIgnoreCase("Yes")) {
            return true;
        }
        return false;
    }

    private void buildTower(Game game, Scanner sc) {
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
                    Worker nowWorker = currentPlayer.getWorker().get(workerGridInfo);
                    System.out.println("Available grids for current worker is " + nowWorker.getValidMoveGrids(game.getBoard().getGrids()));
                    System.out.println("Now please enter a grid.(row, column)");
                    String gridInfo = sc.nextLine().trim();
                    int[] GridInfo = locationScanner(gridInfo);
                    if (GridInfo == null) {
                    System.out.println("You need to enter the valid value (row, column),eg (3, 3)");
                }
                if (move(workerGridInfo,GridInfo) == false) {
                    System.out.println("This is not a valid move.");
                    continue;
                }
                if (currentPlayer.getWinStatus() == true) {
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
            Grid buildGrid = scanGetGrid(buildPlace);
            if (nowWorker.buildTower(buildGrid) == false) {
                System.out.println("It is not a valid build. please try again");
                System.out.println(nowWorker);
                continue;
            }
            System.out.println("You successfully build a tower on (" + (buildGrid.getRow() + 1) +", " + (buildGrid.getColumn() + 1) + ") and the height now is " + buildGrid.getLayer() );
            nowWorker = null;
            currentPlayer = game.getAnotherPlayer(currentPlayer);
            System.out.println("Now is Player " + currentPlayer + " is playing");
            ifMoved = false;
        }
    }


    private void initialSettingWorker(Game game, Scanner sc) {
        System.out.println("Worker1 please set your workers.");
        for (int i = 0; i < 2; i++) {
            int j = 0;
            while (true) {
                System.out.println("Please set your worker"+(j+1)+" location. like row, column");
                String input = sc.nextLine().trim();
                Grid location = scanGetGrid(input);
                if (location == null) {
                    System.out.println("there are something wrong about your input, please input like \"3, 2 \"" + input);
                    continue;
                } else if (currentPlayer.getWorker().get(j).setWorker(location) == false ) {
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

    private Grid scanGetGrid(String arg) {
        if (arg == null) {
            return null;
        }
        String[] grid = arg.split(",");
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
        return gameNow.getBoard().getGrids()[gridInt[0]][gridInt[1]];
    }

    private int[] locationScanner(String response) {
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
    private boolean move(int worker, int[] gridLocation) {
        if (gridLocation == null) {
            return false;
        }
        Grid[][] grids = gameNow.getBoard().getGrids();
        
        if (worker > 3) {
            return false;
        }
        nowWorker = currentPlayer.getWorker().get(worker);
        int row = gridLocation[0] - 1;
        int column = gridLocation[1] - 1;
        Grid goalGrid= grids[row][column];
        if (currentPlayer.moveWorker(nowWorker, goalGrid) == false) {
            return false;
        }
        return true;
    }
}
