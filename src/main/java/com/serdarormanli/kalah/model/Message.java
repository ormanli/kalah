package com.serdarormanli.kalah.model;

public class Message {

    private final MessageType messageType;
    private final int indexOfPit;

    private Message(MessageType messageType, int indexOfPit) {
        this.messageType = messageType;
        this.indexOfPit = indexOfPit;
    }

    public static Message snapshot() {
        return new Message(MessageType.LATEST_SNAPSHOT, 0);
    }

    public static Message play(int indexOfPit) {
        return new Message(MessageType.PLAY, indexOfPit);
    }

    public MessageType getMessageType() {
        return messageType;
    }

    public int getIndexOfPit() {
        return indexOfPit;
    }
}
