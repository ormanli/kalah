package com.serdarormanli.kalah.rules;

import com.deliveredtechnologies.rulebook.RuleState;
import com.deliveredtechnologies.rulebook.annotation.Then;
import com.deliveredtechnologies.rulebook.annotation.When;

public abstract class GameFinishedRule extends BaseRule {

    @Override
    @When
    public boolean when() {
        int remainingStonesOfPlayer1 = 0;
        int remainingStonesOfPlayer2 = 0;

        for (int i = startingIndex(1); i < endingIndex(1); i++) {
            remainingStonesOfPlayer1 += game.getPit(i);
        }

        for (int i = startingIndex(2); i < endingIndex(2); i++) {
            remainingStonesOfPlayer2 += game.getPit(i);
        }

        return remainingStonesOfPlayer1 == 0 || remainingStonesOfPlayer2 == 0;
    }

    private int startingIndex(int whichPlayer) {
        return (whichPlayer - 1) * (game.getNumberOfPitsPerPlayer() + 1);
    }

    private int endingIndex(int whichPlayer) {
        return startingIndex(whichPlayer) + game.getNumberOfPitsPerPlayer();
    }

    @Override
    @Then
    public RuleState then() {
        ruleOutput = RuleOutput.GAME_FINISHED;

        return RuleState.BREAK;
    }
}
