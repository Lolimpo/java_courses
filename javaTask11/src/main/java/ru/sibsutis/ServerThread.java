package ru.sibsutis;

import com.mongodb.BasicDBObject;
import com.mongodb.client.*;
import lombok.*;
import org.bson.Document;

import java.io.*;
import java.net.Socket;
import java.util.*;

/**
 * Server class, where all the magic happens. Implements Runnable Java class for every client to be in a separate thread.
 * @author Nikita Mikheev
 * @version 1.0
 * @see Main
 */

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
class ServerThread implements Runnable {
    /** Client connection socket */
    Socket client;

    /**
     * New client connection handling.
     */
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

    /**
     * Response sender.
     * @param json json string which would be send to the client
     */
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

    /**
     * Started thread.
     */
    @Override
    public void run() {
        try {
            _runThread();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
