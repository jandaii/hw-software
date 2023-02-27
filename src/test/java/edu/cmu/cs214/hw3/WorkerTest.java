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
        nWorker = new Worker();
    }
    @Test
    public void setWorker()
    {
        assertTrue(nWorker.setWorker(setGrid));
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



}
