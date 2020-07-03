package com.epam.practice.testingsystem.data.dao;

import com.epam.practice.testingsystem.data.DataConnection;
import com.epam.practice.testingsystem.data.dto.Question;
import com.epam.practice.testingsystem.data.dto.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

class QuestionDAO extends DbAccess implements IQuestionDAO {
    @Override
    public int add(Test test, Question data) {
        try (Connection connection = DataConnection.getConnection()) {
            assert connection != null;
            PreparedStatement statement = connection.prepareStatement("INSERT INTO test_question(test_id, question, score) VALUES (?, ?, ?)");
            statement.setInt(1, test.getId());
            statement.setString(2, data.getQuestion());
            statement.setInt(3, data.getScore());
            statement.executeUpdate();
            return getLastInsertedId(connection);
        }
        catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public Question find(int id) {
        try (Connection connection = DataConnection.getConnection()) {
            assert connection != null;
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM test_question WHERE id = ?");
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next())
                return DataParse.getQuestion(rs);
            else
                return null;
        }
        catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Question> findAllFromTest(int testId) {
        try (Connection connection = DataConnection.getConnection()) {
            assert connection != null;
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM test_question WHERE test_id = ? ORDER BY id");
            ResultSet rs = statement.executeQuery();
            return DataParse.getList(rs, DataParse::getQuestion);
        }
        catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Question> findAllFromTest(Test test) {
        return findAllFromTest(test.getId());
    }

    @Override
    public void update(Question data) {
        try (Connection connection = DataConnection.getConnection()) {
            assert connection != null;
            PreparedStatement statement = connection.prepareStatement("UPDATE test_question SET question = ?, score = ? WHERE id = ?");
            statement.setString(1, data.getQuestion());
            statement.setInt(2, data.getScore());
            statement.setInt(3, data.getId());
            statement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        try (Connection connection = DataConnection.getConnection()) {
            assert connection != null;
            PreparedStatement statement = connection.prepareStatement("DELETE FROM test_question WHERE id = ?");
            statement.setInt(1, id);
            statement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Question data) {
        delete(data.getId());
    }
}