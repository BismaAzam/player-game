package com.t360.task.bl;
import com.t360.task.util.CommonUtils;
import com.t360.task.util.Constants;

/**
 * Runs the player's message processing logic in a separate thread.
 * It waits for messages, creates replies, updates counters,
 * and stops when the message limit is reached.
 */

public class PlayerThread implements Runnable {
    private final Player player;

    public PlayerThread(Player player) {
        this.player = player;
    }

    @Override
    public void run() {
        sendFirstMessageIfInitiator();
        try {
            while (!isCommunicationCompleted()) {
                String receivedMessage = player.getMessagesQueue().take();
                CommonUtils.log(player.getPlayerName() + " received message: " + receivedMessage);
                String replyMessage = CommonUtils.buildReplyMessage(receivedMessage, player.getMessagesCounter());
                player.incrementMessageCounter();
                if (player.isInitiator()) {
                    player.incrementInitiatorReceivedCounter();
                    if (player.getInitiatorSentCounter() < Constants.MAX_MESSAGES) {
                        player.sendMessage(replyMessage);
                        player.incrementInitiatorSentCounter();
                    }
                } else if (player.getResponderReplyCounter() < Constants.MAX_MESSAGES) {
                    player.sendMessage(replyMessage);
                    player.incrementResponderReplyCounter();
                }
            }
            CommonUtils.log(player.getPlayerName() + " reached stop condition. So, Terminating the communication gracefully.");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void sendFirstMessageIfInitiator() {
        if (player.isInitiator() && player.getInitiatorSentCounter() < Constants.MAX_MESSAGES) {
            player.sendMessage(Constants.WELCOME_MESSAGE);
            player.incrementInitiatorSentCounter();
        }
    }

    private boolean isCommunicationCompleted() {
        if (player.isInitiator()) {
            return player.getInitiatorSentCounter() >= Constants.MAX_MESSAGES && player.getInitiatorReceivedCounter() >= Constants.MAX_MESSAGES;
        }
        return player.getResponderReplyCounter() >= Constants.MAX_MESSAGES;
    }
}