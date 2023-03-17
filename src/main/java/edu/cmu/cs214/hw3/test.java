package edu.cmu.cs214.hw3;
public class test {
    public static void main(String[] args) {
        Game game = new Game();
        game.initialization();
        int count = 0;
        //System.out.println(game.getBoard().getGrid(3, 4));
        //System.out.println(game.setWorker(0, 0,game.getCurrentPlayer().getWorker(count%2)));

        System.out.println(game.getCurrentPlayer().getWorker(0));
        game.setWorker(1, 2,game.getCurrentPlayer().getWorker(count%2));
        Worker workernow = game.getCurrentPlayer().getWorker(count%2);
        Grid gridmove = game.getBoard().getGrid(1, 3);

        workernow.setLocation(gridmove);
        System.out.println(game.getBoard().getGrid(0, 0).getCurrentWorker());
        System.out.println(gridmove);
    }
}
