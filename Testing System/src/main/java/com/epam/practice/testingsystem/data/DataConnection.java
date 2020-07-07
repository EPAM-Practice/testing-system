package com.epam.practice.testingsystem.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataConnection {
    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            String url = System.getenv("DB_URL");
            String user = System.getenv("DB_USER");
            String password = System.getenv("DB_PASSWORD");
            return DriverManager.getConnection(url, user, password);
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("Cannot load JDBC driver");
        }
    }
}
