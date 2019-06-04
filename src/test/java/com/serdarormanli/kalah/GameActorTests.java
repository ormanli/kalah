package com.serdarormanli.kalah;

import com.deliveredtechnologies.rulebook.model.runner.RuleBookRunner;
import com.serdarormanli.kalah.actor.GameActor;
import com.serdarormanli.kalah.model.Message;
import com.serdarormanli.kalah.rules.RuleOutput;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class GameActorTests {

    private static RuleBookRunner ruleBook;

    @BeforeAll
    public static void beforeClass() {
        ruleBook = new RuleBookRunner("com.serdarormanli.kalah.rules");
    }

    @Test
    public void createGame() throws Exception {
        var actor = new GameActor("A", ruleBook, 6, 6).spawn();

        var snapshot = actor.call(Message.snapshot());

        assertThat(snapshot.getCurrentPlayer()).isEqualTo(1);
        assertThat(snapshot.getRuleOutput()).isEqualByComparingTo(RuleOutput.GAME_CONTINUES);
    }

    @Test
    public void playBySamePlayerTwice() throws Exception {
        var actor = new GameActor("A", ruleBook, 6, 6).spawn();

        var snapshot = actor.call(Message.play(0));
        assertThat(snapshot.getRuleOutput()).isEqualByComparingTo(RuleOutput.ANOTHER_TURN);
        assertThat(snapshot.getCurrentPlayer()).isEqualTo(1);

        snapshot = actor.call(Message.play(1));

        assertThat(snapshot.getRuleOutput()).isEqualByComparingTo(RuleOutput.CHANGE_PLAYER);
        assertThat(snapshot.getCurrentPlayer()).isEqualTo(2);
    }

    @Test
    public void switchPlayers() throws Exception {
        var actor = new GameActor("A", ruleBook, 6, 6).spawn();

        var snapshot = actor.call(Message.play(2));
        assertThat(snapshot.getCurrentPlayer()).isEqualTo(2);

        snapshot = actor.call(Message.play(8));
        assertThat(snapshot.getCurrentPlayer()).isEqualTo(1);
    }

    @Test
    public void testRandomGame() throws Exception {
        var actor = new GameActor("A", ruleBook, 6, 4).spawn();

        var play = actor.call(Message.play(0));
        assertThat(play.getCurrentPlayer()).isEqualTo(2);

        play = actor.call(Message.play(11));
        assertThat(play.getCurrentPlayer()).isEqualTo(1);

        play = actor.call(Message.play(1));
        assertThat(play.getCurrentPlayer()).isEqualTo(2);

        play = actor.call(Message.play(10));
        assertThat(play.getCurrentPlayer()).isEqualTo(1);

        play = actor.call(Message.play(0));
        assertThat(play.getCurrentPlayer()).isEqualTo(2);

        play = actor.call(Message.play(7));
        assertThat(play.getCurrentPlayer()).isEqualTo(1);

        play = actor.call(Message.play(2));
        assertThat(play.getCurrentPlayer()).isEqualTo(2);

        play = actor.call(Message.play(9));
        assertThat(play.getCurrentPlayer()).isEqualTo(1);

        play = actor.call(Message.play(3));
        assertThat(play.getCurrentPlayer()).isEqualTo(2);

        play = actor.call(Message.play(11));
        assertThat(play.getCurrentPlayer()).isEqualTo(1);

        play = actor.call(Message.play(4));
        assertThat(play.getCurrentPlayer()).isEqualTo(2);

        play = actor.call(Message.play(8));
        assertThat(play.getCurrentPlayer()).isEqualTo(1);

        play = actor.call(Message.play(5));
        assertThat(play.getCurrentPlayer()).isEqualTo(2);

        play = actor.call(Message.play(9));
        assertThat(play.getCurrentPlayer()).isEqualTo(2);

        play = actor.call(Message.play(8));
        assertThat(play.getCurrentPlayer()).isEqualTo(1);

        play = actor.call(Message.play(0));
        assertThat(play.getCurrentPlayer()).isEqualTo(2);

        play = actor.call(Message.play(11));
        assertThat(play.getCurrentPlayer()).isEqualTo(1);

        play = actor.call(Message.play(1));
        assertThat(play.getCurrentPlayer()).isEqualTo(1);

        play = actor.call(Message.play(4));
        assertThat(play.getCurrentPlayer()).isEqualTo(1);

        play = actor.call(Message.play(2));
        assertThat(play.getCurrentPlayer()).isEqualTo(2);

        play = actor.call(Message.play(9));
        assertThat(play.getCurrentPlayer()).isEqualTo(1);

        play = actor.call(Message.play(3));
        assertThat(play.getCurrentPlayer()).isEqualTo(1);

        play = actor.call(Message.play(4));
        assertThat(play.getCurrentPlayer()).isEqualTo(1);

        play = actor.call(Message.play(5));
        assertThat(play.getCurrentPlayer()).isEqualTo(2);

        play = actor.call(Message.play(7));
        assertThat(play.getCurrentPlayer()).isEqualTo(1);

        play = actor.call(Message.play(0));
        assertThat(play.getCurrentPlayer()).isEqualTo(2);

        play = actor.call(Message.play(11));
        assertThat(play.getRuleOutput()).isEqualByComparingTo(RuleOutput.GAME_FINISHED);

        assertThat(play.getPits()).containsExactly(0, 0, 0, 0, 0, 0, 25, 0, 2, 2, 10, 0, 1, 8);

        assertThat(play.getScoreOfPlayer1()).isEqualTo(25);
        assertThat(play.getScoreOfPlayer2()).isEqualTo(23);
    }
}
