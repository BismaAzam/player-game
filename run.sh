#!/bin/bash

# Build the project using Maven
echo "Building the project..."
mvn clean compile

# Check for a mode argument:
# - "separate" mode uses the MainSeprateProcess class to start the responder and initiator in separate processes.
# - If no argument (or any argument other than "separate") is provided, the program runs in single process mode using PlayerMain.

MODE=$1

if [ "$MODE" = "separate" ]; then
    echo "Starting in separate processes mode..."
    # Start the responder in the background.
    java -cp target/classes com.t360.task.main.PlayerSeparateProcessMain responder &
    RESPONDER_PID=$!
    
    # Wait for a brief moment to ensure the responder is up.
    sleep 2
    
    # Start the initiator in the foreground.
    java -cp target/classes com.t360.task.main.PlayerSeparateProcessMain initiator
    
    # Wait for the background process to finish.
    wait $RESPONDER_PID
else
    echo "Starting in single process mode..."
    java -cp target/classes com.t360.task.main.PlayerSameProcessMain
fi
