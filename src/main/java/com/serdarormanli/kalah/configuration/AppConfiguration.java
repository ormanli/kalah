package com.serdarormanli.kalah.configuration;

import co.paralleluniverse.fibers.FiberForkJoinScheduler;
import com.deliveredtechnologies.rulebook.model.RuleBook;
import com.deliveredtechnologies.rulebook.model.runner.RuleBookRunner;
import com.serdarormanli.kalah.rules.RuleOutput;
import com.serdarormanli.kalah.service.KalahService;
import com.serdarormanli.kalah.service.KalahServiceImpl;
import io.micronaut.context.annotation.Factory;

import javax.inject.Singleton;

@Factory
public class AppConfiguration {

    @Singleton
    public RuleBook ruleBook() {
        return new RuleBookRunner("com.serdarormanli.kalah.rules");
    }

    @Singleton
    public KalahService kalahService(RuleBook<RuleOutput> ruleBook, FiberForkJoinScheduler fiberForkJoinScheduler) {
        return new KalahServiceImpl(ruleBook, fiberForkJoinScheduler);
    }

    @Singleton
    public FiberForkJoinScheduler fiberForkJoinScheduler() {
        return new FiberForkJoinScheduler("actor-scheduler", 4, null, false);
    }
}
