package com.serdarormanli.kalah.rules;

import com.deliveredtechnologies.rulebook.annotation.Rule;

/**
 * Checks if a game is finished before executing other rules.
 */
@Rule(order = 1)
public class GameFinishedStartingRule extends GameFinishedRule {
}
