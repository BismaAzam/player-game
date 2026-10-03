# Player Game

A simple Java application that demonstrates communication between two players: an **Initiator** and a **Responder**.

The application supports two communication modes:

1. **Same Process Mode** – both players run in the same Java process using separate threads and communicate through blocking queues.
2. **Separate Process Mode** – the players run as separate Java processes and communicate over TCP sockets.

## How It Works

The Initiator starts the conversation by sending:

```text
Hello
```

The Responder receives the message and replies by appending a message counter. The conversation continues until the configured message limit is reached.

Example:

```text
Initiator sends: Hello
Responder received: Hello
Responder sends: Hello 1
Initiator received: Hello 1
Initiator sends: Hello 1 1
Responder received: Hello 1 1
...
```

The maximum number of messages is configured as:

```java
MAX_MESSAGES = 10;
```

## Technologies

- Java 25
- Maven
- Java Threads
- BlockingQueue
- TCP Sockets
- Object-Oriented Programming

## Project Structure

```text
PlayerGame/
├── src/main/java/com/t360/task/
│   ├── bean/
│   │   └── PlayerInfo.java
│   ├── bl/
│   │   ├── Initiator.java
│   │   ├── Player.java
│   │   ├── PlayerThread.java
│   │   └── Responder.java
│   ├── main/
│   │   ├── PlayerSameProcessMain.java
│   │   └── PlayerSeparateProcessMain.java
│   └── util/
│       ├── CommonUtils.java
│       └── Constants.java
├── pom.xml
├── run.sh
└── .gitignore
```

## Requirements

Make sure the following are installed:

- JDK 25
- Apache Maven
- Git

Check the installations with:

```bash
java -version
mvn -version
git --version
```

## Build the Project

From the project root directory:

```bash
mvn clean compile
```

## Run in Same Process Mode

Both players run in the same JVM using separate threads.

```bash
java -cp target/classes com.t360.task.main.PlayerSameProcessMain
```

Or, on Linux/macOS:

```bash
chmod +x run.sh
./run.sh
```

## Run in Separate Process Mode

In this mode, the Responder acts as a server on port `5000`, while the Initiator connects to it as a client.

Using the provided script:

```bash
./run.sh separate
```

Alternatively, start them manually.

First terminal:

```bash
java -cp target/classes com.t360.task.main.PlayerSeparateProcessMain responder
```

Second terminal:

```bash
java -cp target/classes com.t360.task.main.PlayerSeparateProcessMain initiator
```

## Main Concepts Demonstrated

This project demonstrates:

- Inter-thread communication
- Java concurrency
- `BlockingQueue` and `LinkedBlockingQueue`
- TCP client-server communication
- `Socket` and `ServerSocket`
- Process-based communication
- Message counters and termination conditions
- Separation of business logic, data and utility classes

## Configuration

Application constants are defined in:

```text
src/main/java/com/t360/task/util/Constants.java
```

Current configuration:

```java
MAX_MESSAGES = 10
WELCOME_MESSAGE = "Hello"
HOST = "localhost"
PORT = 5000
```

## Author

**Bisma Azam**
