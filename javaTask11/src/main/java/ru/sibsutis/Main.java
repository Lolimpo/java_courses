package ru.sibsutis;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Main class. Listens to port 8081 and gets new client.
 * @author Nikita Mikheev
 * @version 1.0
 */
public class Main {
    /**
     * Main function. Program starting point.
     * @param args main arguments
     * @throws IOException input-output exception
     */
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(8081);
        Socket client;
        int clientsNumber = 0;
        while (clientsNumber < 15) {
            client = server.accept();
            System.out.println("New Client Connected!");
            clientsNumber++;
            new Thread(new ServerThread(client)).start();
        }
        server.close();
    }
}