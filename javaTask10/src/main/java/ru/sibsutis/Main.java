package ru.sibsutis;

import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

class ServerThread implements Runnable {
    Socket client;

    ServerThread(Socket client) {
        this.client = client;
    }

    public void _runThread() throws IOException {
        MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");
        MongoDatabase db = mongoClient.getDatabase("user");
        MongoCollection<Document> collection = db.getCollection("profile");
        StringBuilder response = new StringBuilder();
        String JSON = "";

        Scanner in = new Scanner(client.getInputStream());
        String str = in.nextLine();
        System.out.println("Client request: " + str);

        str = str.substring(5);
        str = str.substring(0, str.length() - 9);
        str = str.replace("%20", "");

        int requestType = 0;
        if(str.contains("info"))
            requestType = 1;
        else if (str.contains("insert"))
            requestType = 2;
        else if (str.contains("delete"))
            requestType = 3;

        switch (requestType) {
            case 1:
                for (Document doc : collection.find()) {
                    response.append(doc.toJson());
                    response.append(",");
                }
                JSON += response.toString().substring(0, response.length() - 1);
                sendResponse(JSON);
                break;
            case 2:
                String[] args = str.split("[?&]");
                String[] recordName = args[1].split("[=]");
                String[] recordAge = args[2].split("[=]");
                String[] recordInterest = args[3].split("[=]");
                List<String> allInterests = Arrays.asList(recordInterest[1].split("[,]"));
                Document record = new Document("name", recordName[1])
                        .append("age", recordAge[1])
                        .append("interests", allInterests);
                collection.insertOne(record);
                for (Document doc : collection.find()) {
                    response.append(doc.toJson());
                    response.append(",");
                }
                JSON += response.toString().substring(0, response.length() - 1);
                sendResponse(JSON);
                break;
            case 3:
                String[] arg = str.split("[?&]");
                String[] deleteName = arg[1].split("[=]");
                if (deleteName.length < 2) {
                    System.out.println("Empty delete request");
                    break;
                }
                BasicDBObject searchObject = new BasicDBObject();
                searchObject.append("name", deleteName[1]);
                collection.deleteOne(searchObject);
                for (Document doc : collection.find()) {
                    response.append(doc.toJson());
                    response.append(",");
                }
                JSON += response.toString().substring(0, response.length() - 1);
                sendResponse(JSON);
                break;
            default:
                JSON += "{\"Status\": \"ok\"}";
                sendResponse(JSON);
        }
        mongoClient.close();
        client.close();
    }

    private void sendResponse(String json) throws IOException {
        String response = "HTTP/1.1 200 OK\n" +
                "Content-type: application/json\n" +
                "Access-Control-Allow-Origin: *\n" +
                "\n" +
                "[" + json + "]";
        System.out.println("Formed response: " + response);
        PrintWriter out = new PrintWriter(client.getOutputStream());
        out.print(response);
        out.flush();
    }

    @Override
    public void run() {
        try {
            _runThread();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(8081);
        Socket client;
        int clientsNumber = 0;
        while (clientsNumber < 1000) {
            client = server.accept();
            System.out.println("New Client Connected!");
            clientsNumber++;
            new Thread(new ServerThread(client)).start();
        }
        server.close();
    }
}