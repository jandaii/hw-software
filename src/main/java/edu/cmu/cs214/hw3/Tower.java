package edu.cmu.cs214.hw3;

public class Tower {
    private int layer;
    private static final int MAX_LAYER = 3;
    private boolean isDome;

    /**
     * return the layer of the tower in this grid
     * @return
     */
    public int getLayer() {
        return layer;
    }

    /**
     * constructor.
     * @param height
     */
    public Tower(int height) {
        layer = height;
        isDome = false;
    }

    public void buildBlock() {
        if (IfbuildBlock() == true) {
            layer ++;
        }
        
    }

    private boolean ifbuildTower() {
        return !isDome;
    }

    private boolean IfbuildBlock() {
        if (!isDome && layer < MAX_LAYER) {
            return true;
        }
        return false;
    }

    public void buildDome() {
        if(ifbuildTower() == true) {
            layer ++;
            isDome = true;
        }
    }

    public boolean isDome() {
        return isDome;
    }
    
}
