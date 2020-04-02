package ru.sibsutis;

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
        assert in != null;
        String str = in.nextLine();
        System.out.println(str);

        String tmp ="HTTP/1.1 200 OK\n" +
                "Content-type: text/html\n" +
                "Content-Length: 40\n" +
                "\n" +
                "<html><body><h1>Hello</h1></body></html>";
        System.out.println(tmp);
        assert out != null;
        out.print(tmp);
        out.flush();
        try {
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

public class App {
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(25565);
        Socket client;
        int clientNum = 0;
        while (clientNum < 10) {
            client = server.accept();
            System.out.println("\n\nNew client");
            clientNum++;
            new Thread(new ServerThread(client)).start();
        }
        server.close();
    }
}
