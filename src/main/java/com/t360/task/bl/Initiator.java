package com.t360.task.bl;
import com.t360.task.util.CommonUtils;
import com.t360.task.util.Constants;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

/**
 * Starts the separate-process conversation as a client.
 * It connects to the responder, sends the first message, receives replies,
 * sends the next message with a counter, and stops after 10 replies.
 */
public class Initiator {
    public void start() {
        try (Socket socket = new Socket(Constants.HOST, Constants.PORT); BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream())); BufferedWriter output = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()))) {
            startConversation(input, output);
        } catch (IOException e) {
            CommonUtils.logError("Initiator", e);
        }
        CommonUtils.log("Gracefully Terminating Initiator communication.");
    }

    private void startConversation(BufferedReader input, BufferedWriter output) throws IOException {
        int sentCounter = 0;
        int receivedCounter = 0;
        int messageCounter = 1;
        CommonUtils.sendSocketMessage("Initiator", output, Constants.WELCOME_MESSAGE);
        sentCounter++;
        while (true) {
            String receivedMessage = input.readLine();
            if (receivedMessage == null) {
                break;
            }
            CommonUtils.log("Initiator received: " + receivedMessage);
            receivedCounter++;
            if (receivedCounter >= Constants.MAX_MESSAGES) {
                CommonUtils.log("Initiator reached stop condition. So, Finalizing the conversation.");
                break;
            }
            if (sentCounter < Constants.MAX_MESSAGES) {
                String replyMessage = CommonUtils.buildReplyMessage(receivedMessage, messageCounter);
                messageCounter++;
                CommonUtils.sendSocketMessage("Initiator", output, replyMessage);
                sentCounter++;
            }
        }
    }
}