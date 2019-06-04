package com.serdarormanli.kalah.model;

/**
 * Player 2
 * <----------------------+
 * <p>
 * +----------------------+
 * |     12 11 10 9 8 7   |
 * | 13               +---+
 * |    +-------------+   |
 * +----+               6 |
 * |      0 1 2 3 4 5     |
 * +----------------------+
 * <p>
 * +---------------------->
 * Player 1
 * <p>
 * Stores board and current player.
 */
public class Game {
    private final int[] pits;
    private final int numberOfPitsPerPlayer;
    private int currentPlayer;

    public Game(int numberOfPitsPerPlayer, int numberOfStonesPerPit) {
        this.numberOfPitsPerPlayer = numberOfPitsPerPlayer;
        pits = new int[(this.numberOfPitsPerPlayer + 1) * 2];

        for (var i = 0; i < getTotalNumberOfPits() - 1; i++) {
            if (i != numberOfPitsPerPlayer) {
                setPit(i, numberOfStonesPerPit);
            }
        }

        currentPlayer = 1;
    }

    public int getNumberOfPitsPerPlayer() {
        return numberOfPitsPerPlayer;
    }

    public int getPit(int indexOfPit) {
        return pits[indexOfPit];
    }

    public void setPit(int indexOfPit, int numberOfStones) {
        pits[indexOfPit] = numberOfStones;
    }

    public int getTotalNumberOfPits() {
        return pits.length;
    }

    public int[] getPits() {
        return pits;
    }

    public int getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     * Switches current player to other player.
     */
    public void switchPlayer() {
        currentPlayer = 3 - currentPlayer;
    }
}
