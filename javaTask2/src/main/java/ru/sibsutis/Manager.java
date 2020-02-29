package ru.sibsutis;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

class Manager extends User implements CSV {
    private String level;

    public Manager(String name, String email, String level) {
        super(name, email);
        this.level = level;
    }

    String getLevel() { return this.level; }

    @Override
    public void toCSV() {
        String str;
        FileWriter fw;
        try {
            fw = new FileWriter("managers.csv", true);
            str = (String.join(";", this.getName(), this.getEmail(), this.getLevel(), "\n"));
            fw.write(str);
            fw.close();
        } catch(IOException e) {
            System.err.println(e);
        }
    }

    @Override
    public String fromCSV(Integer index) {
        FileReader fr;
        try {
            fr = new FileReader("managers.csv");
            Scanner in = new Scanner(fr);
            String str = in.nextLine();
            String[] arr = str.split(";");
        } catch(FileNotFoundException e) {
            System.err.println(e);
        }
        return null;
    }

    @Override
    public void removeFromCSV(Integer index) {
        try {
            FileWriter fw = new FileWriter("managers.csv");
        } catch(IOException e) {
            System.err.println(e);
        }
    }
}