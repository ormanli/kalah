package com.serdarormanli.kalah.model;

import com.serdarormanli.kalah.rules.RuleOutput;

/**
 * Represents latest snaphot of round.
 */
public class RoundSnapshot {
    private final String gameId;
    private final int[] pits;
    private final int currentPlayer;
    private final RuleOutput ruleOutput;
    private final int scoreOfPlayer1;
    private final int scoreOfPlayer2;

    public RoundSnapshot(String gameId,
                         int[] pits,
                         int currentPlayer,
                         RuleOutput ruleOutput,
                         int scoreOfPlayer1,
                         int scoreOfPlayer2) {
        this.gameId = gameId;
        this.pits = pits;
        this.currentPlayer = currentPlayer;
        this.ruleOutput = ruleOutput;
        this.scoreOfPlayer1 = scoreOfPlayer1;
        this.scoreOfPlayer2 = scoreOfPlayer2;
    }

    public String getGameId() {
        return gameId;
    }

    public int[] getPits() {
        return pits;
    }

    public int getCurrentPlayer() {
        return currentPlayer;
    }

    public RuleOutput getRuleOutput() {
        return ruleOutput;
    }

    public int getScoreOfPlayer1() {
        return scoreOfPlayer1;
    }

    public int getScoreOfPlayer2() {
        return scoreOfPlayer2;
    }
}
