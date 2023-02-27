package edu.cmu.cs214.hw3;

/**
 * main method to call UI to play this game.
 * @author Xuezhen Dai (andrew ID: xuezhend)
 */
public class Main 
{
    public static void main(String[] args) {
        Game game = new Game();
        new UI(game);
    }
}
