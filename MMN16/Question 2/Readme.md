# Java Broadcasting Message Program

This JavaFX application demonstrates a simple broadcasting message program using UDP communication protocol. It consists of two main components: a client and a server.

## Overview

The application allows multiple clients to connect to a server and send messages. The server then broadcasts these messages to all connected clients. This is achieved through a graphical user interface (GUI) built with JavaFX.

## Components

### Client
- The client GUI is created using JavaFX.
- It allows users to connect to the server and send messages.
- Users can specify the server's IP address and port number.
- The client communicates with the server using UDP sockets.

### Server
- The server manages connections from multiple clients.
- It listens for incoming messages from clients and broadcasts them to all connected clients.
- The server runs in a separate thread to handle client requests concurrently.

## File Structure

- `Client.java`: Main class for the client application.
- `ClientController.fxml`: FXML file defining the client GUI layout.
- `ClientController.java`: Controller class for the client GUI.
- `ClientThread.java`: Thread class for managing client-server communication.
- `Manager.java`: Main class for the server application.
- `ManagerController.fxml`: FXML file defining the server GUI layout.
- `ManagerController.java`: Controller class for the server GUI.
- `ManagerThread.java`: Thread class for broadcasting messages to clients.
- `ServerThread.java`: Thread class for managing client connections on the server.
- `UDPServer.java`: Main class for the UDP server.

## How to Run

1. Compile the Java files using `javac`.
2. Launch the server by running the `Manager` class.
3. Launch one or more clients by running the `Client` class.
4. Enter the server's IP address and port number in the client GUI.
5. Click "Connect" to establish a connection with the server.
6. Send messages from the client, which will be broadcasted to all connected clients.

## Dependencies

- Java Development Kit (JDK) version X.X or higher.
- JavaFX library (included in JDK).

## Credits
- Developed by Liav Segev
