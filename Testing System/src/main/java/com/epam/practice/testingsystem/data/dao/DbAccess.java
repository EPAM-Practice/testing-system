package com.epam.practice.testingsystem.data.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

abstract class DbAccess {
    protected static int getLastInsertedId(Connection connection) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement("SELECT LAST_INSERT_ID()")) {
            ResultSet rs = statement.executeQuery();
            rs.next();
            return rs.getInt(1);
        }
    }
}
