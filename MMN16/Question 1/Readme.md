# Trivia Game with Server and Threads

## Description
This project implements a trivia game using JavaFX, along with a server-client architecture. The game server handles the distribution of questions to multiple clients, and threads manage the communication between the server and clients.

## Features
- Displays questions with multiple-choice answers.
- Implements a timer for each question, penalizing players for running out of time.
- Supports multiple clients playing the game simultaneously.
- Server distributes questions to clients and manages game sessions.
- Utilizes threads to handle communication between the server and clients.
 
## Prerequisites
- Java Development Kit (JDK)
- JavaFX SDK
- IDE (e.g., IntelliJ IDEA, Eclipse) with JavaFX support

## How it Works
1. **Server Setup:**
   - The `Server` class initializes a server socket to listen for client connections.
   - Questions are read from a file and stored in an ArrayList.
   - When a client connects, a new `ServerThread` is created to handle communication with that client.

2. **ServerThread:**
   - Each `ServerThread` instance communicates with a single client.
   - Upon connection, a random question is selected from the question pool and sent to the client.
   - After receiving the client's answer, the thread sends the next question or ends the game session if all questions have been answered.

3. **Client Setup:**
   - The `ClientClass` and `ClientClassController` classes handle the JavaFX UI for the game.
   - The client connects to the server and receives questions through a `ClientThread`.

4. **ClientThread:**
   - The `ClientThread` class manages communication with the server.
   - Upon connection, the client receives questions from the server and displays them to the player.
   - After answering a question, the client sends the response to the server and awaits the next question.

## How to Run
1. Clone the repository to your local machine.
2. Open the project in your preferred IDE.
3. Compile and run the `Server.java` file to start the server.
4. Compile and run the `ClientClass.java` file to start the client interface.
5. Play the game by selecting answers to the questions displayed.

## Screenshots
(Include screenshots of the game interface and server console, if available)

## Credits
- Developed by Liav Segev
