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
        nworker = new Worker(0);
        nworker.setWorker(setGrid);
        player = new Player(1);
        player.addWorker(nworker);
    }

    @Test
    public void testAddWorker() {
        
        assertEquals(player.getWorkers().get(0), nworker);
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

    @Test
    public void getWinStatusTest() {
        nworker.setWorker(setGrid);
        Grid grid1 = new Grid(3, 3);
        grid1.addLayer();
        nworker.setLocation(grid1);
        Grid grid2 = new Grid (3,4);
        grid2.addLayer();
        grid2.addLayer();
        grid1.addLayer();
        grid1.addLayer();
        nworker.setLocation(grid2);
        nworker.setLocation(grid1);
        assertTrue(player.getWinStatus());
    }

    @Test
    public void getWinStatusfalseTest() {

        assertFalse(player.getWinStatus());
    }

    @Test
    public void ifMovableTrueTest() {
        Board board = new Board();
        assertTrue(player.ifMovable(board));
    }

    @Test
    public void ifMovableFalseTest() {
        Board board = new Board();
        nworker.setLocation(board.getGrid(2, 2));
        nworker.setLocation(board.getGrid(1, 1));

        nworker.setLocation(board.getGrid(0, 0));
        board.getGrid(0, 1).setOccupied(true);
        board.getGrid(1, 0).setOccupied(true);
        board.getGrid(1, 1).setOccupied(true);
        //assertEquals(nworker.getLocation(), null);
       // assertEquals(nworker.getValidMoveGrids(board.getGrids()), null);
        assertFalse(player.ifMovable(board));
    }
}
