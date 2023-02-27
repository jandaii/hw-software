package edu.cmu.cs214.hw3;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class UITest {

    // @Before
    // public void setup() {
    //     Game game = new Game();
    //     UI ui = new UI(game);
    // }

    @Test
    public void locationScTest() {
        int[] intOupt = {2,3};
        assertArrayEquals(UI.locationScanner("2,3"), intOupt);
    }


}
