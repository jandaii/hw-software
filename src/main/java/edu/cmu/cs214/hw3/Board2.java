package edu.cmu.cs214.hw3;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
//this is a board with players note on it.
public class Board2 {
    private final Player2[] cells;

    public Board2() {
        this(IntStream.range(0, 25).mapToObj(i -> null)
                .collect(Collectors.toList()).toArray(new Player2[0]));
    }

    public Board2(Player2[] cells) {
        this.cells = cells;
    }

    public Player2 getCell(int x, int y) {
        return this.cells[y * 5 + x];
    }

    public Board2 updateCell(int x, int y, Player2 player) {
        Player2[] newCells = Arrays.copyOf(this.cells, this.cells.length);
        newCells[y * 5 + x] = player;
        return new Board2(newCells);
    }
}
