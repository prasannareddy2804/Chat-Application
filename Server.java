import java.net.*;
import java.io.*;

class Server{

    // Server socket
    ServerSocket server;
    Socket socket;
    // Input and output streams
    BufferedReader in;
    PrintWriter out;
    // Constructor
    public Server() {
       try {
        // Create a server socket on port 7777
        server = new ServerSocket(7777);
        System.out.println("Server Waiting for client to connect...");
    
        // Accept a connection from a client
        socket = server.accept();

        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        out = new PrintWriter(socket.getOutputStream());

        startReading();
        startWriting();
        System.out.println("Client connected: ");
        // Close the server socket
        server.close();
       } catch (Exception e) {
        e.printStackTrace();
       }
    }   


    public void startReading() {
        // Create a new thread to read messages from the client
        Runnable r1 = () -> {
            System.out.println("Reader started...");
            try {
                while (true) {
                    String msg = in.readLine();
                    if (msg == null || msg.equalsIgnoreCase("exit")) {
                        System.out.println("Client terminated the chat");
                        socket.close();
                        break;
                    }
                    System.out.println("Client: " +msg);
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Connection closed");
            }
        };
        new Thread(r1).start();
    }


    public void startWriting() {
        // Create a new thread to write messages to the client
        Runnable r2 = () -> {
            System.out.println("Writer started...");
            try {
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                while (!socket.isClosed()) {
                    String content = br.readLine();
                    out.println(content);
                    out.flush();
                    if (content.equals("exit")) {
                        socket.close();
                        break;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Connection closed");
            }
        };
        new Thread(r2).start();
    }

    public static void main(String[] args) {
        System.out.println("Server is running...");
        new Server();
    }
}