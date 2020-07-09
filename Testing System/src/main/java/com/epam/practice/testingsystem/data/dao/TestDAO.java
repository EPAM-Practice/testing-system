package com.epam.practice.testingsystem.data.dao;

import com.epam.practice.testingsystem.data.DataConnection;
import com.epam.practice.testingsystem.data.dto.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

class TestDAO extends DbAccess implements ITestDAO {
    @Override
    public int add(Test data) {
        try (Connection connection = DataConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO test(name) VALUES (?)");
            statement.setString(1, data.getName());
            statement.executeUpdate();
            return getLastInsertedId(connection);
        }
        catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(dbAccessExceptionMessage);
        }
    }

    @Override
    public Test find(int id) {
        try (Connection connection = DataConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM test WHERE id = ?");
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next())
                return DataParse.getTest(rs);
            else
                return null;
        }
        catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(dbAccessExceptionMessage);
        }
    }

    @Override
    public List<Test> findAll() {
        try (Connection connection = DataConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM test ORDER BY id");
            ResultSet rs = statement.executeQuery();
            return DataParse.getList(rs, DataParse::getTest);
        }
        catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(dbAccessExceptionMessage);
        }
    }

    @Override
    public void update(Test data) {
        try (Connection connection = DataConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("UPDATE test SET name = ? WHERE id = ?");
            statement.setString(1, data.getName());
            statement.setInt(2, data.getId());
            statement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(dbAccessExceptionMessage);
        }
    }

    @Override
    public void deleteById(int id) {
        delete1Arg("test", "id", id);
    }

    @Override
    public void delete(Test data) {
        deleteById(data.getId());
    }
}
