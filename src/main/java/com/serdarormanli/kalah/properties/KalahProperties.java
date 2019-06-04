package com.serdarormanli.kalah.properties;

import io.micronaut.context.annotation.ConfigurationProperties;

@ConfigurationProperties("kalah")
public class KalahProperties {
    private int numberOfPitsPerPlayer;
    private int numberOfStonesPerPit;

    public int getNumberOfPitsPerPlayer() {
        return numberOfPitsPerPlayer;
    }

    public void setNumberOfPitsPerPlayer(int numberOfPitsPerPlayer) {
        this.numberOfPitsPerPlayer = numberOfPitsPerPlayer;
    }

    public int getNumberOfStonesPerPit() {
        return numberOfStonesPerPit;
    }

    public void setNumberOfStonesPerPit(int numberOfStonesPerPit) {
        this.numberOfStonesPerPit = numberOfStonesPerPit;
    }
}
