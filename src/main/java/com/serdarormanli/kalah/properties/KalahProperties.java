package com.serdarormanli.kalah.properties;

import io.micronaut.context.annotation.ConfigurationProperties;
import lombok.Data;

@ConfigurationProperties("kalah")
@Data
public class KalahProperties {
    private int numberOfPitsPerPlayer;
    private int numberOfStonesPerPit;
}
