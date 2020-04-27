package ru.sibsutis;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Objects;
import java.util.Scanner;

class ServerThread implements Runnable {
    Socket client;

    ServerThread(Socket client) {
        this.client = client;
    }

    public String formDataSting() throws IOException {
        String response = "";
        ObjectMapper mapper = new ObjectMapper();
        MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");
        MongoDatabase db = mongoClient.getDatabase("user");
        MongoCollection<Document> collection = db.getCollection("profile");
        for (Document doc: collection.find()) {
            response.concat("ass");
        }
        System.out.println(response);
        return mapper.writeValueAsString(response);
    }

    @Override
    public void run() {
        MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");
        MongoDatabase db = mongoClient.getDatabase("user");
        MongoCollection<Document> collection = db.getCollection("profile");
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
        assert in != null;
        String str = in.nextLine();
        System.out.println("Client request: " + str);
        if (str.contains("info")) {
            StringBuilder response = new StringBuilder();
            String JSON = "HTTP/1.1 200 OK\n" +
                    "Content-type: application/json\n" +
                    "\n";
            for (Document doc : collection.find()) {
                response.append(doc.toJson());
                response.append("\n");
            }
            JSON += response.toString();
            System.out.println(JSON);
            out.print(JSON);
            out.flush();
        }
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