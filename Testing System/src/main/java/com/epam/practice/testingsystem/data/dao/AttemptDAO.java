package com.epam.practice.testingsystem.data.dao;

import com.epam.practice.testingsystem.data.DataConnection;
import com.epam.practice.testingsystem.data.dto.Attempt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

class AttemptDAO extends DbAccess implements IAttemptDAO {
    @Override
    public int add(Attempt data) {
        try (Connection connection = DataConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO test_attempt(user_id, test_id, score) VALUES (?, ?, ?)");
            statement.setInt(1, data.getUser().getId());
            statement.setInt(2, data.getTest().getId());
            statement.setInt(3, data.getScore());
            statement.executeUpdate();
            return getLastInsertedId(connection);
        }
        catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(DbAccess.dbAccessExceptionMessage);
        }
    }

    @Override
    public Attempt find(int id) {
        try (Connection connection = DataConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM test_attempt WHERE id = ?");
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return DataParse.getAttempt(rs);
            }
            else
                return null;
        }
        catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(DbAccess.dbAccessExceptionMessage);
        }
    }

    @Override
    public List<Attempt> findAll() {
        try (Connection connection = DataConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM test_attempt ORDER BY id");
            ResultSet rs = statement.executeQuery();
            return DataParse.getList(rs, DataParse::getAttempt);
        }
        catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(DbAccess.dbAccessExceptionMessage);
        }
    }

    @Override
    public List<Attempt> findAllByUser(int userId) {
        try (Connection connection = DataConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM test_attempt WHERE user_id = ? ORDER BY id");
            statement.setInt(1, userId);
            ResultSet rs = statement.executeQuery();
            return DataParse.getList(rs, DataParse::getAttempt);
        }
        catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(DbAccess.dbAccessExceptionMessage);
        }
    }

    @Override
    public List<Attempt> findAllByTest(int testId) {
        try (Connection connection = DataConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM test_attempt WHERE test_id = ? ORDER BY id");
            statement.setInt(1, testId);
            ResultSet rs = statement.executeQuery();
            return DataParse.getList(rs, DataParse::getAttempt);
        }
        catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(DbAccess.dbAccessExceptionMessage);
        }
    }
}
