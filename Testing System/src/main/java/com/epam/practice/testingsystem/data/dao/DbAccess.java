package com.epam.practice.testingsystem.data.dao;

import com.epam.practice.testingsystem.data.DataConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

abstract class DbAccess {
    protected static final String dbAccessExceptionMessage = "An error occurred while trying to access the database";

    protected static int getLastInsertedId(Connection connection) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement("SELECT LAST_INSERT_ID()")) {
            ResultSet rs = statement.executeQuery();
            rs.next();
            return rs.getInt(1);
        }
    }

    protected void delete1Arg(String table, String field, int id) {
        String sql = String.format("DELETE FROM %s WHERE %s = ?", table, field);
        try (Connection connection = DataConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            statement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(dbAccessExceptionMessage);
        }
    }

    protected void delete2Arg(String table, String field1, int id1, String field2, int id2) {
        String sql = String.format("DELETE FROM %s WHERE %s = ? AND %s = ?", table, field1, field2);
        try (Connection connection = DataConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id1);
            statement.setInt(2, id2);
            statement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(dbAccessExceptionMessage);
        }
    }
}
