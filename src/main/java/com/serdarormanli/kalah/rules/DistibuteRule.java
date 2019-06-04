package com.serdarormanli.kalah.rules;

import com.deliveredtechnologies.rulebook.RuleState;
import com.deliveredtechnologies.rulebook.annotation.Rule;
import com.deliveredtechnologies.rulebook.annotation.Then;
import com.deliveredtechnologies.rulebook.annotation.When;

import static java.lang.Math.floorMod;

/**
 * Main rule responsible for distributing stones.
 * Returns what to do according to last distributed stones pit.
 * Always executed if precessor rules didnt break chain.
 */
@Rule(order = 6)
public class DistibuteRule extends BaseRule {

    @Override
    @When
    public boolean when() {
        return true;
    }

    @Override
    @Then
    public RuleState then() {
        int numberOfStonesInPit = game.getPit(indexOfPit);
        game.setPit(indexOfPit, 0);
        indexOfPit++;

        distributeStones(numberOfStonesInPit);

        if (moveBackward() == playersBigPit(game.getCurrentPlayer())) {
            ruleOutput = RuleOutput.ANOTHER_TURN;

            return RuleState.NEXT;
        } else if (game.getPit(moveBackward()) == 1 && isOwnPit(game.getCurrentPlayer(), moveBackward())) {
            ruleOutput = RuleOutput.PREVIOUSLY_EMPTY_PIT;

            return RuleState.NEXT;
        } else {
            ruleOutput = RuleOutput.CHANGE_PLAYER;

            return RuleState.NEXT;
        }
    }

    /**
     * Distributes stones. Skips opponents big pit.
     *
     * @param numberOfStonesInPit
     */
    private void distributeStones(int numberOfStonesInPit) {
        do {
            if (indexOfPit != otherPlayersBigPit(game.getCurrentPlayer())) {
                game.setPit(indexOfPit, game.getPit(indexOfPit) + 1);
                numberOfStonesInPit--;
            }

            indexOfPit = moveForward();
        } while (numberOfStonesInPit > 0);
    }

    private int moveBackward() {
        return floorMod(indexOfPit - 1, game.getTotalNumberOfPits());
    }

    private int moveForward() {
        return (indexOfPit + 1) % game.getTotalNumberOfPits();
    }
}
