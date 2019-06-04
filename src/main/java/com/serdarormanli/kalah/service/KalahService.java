package com.serdarormanli.kalah.service;

import com.serdarormanli.kalah.model.RoundSnapshot;

public interface KalahService {
    RoundSnapshot createGame(int numberOfPitsPerPlayer, int numberOfStonesPerPit) throws Exception;

    RoundSnapshot play(String gameId, int indexOfPit) throws Exception;

    RoundSnapshot getLatestSnapshot(String gameId) throws Exception;
}
