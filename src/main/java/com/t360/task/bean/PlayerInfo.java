package com.t360.task.bean;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Stores player details such as name, message queue, role and counters.
 * It helps the Player class keep track of sent and received messages.
 */
public class PlayerInfo {

    private String playerName;
    private BlockingQueue<String> messageQueue = new LinkedBlockingQueue<>();
    private boolean initiator;
    private int initiatorSentCounter = 0;
    private int messagesCounter = 1;
    private int initiatorReceivedCounter = 0;
    private int responderReplyCounter = 0;

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public BlockingQueue<String> getMessagesQueue() {
        return messageQueue;
    }

    public boolean isInitiator() {
        return initiator;
    }

    public void setInitiator(boolean initiator) {
        this.initiator = initiator;
    }

    public int getInitiatorSentCounter() {
        return initiatorSentCounter;
    }

    public void setInitiatorSentCounter(int initiatorSentCounter) {
        this.initiatorSentCounter = initiatorSentCounter;
    }

    public int getMessagesCounter() {
        return messagesCounter;
    }

    public void setMessagesCounter(int messagesCounter) {
        this.messagesCounter = messagesCounter;
    }

    public int getInitiatorReceivedCounter() {
        return initiatorReceivedCounter;
    }

    public void setInitiatorReceivedCounter(int initiatorReceivedCounter) {
        this.initiatorReceivedCounter = initiatorReceivedCounter;
    }

    public int getResponderReplyCounter() {
        return responderReplyCounter;
    }

    public void setResponderReplyCounter(int responderReplyCounter) {
        this.responderReplyCounter = responderReplyCounter;
    }

    public void incrementInitiatorSentCounter() {
        setInitiatorSentCounter(getInitiatorSentCounter() + 1);
    }

    public void incrementMessageCounter() {
        setMessagesCounter(getMessagesCounter() + 1);
    }

    public void incrementInitiatorReceivedCounter() {
        setInitiatorReceivedCounter(getInitiatorReceivedCounter() + 1);
    }

    public void incrementResponderReplyCounter() {
        setResponderReplyCounter(getResponderReplyCounter() + 1);
    }
}