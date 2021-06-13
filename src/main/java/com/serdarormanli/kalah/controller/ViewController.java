package com.serdarormanli.kalah.controller;

import com.serdarormanli.kalah.properties.KalahProperties;
import com.serdarormanli.kalah.service.KalahService;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.views.ModelAndView;
import lombok.RequiredArgsConstructor;

import javax.inject.Inject;
import java.util.Map;

@Controller(produces = MediaType.TEXT_HTML)
@RequiredArgsConstructor(onConstructor = @__({@Inject}))
public class ViewController {

    private final KalahService kalahService;
    private final KalahProperties kalahProperties;

    @Get()
    public ModelAndView create() throws Exception {
        var snapshot = kalahService.createGame(kalahProperties.getNumberOfPitsPerPlayer(), kalahProperties.getNumberOfStonesPerPit());

        return new ModelAndView<>("index", Map.of("snapshot", snapshot));
    }

    @Get("/game/{gameId}")
    public ModelAndView snapshot(String gameId) throws Exception {
        var snapshot = kalahService.getLatestSnapshot(gameId);

        return new ModelAndView<>("index", Map.of("snapshot", snapshot));
    }

    @Get("/game/{gameId}/play/{indexOfPit}")
    public ModelAndView play(String gameId, Integer indexOfPit) throws Exception {
        var snapshot = kalahService.play(gameId, indexOfPit);

        return new ModelAndView<>("index", Map.of("snapshot", snapshot));
    }
}
