package com.serdarormanli.kalah.rules;

import com.deliveredtechnologies.rulebook.RuleState;
import com.deliveredtechnologies.rulebook.annotation.Rule;
import com.deliveredtechnologies.rulebook.annotation.Then;
import com.deliveredtechnologies.rulebook.annotation.When;

@Rule(order = 9)
public class SwitchPlayerRule extends BaseRule {

    @Override
    @When
    public boolean when() {
        return ruleOutput == RuleOutput.CHANGE_PLAYER;
    }

    @Override
    @Then
    public RuleState then() {
        game.switchPlayer();

        return RuleState.NEXT;
    }
}
