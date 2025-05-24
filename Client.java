import java.io.*;
import java.net.*;

public class Client {

      // Client socket
      Socket socket;
      // Input and output streams
      BufferedReader in;
      PrintWriter out;
   
      // Constructor

   public Client() {
        // Client constructor
         try {
               socket = new Socket("127.0.0.1", 7777);
               System.out.println("Client connected to server");
               // Create output stream to send messages to the server
               in = new BufferedReader(new InputStreamReader(socket.getInputStream()));  
               out = new PrintWriter(socket.getOutputStream()); 

               startReading();
               startWriting();
         } catch (Exception e) {
               e.printStackTrace();
         }
   }
   // Method to start reading messages from the server   
   public void startReading() {
         // Create a new thread to read messages from the server
         Runnable r1 = () -> {
               System.out.println("Reader started...");
               try {
                     while (true) {
                           String msg = in.readLine();
                           if (msg.equals("exit")) {
                                 System.out.println("Server terminated the chat");
                                 socket.close();
                                 break;
                           }
                           System.out.println("Server: " + msg);
                     }
               } catch (Exception e) {
                     e.printStackTrace();
                     System.out.println("Connection closed");
               }
         };
         new Thread(r1).start();
   }
   // Method to start writing messages to the server  
   public void startWriting() {
         // Create a new thread to write messages to the server
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
               }
         };
         new Thread(r2).start();
   }
   public static void main(String[] args) {
        System.out.println("Client is running...");
        // Client logic goes here
         new Client();

   } 
}
