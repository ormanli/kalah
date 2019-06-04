package com.serdarormanli.kalah.rules;

import com.deliveredtechnologies.rulebook.RuleState;
import com.deliveredtechnologies.rulebook.annotation.Rule;
import com.deliveredtechnologies.rulebook.annotation.Then;
import com.deliveredtechnologies.rulebook.annotation.When;

/**
 * Checks if a player is trying to move own stones.
 */
@Rule(order = 5)
public class OwnPitRule extends BaseRule {

    @Override
    @When
    public boolean when() {
        return !isOwnPit(game.getCurrentPlayer(), indexOfPit);
    }

    @Override
    @Then
    public RuleState then() {
        ruleOutput = RuleOutput.OTHER_PLAYERS_PIT;

        return RuleState.BREAK;
    }
}
