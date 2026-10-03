package com.t360.task.bl;
import com.t360.task.util.CommonUtils;
import com.t360.task.util.Constants;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * Handles responder communication in separate process mode.
 * It waits for the initiator connection, receives messages,
 * sends replies with a counter, and stops when the connection is closed.
 */

public class Responder {

    public void start() {
        try (ServerSocket serverSocket = new ServerSocket(Constants.PORT)) {
            CommonUtils.log("Responder waiting for a connection on the port " + Constants.PORT);
            try (Socket socket = serverSocket.accept()) {
                CommonUtils.log("Responder connected to an initiator.");
                handleConnection(socket);
            }
        } catch (IOException e) {
            CommonUtils.logError("Responder", e);
        }
        CommonUtils.log("Gracefully Terminating the Responder communication.");
    }

    private void handleConnection(Socket socket) throws IOException {
        try (BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             BufferedWriter output = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()))) {
            int messageCounter = 1;
            while (true) {
                String receivedMessage = input.readLine();
                if (receivedMessage == null) {
                    break;
                }
                CommonUtils.log("Responder received: " + receivedMessage);
                String replyMessage = CommonUtils.buildReplyMessage(receivedMessage, messageCounter);
                messageCounter++;
                CommonUtils.sendSocketMessage("Responder", output, replyMessage);
            }
        }
    }
}