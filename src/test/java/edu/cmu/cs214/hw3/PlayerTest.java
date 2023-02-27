package edu.cmu.cs214.hw3;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class PlayerTest {
    private Player player;
    private Worker nworker;
    private Grid setGrid;

    @Before
    public void setNewPlayer() {
        setGrid = new Grid(2, 3);
        nworker = new Worker();
        nworker.setWorker(setGrid);
        player = new Player(1);
        player.addWorker(nworker);
    }

    @Test
    public void testAddWorker() {
        
        assertEquals(player.getWorker().get(0), nworker);
    }

    @Test
    public void moveWorker() {
        assertTrue(player.moveWorker(nworker, new Grid(3, 3)));
    }

    @Test
    public void moveWorkerFalse() {
        assertFalse(player.moveWorker(nworker, new Grid(0, 2)));
    }

    @Test
    public void buildTowerTrue() {
        assertTrue(player.buildTower(nworker, new Grid(3, 3)));
    }

    @Test
    public void buildTowerFalse() {
        assertFalse(player.buildTower(nworker, new Grid(0, 2)));
    }
}
