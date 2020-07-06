package com.epam.practice.testingsystem.data.dao;

import com.epam.practice.testingsystem.data.DataConnection;
import com.epam.practice.testingsystem.data.dto.Deadline;
import com.epam.practice.testingsystem.data.dto.Test;
import com.epam.practice.testingsystem.data.dto.UniversityGroup;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

class DeadlineDAO extends DbAccess implements IDeadlineDAO {
    @Override
    public void setDeadline(Test test, UniversityGroup group, LocalDate date) {
        if (getDeadline(test, group) == null)
            insertDeadline(test, group, date);
        else
            updateDeadline(test, group, date);
    }

    private void insertDeadline(Test test, UniversityGroup group, LocalDate date)
    {
        try (Connection connection = DataConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO test_deadline(test_id, university_group_id, deadline) VALUES (?, ?, ?)");
            statement.setInt(1, test.getId());
            statement.setInt(2, group.getId());
            statement.setObject(3, date);
            statement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(dbAccessExceptionMessage);
        }
    }

    private void updateDeadline(Test test, UniversityGroup group, LocalDate date)
    {
        try (Connection connection = DataConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("UPDATE test_deadline SET deadline = ? WHERE test_id = ? AND university_group_id = ?");
            statement.setObject(1, date);
            statement.setInt(2, test.getId());
            statement.setInt(3, group.getId());
            statement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(dbAccessExceptionMessage);
        }
    }

    @Override
    public Deadline getDeadline(Test test, UniversityGroup group) {
        try (Connection connection = DataConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM test_deadline WHERE test_id = ? AND university_group_id = ?");
            statement.setInt(1, test.getId());
            statement.setInt(2, group.getId());
            ResultSet rs = statement.executeQuery();
            if (rs.next())
                return DataParse.getDeadline(rs);
            else
                return null;
        }
        catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(dbAccessExceptionMessage);
        }
    }

    @Override
    public List<Deadline> getDeadlines(Test test) {
        try (Connection connection = DataConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM test_deadline WHERE test_id = ?");
            statement.setInt(1, test.getId());
            ResultSet rs = statement.executeQuery();
            return DataParse.getList(rs, DataParse::getDeadline);
        }
        catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(dbAccessExceptionMessage);
        }
    }

    @Override
    public List<Deadline> getDeadlines(UniversityGroup universityGroup) {
        try (Connection connection = DataConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM test_deadline WHERE university_group_id = ?");
            statement.setInt(1, universityGroup.getId());
            ResultSet rs = statement.executeQuery();
            return DataParse.getList(rs, DataParse::getDeadline);
        }
        catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(dbAccessExceptionMessage);
        }
    }

    public void removeDeadline(Test test, UniversityGroup group) {
        delete2Arg("test_deadline", "test_id", test.getId(), "university_group_id", group.getId());
    }
}
