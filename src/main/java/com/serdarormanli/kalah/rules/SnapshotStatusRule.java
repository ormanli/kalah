package com.serdarormanli.kalah.rules;

import com.deliveredtechnologies.rulebook.RuleState;
import com.deliveredtechnologies.rulebook.annotation.Rule;
import com.deliveredtechnologies.rulebook.annotation.Then;
import com.deliveredtechnologies.rulebook.annotation.When;

/**
 * Prevents other rules from execution if rule execution was to get snapshot.
 */
@Rule(order = 2)
public class SnapshotStatusRule extends BaseRule {

    @Override
    @When
    public boolean when() {
        return snapshot;
    }

    @Override
    @Then
    public RuleState then() {
        ruleOutput = RuleOutput.GAME_CONTINUES;

        return RuleState.BREAK;
    }
}
