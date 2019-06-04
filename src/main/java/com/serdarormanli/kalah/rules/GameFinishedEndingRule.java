package com.serdarormanli.kalah.rules;

import com.deliveredtechnologies.rulebook.annotation.Rule;

/**
 * Checks if a game is finished after all rules executed.
 * Because a game might be finished.
 */
@Rule(order = 8)
public class GameFinishedEndingRule extends GameFinishedRule {
}
