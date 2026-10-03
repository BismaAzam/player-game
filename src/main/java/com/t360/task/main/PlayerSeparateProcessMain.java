package com.t360.task.main;
import com.t360.task.bl.Initiator;
import com.t360.task.bl.Responder;

/**
 * Entry point for separate-process mode.
 * It starts either the initiator or responder based on the command-line argument.
 */

public class PlayerSeparateProcessMain {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Error: Missing mandatory argument. Please specify initiator or responder.");
            return;
        }
        String role = args[0].toLowerCase();
        if (role.equals("initiator")) {
            new Initiator().start();
        } else if (role.equals("responder")) {
            new Responder().start();
        } else {
            System.out.println("Invalid argument has been passed. Use initiator or responder.");
        }
    }

}
