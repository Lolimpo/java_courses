package ru.sibsutis;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

class Developer extends User implements CSV {
    private String project;

    public Developer(String name, String email, String project) {
        super(name, email);
        this.project = project;
    }

    String getProject() { return this.project; }

    @Override
    public void toCSV() {
        String str;
        FileWriter fw;
        try {
            fw = new FileWriter("developers.csv", true);
            str = (String.join(",", this.getName(), this.getEmail(), this.getProject()));
            str = str.concat("\n");
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
            fr = new FileReader("developers.csv");
            Scanner in = new Scanner(fr);
            String str = in.nextLine();
            String[] arr = str.split(",");
        } catch(FileNotFoundException e) {
            System.err.println(e);
        }

        return null;
    }

    @Override
    public void removeFromCSV(Integer index) {
        FileReader fr;
        try {
            fr = new FileReader("developers.csv");
        } catch(FileNotFoundException e) {
            System.err.println(e);
        }
    }
}