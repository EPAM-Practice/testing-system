package com.epam.practice.testingsystem.data.dao;

import com.epam.practice.testingsystem.data.dto.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

class DataParse {
    static User getUser(ResultSet rs) {
        if (rs == null)
            throw new IllegalArgumentException();
        try {
            int id = rs.getInt("id");
            String name = rs.getString("user_name");
            String pass = rs.getString("password_hash");
            int roleId = rs.getInt("role_id");
            return new User(id, name, pass, roleId);
        }
        catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    static UniversityGroup getUniversityGroup(ResultSet rs) {
        if (rs == null)
            throw new IllegalArgumentException();
        try {
            UniversityGroup universityGroup = new UniversityGroup();
            int id = rs.getInt("id");
            String name = rs.getString("name");
            universityGroup.setId(id);
            universityGroup.setName(name);
            return universityGroup;
        }
            catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    static Answer getAnswer(ResultSet rs) {
        if (rs == null)
            throw new IllegalArgumentException();
        try {
            int id = rs.getInt("id");
            String answer = rs.getString("answer");
            Boolean isCorrect = rs.getBoolean("is_correct");
            if (rs.wasNull())
                isCorrect = null;
            return new Answer(id, answer, isCorrect);
        }
        catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    static Question getQuestion(ResultSet rs) {
        if (rs == null)
            throw new IllegalArgumentException();
        try {
            int id = rs.getInt("id");
            String question = rs.getString("question");
            int score = rs.getInt("score");
            List<Answer> answers = new AnswerDAO().findAll(id);
            return new Question(id, question, score, answers);
        }
        catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    static Attempt getAttempt(ResultSet rs) {
        if (rs == null)
            throw new IllegalArgumentException();
        try {
            Attempt attempt = new Attempt();
            int id = rs.getInt("id");
            int testId = rs.getInt("test_id");
            int userId = rs.getInt("user_id");
            int score = rs.getInt("score");
            Time datetime = rs.getTime("datetime");
            attempt.setId(id);
            attempt.setDateTime(datetime);
            attempt.setScore(score);

            ITestDAO testDAO = new TestDAO();
            Test test = testDAO.find(testId);
            attempt.setTest(test);

            IUserDAO userDAO = new UserDAO();
            User user = userDAO.find(userId);
            attempt.setUser(user);
            return attempt;
        }
        catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    static Test getTest(ResultSet rs) {
        if (rs == null)
            throw new IllegalArgumentException();
        try {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            Test test = new Test();
            test.setId(id);
            test.setName(name);
            return test;
        }
        catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    static Deadline getDeadline(ResultSet rs) {
        if (rs == null)
            throw new IllegalArgumentException();
        try {
            int testId = rs.getInt("test_id");
            Test test = DAOFactory.getTestDAO().find(testId);
            int universityGroupId = rs.getInt("university_group_id");
            UniversityGroup universityGroup = DAOFactory.getUniversityGroupDAO().find(universityGroupId);
            LocalDate deadline = rs.getDate("deadline").toLocalDate();
            return new Deadline(test, universityGroup, deadline);
        }
        catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    static <T> List<T> getList(ResultSet rs, Function<ResultSet, T> handler) {
        if (rs == null)
            throw new IllegalArgumentException();
        List<T> list = new ArrayList<>();
        try {
            while (rs.next()) {
                T item = handler.apply(rs);
                list.add(item);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
