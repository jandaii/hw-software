package edu.cmu.cs214.hw3;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
public class WorkerTest {

    private Worker nWorker;
    private Grid setGrid;
    @Before

    public void setNewWorker() {
        setGrid = new Grid(2, 3);
        nWorker = new Worker(0);
    }
    @Test
    public void setWorker()
    {
        assertTrue(nWorker.setWorker(setGrid));
    }

    @Test
    public void setWorkerFalse()
    {
        Grid newGrid = new Grid(5, 4);
        assertFalse(nWorker.setWorker(newGrid));
    }

    @Test
    public void getLocation()
    {
        nWorker.setWorker(setGrid);
        assertEquals(nWorker.getLocation(), setGrid);
    }

    @Test
    public void setWorkerOccupaied() {
        setGrid.setOccupied(true);
        assertFalse(nWorker.setWorker(setGrid));
    }

    @Test
    public void setWorkerInvalid() {
        Grid newGrid = new Grid(5, 3);
        assertFalse(nWorker.setWorker(newGrid));
    }

    @Test
    public void setLocationTrue() {
        Grid newGrid = new Grid(3,2);
        nWorker.setWorker(setGrid);
        assertTrue(nWorker.setLocation(newGrid));
        assertFalse(setGrid.getOccupied());
        assertTrue(newGrid.getOccupied());
    }

    @Test
    public void setLocationinValid(){
        Grid newGrid = new Grid(5,4);
        nWorker.setWorker(setGrid);
        assertFalse(nWorker.setLocation(newGrid));
        assertTrue(setGrid.getOccupied());
        assertFalse(newGrid.getOccupied());
    }

    @Test
    public void setLocationcheckValid() {
        Grid newGrid = new Grid(5,4);
        newGrid.setOccupied(true);
        nWorker.setWorker(setGrid);
        assertFalse(nWorker.setLocation(newGrid));
        assertTrue(setGrid.getOccupied());
    }

    @Test
    public void setLocationlayerPro() {
        Grid newGrid = new Grid(5,4);
        newGrid.addLayer();
        newGrid.addLayer();
        newGrid.addLayer();
        nWorker.setWorker(setGrid);
        assertFalse(nWorker.setLocation(newGrid));
        assertTrue(setGrid.getOccupied());
    }

    @Test
    public void setLocationtooFar1() {
        Grid newGrid = new Grid(0,0);
        nWorker.setWorker(setGrid);
        assertFalse(nWorker.setLocation(newGrid));
        assertTrue(setGrid.getOccupied());
    }

    @Test
    public void setLocationtooFar2() {
        Grid newGrid = new Grid(0,3);
        nWorker.setWorker(setGrid);
        assertFalse(nWorker.setLocation(newGrid));
        assertTrue(setGrid.getOccupied());
    }

    @Test
    public void setLocationtooFar3() {
        Grid newGrid = new Grid(3, 0);
        nWorker.setWorker(setGrid);
        assertFalse(nWorker.setLocation(newGrid));
        assertTrue(setGrid.getOccupied());
    }

    @Test
    public void setLocationlayer() {
        Grid newGrid = new Grid(3, 2);
        newGrid.addLayer();
        newGrid.addLayer();
        nWorker.setWorker(setGrid);
        assertFalse(nWorker.setLocation(newGrid));
        assertTrue(setGrid.getOccupied());
    }

    @Test
    public void buildTowerTestTrue() {
        nWorker.setWorker(setGrid);
        assertTrue(nWorker.buildTower(new Grid(3, 3)));
    }

    @Test
    public void buildTowerTestOccupy() {
        nWorker.setWorker(setGrid);
        Grid newGrid = new Grid(3, 3);
        newGrid.setOccupied(true);
        assertFalse(nWorker.buildTower(newGrid));
    }

    @Test
    public void buildTowerTestlayer(){
        nWorker.setWorker(setGrid);
        Grid newGrid = new Grid(3, 3);
        newGrid.addLayer();
        newGrid.addLayer();
        newGrid.addLayer();
        newGrid.addLayer();

        assertFalse(nWorker.buildTower(newGrid));
    }

    @Test
    public void buildTowerTestRow(){
        nWorker.setWorker(setGrid);
        Grid newGrid = new Grid(0, 3);
        assertFalse(nWorker.buildTower(newGrid));
    }

    @Test
    public void buildTowerTestColumn(){
        nWorker.setWorker(setGrid);
        Grid newGrid = new Grid(0, 3);
        assertFalse(nWorker.buildTower(newGrid));
    }

