package com.epam.practice.testingsystem.data.dao;

import com.epam.practice.testingsystem.data.DataConnection;
import com.epam.practice.testingsystem.data.dto.UniversityGroup;
import com.epam.practice.testingsystem.data.dto.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

class UserDAO extends DbAccess implements IUserDAO {
    @Override
    public int add(User data) {
        try (Connection connection = DataConnection.getConnection()) {
            assert connection != null;
            PreparedStatement statement = connection.prepareStatement("INSERT INTO user(user_name, password_hash, role_id) VALUES (?, ?, ?)");
            statement.setString(1, data.getName());
            statement.setString(2, data.getPasswordHash());
            statement.setInt(3, data.getRoleId());
            statement.executeUpdate();
            return getLastInsertedId(connection);
        }
        catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public User find(int id) {
        try (Connection connection = DataConnection.getConnection()) {
            assert connection != null;
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM user WHERE id = ?");
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next())
                return DataParse.getUser(rs);
            else
                return null;
        }
        catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<User> findAll() {
        try (Connection connection = DataConnection.getConnection()) {
            assert connection != null;
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM user ORDER BY id");
            ResultSet rs = statement.executeQuery();
            return DataParse.getList(rs, DataParse::getUser);
        }
        catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void update(User data) {
        try (Connection connection = DataConnection.getConnection()) {
            assert connection != null;
            PreparedStatement statement = connection.prepareStatement("UPDATE user SET password_hash = ?, role_id = ? WHERE id = ?");
            statement.setString(1, data.getPasswordHash());
            statement.setInt(2, data.getRoleId());
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
            PreparedStatement statement = connection.prepareStatement("DELETE FROM user WHERE id = ?");
            statement.setInt(1, id);
            statement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(User data) {
        delete(data.getId());
    }

    @Override
    public void assignUniversityGroup(User user, UniversityGroup group) {
        try (Connection connection = DataConnection.getConnection()) {
            assert connection != null;
            PreparedStatement statement = connection.prepareStatement("INSERT INTO user_university_group(user_id, university_group_id) VALUES (?, ?)");
            statement.setInt(1, user.getId());
            statement.setInt(2, group.getId());
            statement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeUniversityGroup(User user, UniversityGroup group) {
        try (Connection connection = DataConnection.getConnection()) {
            assert connection != null;
            PreparedStatement statement = connection.prepareStatement("DELETE FROM user_university_group WHERE user_id = ? AND university_group_id = ?");
            statement.setInt(1, user.getId());
            statement.setInt(2, group.getId());
            statement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<UniversityGroup> findAssignedUniversityGroups(User user) {
        try (Connection connection = DataConnection.getConnection()) {
            assert connection != null;
            PreparedStatement statement = connection.prepareStatement("SELECT ug.id, ug.name " +
                    "FROM user_university_group uug " +
                    "INNER JOIN university_group ug ON uug.university_group_id = ug.id " +
                    "WHERE user_id = ? " +
                    "ORDER BY ug.id");
            int id = user.getId();
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            return DataParse.getList(rs, DataParse::getUniversityGroup);
        }
        catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
