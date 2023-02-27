package edu.cmu.cs214.hw3;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class GridTest {
    private Grid testGrid;

    @Before
    public void Layertest() {
        testGrid = new Grid(3, 2);
    }

    @Test
    public void addLayerTest() {
        testGrid.addLayer();
        assertEquals(testGrid.getLayer(), 1);
    }

    @Test
    public void occupyTest() {
        testGrid.setOccupied(true);
        assertTrue(testGrid.getOccupied());
    }
}
