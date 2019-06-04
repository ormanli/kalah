package com.serdarormanli.kalah.rules;

import com.deliveredtechnologies.rulebook.RuleState;
import com.deliveredtechnologies.rulebook.annotation.Rule;
import com.deliveredtechnologies.rulebook.annotation.Then;
import com.deliveredtechnologies.rulebook.annotation.When;

/**
 * Checks if a given pit has stones.
 */
@Rule(order = 4)
public class PitEmptyRule extends BaseRule {

    @Override
    @When
    public boolean when() {
        return game.getPit(indexOfPit) == 0;
    }

    @Override
    @Then
    public RuleState then() {
        ruleOutput = RuleOutput.EMPTY_PIT;

        return RuleState.BREAK;
    }
}
