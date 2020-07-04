package com.epam.practice.testingsystem.data.dao;

import com.epam.practice.testingsystem.data.DataConnection;
import com.epam.practice.testingsystem.data.dto.Answer;
import com.epam.practice.testingsystem.data.dto.Question;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

class AnswerDAO extends DbAccess implements IAnswerDAO {
    @Override
    public int add(Answer data) {
        try (Connection connection = DataConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO test_answer(answer) VALUES (?)");
            statement.setString(1, data.getAnswer());
            statement.executeUpdate();
            return getLastInsertedId(connection);
        }
        catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("An error occurred while trying to access the database");
        }
    }

    @Override
    public Answer find(int id) {
        try (Connection connection = DataConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("SELECT *, NULL as is_correct FROM test_answer WHERE id = ?");
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next())
                return DataParse.getAnswer(rs);
            else
                return null;
        }
        catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("An error occurred while trying to access the database");
        }
    }

    @Override
    public List<Answer> findAll(int questionId) {
        try (Connection connection = DataConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("SELECT id, answer, is_correct " +
                    "FROM test_answer ta " +
                        "INNER JOIN test_question_answer tqa ON ta.id = tqa.answer_id " +
                    "WHERE question_id = ? " +
                    "ORDER BY id");
            statement.setInt(1, questionId);
            ResultSet rs = statement.executeQuery();
            return DataParse.getList(rs, DataParse::getAnswer);
        }
        catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("An error occurred while trying to access the database");
        }
    }

    @Override
    public List<Answer> findAll(Question question) {
        return findAll(question.getId());
    }

    @Override
    public void update(Answer data) {
        try (Connection connection = DataConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("UPDATE test_answer SET answer = ? WHERE id = ?");
            statement.setString(1, data.getAnswer());
            statement.setInt(2, data.getId());
            statement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("An error occurred while trying to access the database");
        }
    }

    @Override
    public void delete(int id) {
        try (Connection connection = DataConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM test_answer WHERE id = ?");
            statement.setInt(1, id);
            statement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("An error occurred while trying to access the database");
        }
    }

    @Override
    public void delete(Answer data) {
        delete(data.getId());
    }
}
