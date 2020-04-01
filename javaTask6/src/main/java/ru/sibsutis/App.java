package ru.sibsutis;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(25565);
        Socket client = server.accept();

        Scanner in = new Scanner(client.getInputStream());
        PrintWriter out = new PrintWriter(client.getOutputStream());

        String str = in.nextLine();
        System.out.println(str);

        String tmp ="HTTP/1.1 200 OK\n" + "Content-type: text/html\n" + "Content-Length: 149\n" + "\n" +
                "<html><body><p>Hello</p></body></html>";
        System.out.println(tmp);
        out.print(tmp);
        out.flush();

        client.close();
        server.close();
    }
}
