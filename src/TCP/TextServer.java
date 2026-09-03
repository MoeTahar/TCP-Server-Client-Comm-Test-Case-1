/**
* Student Name: Mohammed Tahar Souida
* Student Number:  041200233
* Course: Course name - 8108 Networking Labs
* Program/Level: CET-CS - Level 2
* Lab Professor: Prof name - Mike
*/
package TCP;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class TextServer {
    public static final int PORT = 18990;

    public static void main(String[] args) {
        System.out.println("Server starting on port " + PORT);

        try (ServerSocket serverSocket = new ServerSocket(PORT);
             BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in))) {

            boolean running = true;

            while (running) {
                System.out.println("Waiting for a client...");
                Socket socket = serverSocket.accept();
                System.out.println("Client connected.");

                DataOutputStream out = new DataOutputStream(socket.getOutputStream());

                while (true) {
                    System.out.print("Enter message to send: ");
                    String msg = keyboard.readLine();

                    out.writeUTF(msg);

                    if (msg.equalsIgnoreCase("goodbye")) {
                        System.out.println("Sent 'goodbye'. Closing client connection.");
                        socket.close();
                        break;
                    }

                    if (msg.equalsIgnoreCase("shutdown")) {
                        System.out.println("Sent 'shutdown'. Closing client and server.");
                        socket.close();
                        running = false;
                        break;
                    }
                }
            }

            System.out.println("Server stopped.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
