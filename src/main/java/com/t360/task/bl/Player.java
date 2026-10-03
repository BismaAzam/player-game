package com.t360.task.bl;
import com.t360.task.bean.PlayerInfo;
import com.t360.task.util.CommonUtils;

/**
 * Represents one player in the game.
 * It can send messages to another player, receive messages,
 * start its own thread for same-process communication.
 * Handles player communication in same-process mode.
 */

public class Player extends PlayerInfo {

    private Player receiver;

    public Player(String playerName, boolean isInitiator) {
        setPlayerName(playerName);
        setInitiator(isInitiator);
    }

    public void setResponder(Player receiver) {
        this.receiver = receiver;
    }

    public void receiveMessage(String message) {
        try {
            getMessagesQueue().put(message);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void sendMessage(String message) {
        CommonUtils.log(getPlayerName() + " sends: " + message);
        receiver.receiveMessage(message);
    }

    public void start() {
        Thread playerThread = new Thread(new PlayerThread(this));
        playerThread.start();
    }
}