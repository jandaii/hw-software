package edu.cmu.cs214.hw3;

import java.io.IOException;
import java.util.Map;
import fi.iki.elonen.NanoHTTPD;

public class App extends NanoHTTPD {

    //int count = 0;
    Worker chosen;
    boolean ifRoundWin;
    public static void main(String[] args) {
        try {
            new App();
        } catch (IOException ioe) {
            System.err.println("Couldn't start server:\n" + ioe);
        }
    }

    private Game game;

    /**
     * Start the server at :8080 port.
     * @throws IOException
     */
    public App() throws IOException {
        super(8080);

        this.game = new Game();
        start(NanoHTTPD.SOCKET_READ_TIMEOUT, false);
        System.out.println("\nRunning!\n");
    }


    @Override
    public Response serve(IHTTPSession session) {
        String uri = session.getUri();
        
        Map<String, String> params = session.getParms();
        if (uri.equals("/newgame")) {
            this.game = new Game();
            game.initialization();
        } else if (uri.equals("/set")) {
            // e.g., /play?x=1&y=1
            // this game set the worker firstly
            int count = Integer.parseInt(params.get("count"));
            if (count == 3) {
                game.changeCurrentPlayer();
            }
            //count++;
            game.setWorker(Integer.parseInt(params.get("x")), Integer.parseInt(params.get("y")), this.game.getCurrentPlayer().getWorker(count%2));
            if (count == 4) {
                game.changeCurrentPlayer();
                game.changeSelectMode();
            }

            //this.game = this.game.play(Integer.parseInt(params.get("x")), Integer.parseInt(params.get("y")));
        } else if (uri.equals("/select")) {
            int x = Integer.parseInt(params.get("x"));
            int y = Integer.parseInt(params.get("y"));
            Grid gridNow = this.game.getBoard().getGrid(x, y);
            chosen = gridNow.getCurrentWorker();
            game.changeMoveGrid(chosen);
        } else if (uri.equals("/move")) {
            if (game.getCurrentPlayer().ifMovable(game.getBoard()) == false) {
                
            }
            int x = Integer.parseInt(params.get("x"));
            int y = Integer.parseInt(params.get("y"));
            Grid gridmove = this.game.getBoard().getGrid(x, y);
            chosen.setLocation(gridmove);
            if (chosen.getIfWin() == false) {
                gridmove.setPlayerInfo(game.getCurrentPlayer().getPlayerId()); 
                gridmove.setMovable(false);
                game.changeBuild(chosen); 
            } else {
                game.changeAvailable(); 
            }
            
              
        } else if (uri.equals("/build")) {
            int x = Integer.parseInt(params.get("x"));
            int y = Integer.parseInt(params.get("y"));
            Grid gridbuild = this.game.getBoard().getGrid(x, y);
            chosen.buildTower(gridbuild);
            game.changeCurrentPlayer();
            game.changeSelectMode();
            game.changeAllMovable();

        } 
        Board boardNow = game.getBoard();
        // Extract the view-specific data from the game and apply it to the template.
        //GameState gameplay = GameState.forGame(this.game);
        //System.out.println(boardNow.toString());
        return newFixedLengthResponse(boardNow.toString());
    }

    public static class Test {
        public String getText() {
            return "Hello World!";
        }
    }
}