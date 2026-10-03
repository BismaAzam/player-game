package com.t360.task.util;

import java.io.BufferedWriter;
import java.io.IOException;
import java.lang.management.ManagementFactory;

/**
 * Provides common helper methods used across the application.
 * It handles logging, process ID reading, message formatting,
 * socket message writing, and error logging.
 */

public class CommonUtils {

    /**
     * Utility method to print logs with the process ID.
     */
    public static void log(String message) {
        System.out.println("Process Id: [" + getProcessId() + "] " + message);
    }

    /**
     * Get the current process ID and return the process ID as a String.
     */
    public static String getProcessId() {
        String processName = ManagementFactory.getRuntimeMXBean().getName();
        return processName.split("@")[0];
    }

    /**
     * Builds the next reply message by appending the message counter to the received message.
     */
    public static String buildReplyMessage(String receivedMessage, int messageCounter) {
        return receivedMessage + " " + messageCounter;
    }

    /**
     * Sends a message through the socket output writer and also logs the message before sending it.
     */
    public static void sendSocketMessage(String senderName, BufferedWriter output, String message) throws IOException {
        log(senderName + " sends: " + message);
        output.write(message);
        output.newLine();
        output.flush();
    }

    /**
     * Prints an error message with the process ID and component name.
     */
    public static void logError(String componentName, IOException e) {
        System.err.println("[" + getProcessId() + "] " + componentName + " experienced an error: " + e.getMessage());
    }
}
