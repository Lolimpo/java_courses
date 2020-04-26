package ru.sibsutis;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

class ServerThread implements Runnable {
    Socket client;

    ServerThread(Socket client) {
        this.client = client;
    }

    @Override
    public void run() {
        Scanner in = null;
        try {
            in = new Scanner(client.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        PrintWriter out = null;
        try {
            out = new PrintWriter(client.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        String str = in.nextLine();
        System.out.println("Client request: " + str);
        MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");
        MongoDatabase db = mongoClient.getDatabase("user");
        MongoCollection<Document> collection = db.getCollection("profile");
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(25565);
        Socket client;
        int clientsNumber = 0;
        while (clientsNumber < 10) {
            client = server.accept();
            System.out.println("New Client Connected!");
            clientsNumber++;
            new Thread(new ServerThread(client)).start();
        }
        server.close();
    }
}