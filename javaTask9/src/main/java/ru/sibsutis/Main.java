package ru.sibsutis;

import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class Main {
    public static void main(String[] args) throws IOException, SQLException {
        Properties properties = new Properties();
        properties.load(new FileReader("src/main/resources/app.properties"));
        Connection connection = DriverManager.getConnection(
                properties.getProperty("db.host"),
                properties.getProperty("db.user"),
                properties.getProperty("db.password"));

        Statement statement = connection.createStatement();
        connection.setAutoCommit(false);

        Long startTime = System.nanoTime();
        for(int i = 0; i < 1_000_000; i++) {
            statement.execute("INSERT INTO user (id, fio, phone) VALUES (" + i + ", 'Sample', 89231112222)");
        }
        Long endTime = System.nanoTime();
        System.out.println("1kk elements addition took:" + ((double)endTime - startTime) / 1_000_000_000 + "sec.");

//        ResultSet resultSet = statement.executeQuery("SELECT * FROM user"); //Full base printing
//        while (resultSet.next()) {
//            String id = resultSet.getString("id");
//            String fio = resultSet.getString("fio");
//            String phone = resultSet.getString("phone");
//            System.out.println(id + " " + fio + " " + phone);
//        }

        statement.execute("DELETE FROM user");
        connection.commit();
        connection.close();
//      resultSet.close();
    }
}