    @Test
    public void testIfwin() {
        nWorker.setWorker(setGrid);
        Grid grid1 = new Grid(3, 3);
        grid1.addLayer();
        nWorker.setLocation(grid1);
        Grid grid2 = new Grid (3,4);
        grid2.addLayer();
        grid2.addLayer();
        nWorker.setLocation(grid2);
        grid1.addLayer();
        grid1.addLayer();
        nWorker.setLocation(grid1);
        //assertEquals(nWorker.layer, grid1);
        assertTrue(nWorker.getIfWin());
    }

    @Test
    public void testIfwinFalse() {
        nWorker.setWorker(setGrid);
        Grid grid1 = new Grid(3, 3);
        grid1.addLayer();
        nWorker.setLocation(grid1);
        assertFalse(nWorker.getIfWin());
    }

    @Test
    public void testOccupiedMove() {
        nWorker.setWorker(setGrid);
        Grid grid1 = new Grid(3, 3);
        grid1.addLayer();
        grid1.setOccupied(true);
        assertFalse(nWorker.setLocation(grid1));
    }

    @Test
    public void testReturnStr() {
        Board board = new Board();
        nWorker.setWorker(setGrid);
        assertEquals(nWorker.getValidMoveGrids(board.getGrids()).trim(), "(2, 3) (2, 4) (2, 5) (3, 3) (3, 4) (3, 5) (4, 3) (4, 4) (4, 5)".trim());
    }

    @Test
    public void testMovable() {
        Board board = new Board();
        nWorker.setWorker(setGrid);
        assertTrue(nWorker.isMovable(board.getGrids()));
        
    }
    @Test
    public void testReturnStr1() {
        Board board = new Board();
        nWorker.setWorker(board.getGrid(1, 0));
        assertEquals(nWorker.getValidMoveGrids(board.getGrids()).trim(), "(1, 1) (1, 2) (2, 2) (3, 1) (3, 2)".trim());
    }


    @Test
    public void testReturnStr2() {
        Board board = new Board();
        nWorker.setWorker(board.getGrid(0, 0));
        Grid girdTO = board.getGrid(1, 1);
        girdTO.addLayer();
        girdTO.addLayer();
        assertEquals(nWorker.getValidMoveGrids(board.getGrids()).trim(), "(1, 2) (2, 1)".trim());
    }

    @Test
    public void testReturnStr3() {
        Board board = new Board();
        nWorker.setWorker(board.getGrid(0, 1));
        assertEquals(nWorker.getValidMoveGrids(board.getGrids()).trim(), "(1, 1) (1, 3) (2, 1) (2, 2) (2, 3)".trim());
    }

    @Test
    public void testReturnStr4() {
        Board board = new Board();
        nWorker.setWorker(board.getGrid(0, 1));
        assertEquals(nWorker.getValidMoveGrids(board.getGrids()).trim(), "(1, 1) (1, 3) (2, 1) (2, 2) (2, 3)".trim());
    }

    @Test
    public void testReturnStr5() {
        Board board = new Board();
        nWorker.setWorker(board.getGrid(4, 3));
        assertEquals(nWorker.getValidMoveGrids(board.getGrids()).trim(), "(4, 3) (4, 4) (4, 5) (5, 3) (5, 5)".trim());
    }

    @Test
    public void testReturnStr6() {
        Board board = new Board();
        nWorker.setWorker(board.getGrid(3, 4));
        assertEquals(nWorker.getValidMoveGrids(board.getGrids()).trim(), "(3, 4) (3, 5) (4, 4) (5, 4) (5, 5)".trim());
    }

    @Test
    public void testReturnStr7() {
        Board board = new Board();
        board.getGrid(3, 3).setOccupied(true);
        nWorker.setWorker(board.getGrid(3, 4));
        assertEquals(nWorker.getValidMoveGrids(board.getGrids()).trim(), "(3, 4) (3, 5) (5, 4) (5, 5)".trim());
    }

    @Test
    public void testReturnStr8() {
        Board board = new Board();
        board.getGrid(3, 3).addLayer();
        board.getGrid(3, 3).addLayer();
        board.getGrid(3, 3).addLayer();

        nWorker.setWorker(board.getGrid(3, 4));
        assertEquals(nWorker.getValidMoveGrids(board.getGrids()).trim(), "(3, 4) (3, 5) (5, 4) (5, 5)".trim());
    }

}
