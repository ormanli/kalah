package com.serdarormanli.kalah.rules;

import com.deliveredtechnologies.rulebook.RuleState;
import com.deliveredtechnologies.rulebook.annotation.Rule;
import com.deliveredtechnologies.rulebook.annotation.Then;
import com.deliveredtechnologies.rulebook.annotation.When;

/**
 * Rule of if players last stone lands on own previously empty pit.
 * Captures stones of opponent and puts to own big pit.
 */
@Rule(order = 7)
public class CaptureOpponentRule extends BaseRule {

    @Override
    @When
    public boolean when() {
        return ruleOutput == RuleOutput.PREVIOUSLY_EMPTY_PIT;
    }

    @Override
    @Then
    public RuleState then() {
        var otherPlayersPit = (game.getNumberOfPitsPerPlayer() * 2) - indexOfPit;

        var amountOfCapturedStones = game.getPit(otherPlayersPit);
        game.setPit(otherPlayersPit, 0);

        game.setPit(playersBigPit(game.getCurrentPlayer()), game.getPit(playersBigPit(game.getCurrentPlayer())) + amountOfCapturedStones);

        ruleOutput = RuleOutput.CHANGE_PLAYER;

        return RuleState.NEXT;
    }
}
