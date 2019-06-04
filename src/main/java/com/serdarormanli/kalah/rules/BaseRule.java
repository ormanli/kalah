package com.serdarormanli.kalah.rules;

import com.deliveredtechnologies.rulebook.RuleState;
import com.deliveredtechnologies.rulebook.annotation.Given;
import com.deliveredtechnologies.rulebook.annotation.Result;
import com.serdarormanli.kalah.model.Game;

/**
 * Base class that defines a rule.
 * All existing rules are subclass of this class.
 * Contains all necessary facts.
 */
abstract class BaseRule {
    @Given("game")
    Game game;

    @Given("indexOfPit")
    int indexOfPit;

    @Given("snapshot")
    boolean snapshot;

    @Result
    RuleOutput ruleOutput;

    boolean isOwnPit(int whichPlayer, int indexOfPit) {
        if (whichPlayer == 1) {
            return indexOfPit < game.getNumberOfPitsPerPlayer();
        } else {
            return indexOfPit > game.getNumberOfPitsPerPlayer();
        }
    }

    int otherPlayersBigPit(int whichPlayer) {
        if (whichPlayer == 2) {
            return game.getNumberOfPitsPerPlayer();
        } else {
            return game.getNumberOfPitsPerPlayer() * 2 + 1;
        }
    }

    int playersBigPit(int whichPlayer) {
        if (whichPlayer == 1) {
            return game.getNumberOfPitsPerPlayer();
        } else {
            return game.getNumberOfPitsPerPlayer() * 2 + 1;
        }
    }

    public abstract boolean when();

    public abstract RuleState then();
}
