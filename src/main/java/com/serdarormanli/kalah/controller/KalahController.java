package com.serdarormanli.kalah.controller;

import com.serdarormanli.kalah.model.RoundSnapshot;
import com.serdarormanli.kalah.properties.KalahProperties;
import com.serdarormanli.kalah.service.KalahService;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.RequestAttribute;

import javax.inject.Inject;
import java.util.Optional;

@Controller("/kalah")
public class KalahController {

    private final KalahService kalahService;
    private final KalahProperties kalahProperties;

    @Inject
    public KalahController(KalahService kalahService, KalahProperties kalahProperties) {
        this.kalahService = kalahService;
        this.kalahProperties = kalahProperties;
    }

    @Post
    public RoundSnapshot createGame(@RequestAttribute("numberOfPitsPerPlayer") Optional<Integer> numberOfPitsPerPlayer,
                                    @RequestAttribute("numberOfStonesPerPit") Optional<Integer> numberOfStonesPerPit) throws Exception {
        return kalahService.createGame(numberOfPitsPerPlayer.orElse(kalahProperties.getNumberOfPitsPerPlayer()),
                numberOfStonesPerPit.orElse(kalahProperties.getNumberOfStonesPerPit()));
    }

    @Get("/{gameId}")
    public RoundSnapshot getLatestSnapshot(@PathVariable("gameId") String gameId) throws Exception {
        return kalahService.getLatestSnapshot(gameId);
    }

    @Post("/{gameId}/indexOfPit/{indexOfPit}")
    public RoundSnapshot play(@PathVariable("gameId") String gameId, @PathVariable("indexOfPit") int indexOfPit) throws Exception {
        return kalahService.play(gameId, indexOfPit);
    }
}