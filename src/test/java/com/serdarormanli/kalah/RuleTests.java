package com.serdarormanli.kalah;

import com.deliveredtechnologies.rulebook.Fact;
import com.deliveredtechnologies.rulebook.FactMap;
import com.deliveredtechnologies.rulebook.NameValueReferableMap;
import com.deliveredtechnologies.rulebook.Result;
import com.deliveredtechnologies.rulebook.model.runner.RuleBookRunner;
import com.serdarormanli.kalah.model.Game;
import com.serdarormanli.kalah.rules.RuleOutput;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class RuleTests {

    private static RuleBookRunner ruleBook;

    private Game game;

    @BeforeAll
    public static void beforeClass() {
        ruleBook = new RuleBookRunner("com.serdarormanli.kalah.rules");
    }

    @BeforeEach
    public void setUp() {
        game = new Game(6, 0);
    }

    @Test
    public void testGameFinishedRule() {
        game.setPit(0, 6);

        NameValueReferableMap<Object> facts = new FactMap<>();
        facts.put("game", new Fact<>(game));

        ruleBook.run(facts);

        Optional<Result> result = ruleBook.getResult();

        assertThat(result).isNotEmpty();
        assertThat(result).map(Result::getValue).contains(RuleOutput.GAME_FINISHED);
    }

    @Test
    public void testPitEmptyRule() {
        game.setPit(0, 6);
        game.setPit(7, 6);

        NameValueReferableMap<Object> facts = new FactMap<>();
        facts.put("game", new Fact<>(game));
        facts.put("indexOfPit", new Fact<>(2));

        ruleBook.run(facts);

        Optional<Result> result = ruleBook.getResult();

        assertThat(result).isNotEmpty();
        assertThat(result).map(Result::getValue).contains(RuleOutput.EMPTY_PIT);
    }

    @Test
    public void testPitIndexOutOfBounds() {
        game.setPit(0, 6);
        game.setPit(7, 6);

        NameValueReferableMap<Object> facts = new FactMap<>();
        facts.put("game", new Fact<>(game));
        facts.put("indexOfPit", new Fact<>(6));

        ruleBook.run(facts);

        Optional<Result> result = ruleBook.getResult();

        assertThat(result).isNotEmpty();
        assertThat(result).map(Result::getValue).contains(RuleOutput.INDEX_OUT_OF_BOUNDS);
    }

    @Test
    public void testOwnPit() {
        game.setPit(0, 6);
        game.setPit(7, 6);

        NameValueReferableMap<Object> facts = new FactMap<>();
        facts.put("game", new Fact<>(game));
        facts.put("indexOfPit", new Fact<>(7));
        facts.put("whichPlayer", new Fact<>(1));

        ruleBook.run(facts);

        Optional<Result> result = ruleBook.getResult();

        assertThat(result).isNotEmpty();
        assertThat(result).map(Result::getValue).contains(RuleOutput.OTHER_PLAYERS_PIT);
    }

    @Test
    public void testDistribute() {
        game.setPit(0, 6);
        game.setPit(1, 6);
        game.setPit(2, 6);
        game.setPit(3, 6);
        game.setPit(4, 6);
        game.setPit(5, 6);
        game.setPit(7, 6);
        game.setPit(8, 6);
        game.setPit(9, 6);
        game.setPit(10, 6);
        game.setPit(11, 6);
        game.setPit(12, 6);

        NameValueReferableMap<Object> facts = new FactMap<>();
        facts.put("game", new Fact<>(game));
        facts.put("indexOfPit", new Fact<>(0));
        facts.put("whichPlayer", new Fact<>(1));

        ruleBook.run(facts);

        Optional<Result> result = ruleBook.getResult();

        assertThat(result).isNotEmpty();
        assertThat(result).map(Result::getValue).contains(RuleOutput.ANOTHER_TURN);

        facts = new FactMap<>();
        facts.put("game", new Fact<>(game));
        facts.put("indexOfPit", new Fact<>(1));
        facts.put("whichPlayer", new Fact<>(1));

        ruleBook.run(facts);

        result = ruleBook.getResult();

        assertThat(result).isNotEmpty();
        assertThat(result).map(Result::getValue).contains(RuleOutput.CHANGE_PLAYER);
    }
}
