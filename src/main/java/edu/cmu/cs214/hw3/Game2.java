package edu.cmu.cs214.hw3;

import java.util.ArrayList;
import java.util.List;

enum Player2 {
    PLAYER0(0), PLAYER1(1);

    final int value;

    Player2(int value) {
        this.value = value;
    }
}

public class Game2 {
    private final Board2 board;
    private final Player2 player;
    private final List<Game2> history;

    public Game2() {
        this(new Board2(), Player2.PLAYER0);
    }

    public Game2(Board2 board, Player2 nextPlayer) {
        this(board, nextPlayer, List.of());
    }

    public Game2(Board2 board, Player2 nextPlayer, List<Game2> history) {
        this.board = board;
        this.player = nextPlayer;
        this.history = history;
    }

    public Board2 getBoard() {
        return this.board;
    }

    public Player2 getPlayer() {
        return this.player;
    }

    public Game2 play(int x, int y) {
        if (this.board.getCell(x, y) != null)
            return this;
        if (this.getWinner() != null)
            return this;
        List<Game2> newHistory = new ArrayList<>(this.history);
        newHistory.add(this);
        //change the player, but when set the workers, they need to set 2 in one time
        Player2 nextPlayer = this.player == Player2.PLAYER0 ? Player2.PLAYER1 : Player2.PLAYER0;
        return new Game2(this.board.updateCell(x, y, this.player), nextPlayer, newHistory);
    }

    public Game2 setWorker(int x, int y) {
        if (this.board.getCell(x, y) != null)
            return this;
        if (this.getWinner() != null)
            return this;
        List<Game2> newHistory = new ArrayList<>(this.history);
        newHistory.add(this);
        //change the player, but when set the workers, they need to set 2 in one time
        Player2 nextPlayer = this.player == Player2.PLAYER0 ? Player2.PLAYER1 : Player2.PLAYER0;
        return new Game2(this.board.updateCell(x, y, this.player), nextPlayer, newHistory);
    }

    public Player2 getWinner() {
        for (int row = 0; row < 5; row++)
            if (board.getCell(row, 0) != null && board.getCell(row, 0) == board.getCell(row, 1)
                    && board.getCell(row, 1) == board.getCell(row, 2))
                return board.getCell(row, 0);
        for (int col = 0; col < 5; col++)
            if (board.getCell(0, col) != null && board.getCell(0, col) == board.getCell(1, col)
                    && board.getCell(0, col) == board.getCell(2, col))
                return board.getCell(0, col);
        if (board.getCell(1, 1) != null && board.getCell(0, 0) == board.getCell(1, 1)
                && board.getCell(1, 1) == board.getCell(2, 2))
            return board.getCell(1, 1);
        if (board.getCell(1, 1) != null && board.getCell(0, 2) == board.getCell(1, 1)
                && board.getCell(1, 1) == board.getCell(2, 0))
            return board.getCell(1, 1);
        return null;
    }
}
