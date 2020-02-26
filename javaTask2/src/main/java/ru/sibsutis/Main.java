package main.java.ru.sibsutis;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

abstract class User {
    private String name;
    private String email;

    public User(String name) { this.name = name; }

    public User(String name, String email) {
        this(name);
        this.email = email;
    }

    protected String getName() { return this.name; }

    protected String getEmail() { return this.email; }
}

class Developer extends User implements CSV{
    private String project;

    public Developer(String name, String email, String project) {
        super(name, email);
        this.project = project;
    }

    String getProject() { return this.project; }

    @Override
    public Void toCSV(String str) {
        return null;
    }

    @Override
    public String fromCSV(Integer index) {
        return null;
    }
}

class Manager extends User implements CSV {
    private String level;

    public Manager(String name, String email, String level) {
        super(name, email);
        this.level = level;
    }

    String getLevel() { return this.level; }

    @Override
    public Void toCSV(String str) throws IOException {
        FileWriter fw = new FileWriter("managers.csv");
        fw.write(str);
        fw.close();
    }

    @Override
    public String fromCSV(Integer index) throws  IOException {
        FileReader fr = new FileReader("managers.csv");
        Scanner in = new Scanner(fr);
        String str = in.nextLine();
        String arr[] = str.split(";");
        return null;
    }

    @Override
    public Void removeFromCSV(Integer index) throws IOException {
        FileWriter fw = new FileWriter("managers.csv");
        
    }
}

interface CSV {
    Void toCSV(String str) throws IOException;
    String fromCSV(Integer index) throws IOException;
    Void removeFromCSV(Integer index) throws IOException;
}

public class Main {

}
