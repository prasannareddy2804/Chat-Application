# Java Socket Chat Application

This project is a simple client-server chat application built using Java sockets. It allows two users to communicate over a local network using terminal input and output.

## Features
- **Client-server architecture** using Java sockets.
- **Multi-threading** for simultaneous read/write.
- **Graceful termination** when `"exit"` is sent.
- **BufferedReader & PrintWriter** for I/O handling.
- **Exception handling** for connection errors & failures.

## Getting Started

### Running the Server
1. Compile the server code:
javac Server.java
3. Run the server:
java Server

### Running the Client
1. Compile the client code:
javac Client.java
2. Run the client:
java Client


## How It Works
- The server listens for incoming client connections on **port 7777**.
- Once connected, both client and server can send and receive messages.
- If either user enters "exit", the connection closes.

## Code Structure
```bash
Chat-Application
├── Server.java   # Server-side implementation
├── Client.java   # Client-side implementation
└── README.md     # Project documentation
|__ output_image.png # Output 
```

![Chat Application Output](https://github.com/prasannareddy2804/Chat-Application/blob/main/output_image.png)
