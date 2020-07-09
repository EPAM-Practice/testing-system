package com.epam.practice.testingsystem.data.dao;

import com.epam.practice.testingsystem.data.dto.Answer;
import com.epam.practice.testingsystem.data.dto.Attempt;
import com.epam.practice.testingsystem.data.dto.Deadline;
import com.epam.practice.testingsystem.data.dto.Question;
import com.epam.practice.testingsystem.data.dto.Test;
import com.epam.practice.testingsystem.data.dto.UniversityGroup;
import com.epam.practice.testingsystem.data.dto.User;
import com.epam.practice.testingsystem.data.dto.UserRole;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

class DataParse {
    private static final String dataStructureExceptionMessage = "Incorrect data structure";

    static User getUser(ResultSet rs) {
        if (rs == null)
            throw new IllegalArgumentException();
        try {
            int id = rs.getInt("id");
            String name = rs.getString("user_name");
            String pass = rs.getString("password_hash");
            int roleId = rs.getInt("role_id");
            UserRole role = DAOFactory.getUserRoleDAO().find(roleId);
            return new User(id, name, pass, role);
        }
        catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(dataStructureExceptionMessage);
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
            throw new RuntimeException(dataStructureExceptionMessage);
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
            throw new RuntimeException(dataStructureExceptionMessage);
        }
    }

    static Question getQuestion(ResultSet rs) {
        if (rs == null)
            throw new IllegalArgumentException();
        try {
            int id = rs.getInt("id");
            String question = rs.getString("question");
            int score = rs.getInt("score");
            List<Answer> answers = new AnswerDAO().findAllByQuestionId(id);
            return new Question(id, question, score, answers);
        }
        catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(dataStructureExceptionMessage);
        }
    }

    static Attempt getAttempt(ResultSet rs) {
        if (rs == null)
            throw new IllegalArgumentException();
        try {
            int id = rs.getInt("id");
            int testId = rs.getInt("test_id");
            int userId = rs.getInt("user_id");
            int score = rs.getInt("score");
            LocalDateTime datetime = rs.getTimestamp("datetime").toLocalDateTime();
            Test test = DAOFactory.getTestDAO().find(testId);
            User user = DAOFactory.getUserDAO().find(userId);
            return new Attempt(id, user, test, score, datetime);
        }
        catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(dataStructureExceptionMessage);
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
            throw new RuntimeException(dataStructureExceptionMessage);
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
            throw new RuntimeException(dataStructureExceptionMessage);
        }
    }

    static UserRole getUserRole(ResultSet rs) {
        if (rs == null)
            throw new IllegalArgumentException();
        try {
            int roleId = rs.getInt("id");
            String name = rs.getString("role_name");
            boolean checkDeadlines = rs.getBoolean("check_deadlines");
            return new UserRole(roleId, name, checkDeadlines);
        }
        catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(dataStructureExceptionMessage);
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
