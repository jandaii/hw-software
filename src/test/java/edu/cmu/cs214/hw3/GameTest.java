package edu.cmu.cs214.hw3;

import static org.junit.Assert.assertEquals;

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
}
