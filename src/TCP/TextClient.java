/**
* Student Name: Mohammed Tahar Souida
* Student Number:  041200233
* Course: Course name - 8108 Networking Labs
* Program/Level: CET-CS - Level 2
* Lab Professor: Prof name - MIke
*/
package TCP;

import java.io.*;
import java.net.Socket;

public class TextClient {
    public static final String SERVER_IP = "localhost";
    public static final int PORT = 18990;

    public static void main(String[] args) {
        System.out.println("Connecting to server...");

        try (Socket socket = new Socket(SERVER_IP, PORT);
             DataInputStream in = new DataInputStream(socket.getInputStream())) {

            while (true) {
                String msg = in.readUTF();
                System.out.println("Received: " + msg);

                if (msg.equalsIgnoreCase("goodbye") || msg.equalsIgnoreCase("shutdown")) {
                    System.out.println("Termination message received. Closing client.");
                    break;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
