package com.t360.task.main;
import com.t360.task.bl.Player;

/**
 * Entry point for same-process mode.
 * It creates two players, connects them to each other,
 * and starts both players in the same Java process.
 */

public class PlayerSameProcessMain {
    public static void main(String[] args) {
        Player initiator = new Player("initiator", true);
        Player responder = new Player("responder", false);
        initiator.setResponder(responder);
        responder.setResponder(initiator);
        initiator.start();
        responder.start();
    }
}
