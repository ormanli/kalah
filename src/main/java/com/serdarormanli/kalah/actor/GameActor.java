package com.serdarormanli.kalah.actor;

import co.paralleluniverse.actors.ActorRef;
import co.paralleluniverse.actors.behaviors.ServerActor;
import com.deliveredtechnologies.rulebook.Fact;
import com.deliveredtechnologies.rulebook.FactMap;
import com.deliveredtechnologies.rulebook.NameValueReferableMap;
import com.deliveredtechnologies.rulebook.model.RuleBook;
import com.serdarormanli.kalah.model.Game;
import com.serdarormanli.kalah.model.Message;
import com.serdarormanli.kalah.model.RoundSnapshot;
import com.serdarormanli.kalah.rules.RuleOutput;

import static java.util.Arrays.stream;

public class GameActor extends ServerActor<Message, RoundSnapshot, Message> {
    private final RuleBook<RuleOutput> ruleBook;
    private final Game game;

    public GameActor(String id, RuleBook<RuleOutput> ruleBook, int numberOfPitsPerPlayer, int numberOfStonesPerPit) {
        super(id);
        this.ruleBook = ruleBook;
        game = new Game(numberOfPitsPerPlayer, numberOfStonesPerPit);
    }

    @Override
    protected RoundSnapshot handleCall(ActorRef<?> from, Object id, Message m) throws Exception {
        switch (m.getMessageType()) {
            case PLAY:
                return play(m.getIndexOfPit());
            case LATEST_SNAPSHOT:
                return getLatestSnapshot();
            default:
                throw new RuntimeException();
        }
    }

    private RoundSnapshot play(int indexOfPit) {
        NameValueReferableMap<Object> facts = new FactMap<>();

        facts.put("game", new Fact<>(game));
        facts.put("indexOfPit", new Fact<>(indexOfPit));

        var result = executeRules(facts);

        return getLatestSnapshot(result);
    }

    private RuleOutput executeRules(NameValueReferableMap<Object> facts) {
        ruleBook.run(facts);

        return ruleBook.getResult()
                .orElseThrow(RuntimeException::new)
                .getValue();
    }

    private RoundSnapshot getLatestSnapshot() {
        NameValueReferableMap<Object> facts = new FactMap<>();

        facts.put("game", new Fact<>(game));
        facts.put("snapshot", new Fact<>(true));

        var result = executeRules(facts);

        return getLatestSnapshot(result);
    }

    private RoundSnapshot getLatestSnapshot(RuleOutput result) {
        var pits = game.getPits();
        var numberOfPitsPerPlayer = game.getNumberOfPitsPerPlayer();
        var totalNumberOfPits = game.getTotalNumberOfPits();

        if (result == RuleOutput.GAME_FINISHED) {
            var scoreOfPlayer1 = stream(pits, 0, numberOfPitsPerPlayer + 1).sum();
            var scoreOfPlayer2 = stream(pits, numberOfPitsPerPlayer + 1, totalNumberOfPits).sum();

            return new RoundSnapshot(getName(), pits, game.getCurrentPlayer(), result, scoreOfPlayer1, scoreOfPlayer2);
        } else {
            return new RoundSnapshot(getName(), pits, game.getCurrentPlayer(), result, 0, 0);
        }
    }
}
