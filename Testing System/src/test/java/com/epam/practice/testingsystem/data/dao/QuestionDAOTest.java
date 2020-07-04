package com.epam.practice.testingsystem.data.dao;

import com.epam.practice.testingsystem.data.dto.Answer;
import com.epam.practice.testingsystem.data.dto.Question;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class QuestionDAOTest {

    @Test
    void add() {
        QuestionDAO questionDAO = new QuestionDAO();
        Question question = new Question();
        question.setQuestion("Test question");
        question.setScore(10);
        Answer a1 = new Answer();
        a1.setAnswer("Answer1");
        a1.setIsCorrect(false);
        Answer a2 = new Answer();
        a2.setAnswer("Answer2");
        a2.setIsCorrect(true);
        List<Answer> answers = new ArrayList<>();
        answers.add(a1);
        answers.add(a2);
        question.setAnswers(answers);

        TestDAO testDAO = new TestDAO();
        com.epam.practice.testingsystem.data.dto.Test test = testDAO.find(1);
        int i = questionDAO.add(test, question);

        Question question1 = questionDAO.find(i);
        assertEquals(question1.getQuestion(), question.getQuestion());
    }

    @Test
    void find() {
    }

    @Test
    void findAll() {
    }

    @Test
    void testFindAll() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    @Test
    void testDelete() {
    }
}