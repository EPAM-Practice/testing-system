package com.epam.practice.testingsystem.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataConnection {
    public static Connection getConnection() throws SQLException {
        String url = System.getenv("DB_URL");
        String user = System.getenv("DB_USER");
        String password = System.getenv("DB_PASSWORD");

        try {
            return DriverManager.getConnection(url, user, password);
        }
        catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }
}
