package com.serdarormanli.kalah.service;

import co.paralleluniverse.actors.behaviors.Server;
import co.paralleluniverse.actors.behaviors.Supervisor;
import co.paralleluniverse.actors.behaviors.SupervisorActor;
import co.paralleluniverse.fibers.FiberFactory;
import com.deliveredtechnologies.rulebook.model.RuleBook;
import com.fasterxml.uuid.Generators;
import com.fasterxml.uuid.impl.TimeBasedGenerator;
import com.serdarormanli.kalah.actor.GameActor;
import com.serdarormanli.kalah.model.Message;
import com.serdarormanli.kalah.model.RoundSnapshot;
import com.serdarormanli.kalah.rules.RuleOutput;

import javax.inject.Inject;
import java.util.concurrent.TimeUnit;

public class KalahServiceImpl implements KalahService {
    private final RuleBook<RuleOutput> ruleBook;
    private final Supervisor supervisor;
    private final TimeBasedGenerator timeBasedGenerator;

    @Inject
    public KalahServiceImpl(RuleBook<RuleOutput> ruleBook, FiberFactory fiberFactory) {
        this.ruleBook = ruleBook;
        supervisor = new SupervisorActor(SupervisorActor.RestartStrategy.ONE_FOR_ONE).spawn(fiberFactory);
        timeBasedGenerator = Generators.timeBasedGenerator();
    }

    @Override
    public RoundSnapshot createGame(int numberOfPitsPerPlayer, int numberOfStonesPerPit) throws Exception {
        var gameId = timeBasedGenerator.generate().toString();

        var gameActor = new GameActor(gameId, ruleBook, numberOfPitsPerPlayer, numberOfStonesPerPit);

        var childSpec = new Supervisor.ChildSpec(gameId, Supervisor.ChildMode.PERMANENT, 5, 1, TimeUnit.SECONDS, 3, gameActor);

        var child = new Server<Message, RoundSnapshot, Message>(supervisor.addChild(childSpec));

        return child.call(Message.snapshot());
    }

    @Override
    public RoundSnapshot play(String gameId, int indexOfPit) throws Exception {
        var child = new Server<Message, RoundSnapshot, Message>(supervisor.getChild(gameId));

        return child.call(Message.play(indexOfPit));
    }

    @Override
    public RoundSnapshot getLatestSnapshot(String gameId) throws Exception {
        var child = new Server<Message, RoundSnapshot, Message>(supervisor.getChild(gameId));

        return child.call(Message.snapshot());
    }
}
