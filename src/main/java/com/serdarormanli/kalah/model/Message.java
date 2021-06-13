package com.serdarormanli.kalah.model;

import lombok.Value;

@Value
public class Message {

    MessageType messageType;
    int indexOfPit;

    public static Message snapshot() {
        return new Message(MessageType.LATEST_SNAPSHOT, 0);
    }

    public static Message play(int indexOfPit) {
        return new Message(MessageType.PLAY, indexOfPit);
    }
}
