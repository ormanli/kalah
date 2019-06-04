package com.serdarormanli.kalah.rules;

import com.deliveredtechnologies.rulebook.RuleState;
import com.deliveredtechnologies.rulebook.annotation.Rule;
import com.deliveredtechnologies.rulebook.annotation.Then;
import com.deliveredtechnologies.rulebook.annotation.When;

/**
 * Checks if given pit index is valid.
 */
@Rule(order = 3)
public class PitIndexOutOfBoundsRule extends BaseRule {

    @Override
    @When
    public boolean when() {
        int indexOfPlayers1BigPit = game.getNumberOfPitsPerPlayer();
        int indexOfPlayers2BigPit = game.getNumberOfPitsPerPlayer() + indexOfPlayers1BigPit + 1;

        return indexOfPit < 0 ||
                indexOfPlayers1BigPit == indexOfPit ||
                indexOfPlayers2BigPit == indexOfPit ||
                indexOfPit > indexOfPlayers2BigPit;
    }

    @Override
    @Then
    public RuleState then() {
        ruleOutput = RuleOutput.INDEX_OUT_OF_BOUNDS;

        return RuleState.BREAK;
    }
}
