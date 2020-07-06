package com.epam.practice.testingsystem.data.dao;

import com.epam.practice.testingsystem.data.DataConnection;
import com.epam.practice.testingsystem.data.dto.UniversityGroup;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

class UniversityGroupDAO extends DbAccess implements IUniversityGroupDAO {

    @Override
    public int add(UniversityGroup data) {
        try (Connection connection = DataConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO university_group(name) VALUES (?)");
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
    public UniversityGroup find(int id) {
        try (Connection connection = DataConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM university_group WHERE id = ?");
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next())
                return DataParse.getUniversityGroup(rs);
            else
                return null;
        }
        catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(dbAccessExceptionMessage);
        }
    }

    @Override
    public List<UniversityGroup> findAll() {
        try (Connection connection = DataConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM university_group ORDER BY id");
            ResultSet rs = statement.executeQuery();
            return DataParse.getList(rs, DataParse::getUniversityGroup);
        }
        catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(dbAccessExceptionMessage);
        }
    }

    @Override
    public void update(UniversityGroup data) {
        try (Connection connection = DataConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("UPDATE university_group SET name = ? WHERE id = ?");
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
    public void delete(int id) {
        delete1Arg("university_group", "id", id);
    }

    @Override
    public void delete(UniversityGroup data) {
        delete(data.getId());
    }
}
