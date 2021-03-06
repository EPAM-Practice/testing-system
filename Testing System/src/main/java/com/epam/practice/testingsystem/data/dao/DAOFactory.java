package com.epam.practice.testingsystem.data.dao;

public class DAOFactory {
    public static IAnswerDAO getAnswerDAO() {
        return new AnswerDAO();
    }

    public static IQuestionDAO getQuestionDAO() {
        return new QuestionDAO();
    }

    public static ITestDAO getTestDAO() {
        return new TestDAO();
    }

    public static IUserDAO getUserDAO() {
        return new UserDAO();
    }

    public static IUniversityGroupDAO getUniversityGroupDAO() {
        return new UniversityGroupDAO();
    }

    public static IAttemptDAO getAttemptDAO() {
        return new AttemptDAO();
    }

    public static IDeadlineDAO getDeadlineDAO() {
        return new DeadlineDAO();
    }

    public static IUserRoleDAO getUserRoleDAO() {
        return new UserRoleDAO();
    }
}
