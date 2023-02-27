package edu.cmu.cs214.hw3;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class GameTest {
    Game gamenow;

    @Before 
    public void beforeset() {
        gamenow = new Game();
    }
    @Test
    public void initializationTest() {
        gamenow.initialization();
        assertEquals(gamenow.getCurrentPlayer(), gamenow.getPlayer1());
    }
    @Test
    public void getAnotherPlayerTest() {
        gamenow.initialization();
        Player current = gamenow.getCurrentPlayer();
        assertEquals(gamenow.getAnotherPlayer(current), gamenow.getPlayer2());
    }

    @Test
    public void changePlayerTest() {
        gamenow.initialization();
        gamenow.changeCurrentPlayer();
        Player current = gamenow.getCurrentPlayer();
        assertEquals(current, gamenow.getPlayer2());
    }

    @Test
    public void moveWorker() {
        gamenow.initialization();
        Worker workerNow = gamenow.getCurrentPlayer().getWorker(0);
        workerNow.setWorker(gamenow.getBoard().getGrid(2, 3));
        int[] gridLocation = {3,4};
        assertFalse( gamenow.move(workerNow, gridLocation));
        assertEquals(workerNow.getLocation(), gamenow.getBoard().getGrid(2, 3));
    }

    @Test
    public void moveWorkerTrue() {
        gamenow.initialization();
        Worker workerNow = gamenow.getCurrentPlayer().getWorker(0);
        workerNow.setWorker(gamenow.getBoard().getGrid(2, 3));
        int[] gridLocation = {4,4};
        assertTrue( gamenow.move(workerNow, gridLocation));
        assertEquals(workerNow.getLocation(), gamenow.getBoard().getGrid(3, 3));

    }
}
