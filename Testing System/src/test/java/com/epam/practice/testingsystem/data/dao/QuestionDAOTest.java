package com.epam.practice.testingsystem.data.dao;

import com.epam.practice.testingsystem.data.dto.Answer;
import com.epam.practice.testingsystem.data.dto.Question;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class QuestionDAOTest {

    @Test
    void add() {
        Answer answer1 = Answer.builder()
                .answer("Answer1")
                .isCorrect(false)
                .build();
        Answer answer2 = Answer.builder()
                .answer("Answer2")
                .isCorrect(true)
                .build();

        List<Answer> answers = Arrays.asList(answer1, answer2);

        Question expectedQuestion = Question.builder()
                .question("Test question")
                .score(10)
                .answers(answers)
                .build();

        com.epam.practice.testingsystem.data.dto.Test test = DAOFactory.getTestDAO().find(5);
        IQuestionDAO questionDAO = DAOFactory.getQuestionDAO();
        int index = questionDAO.add(test, expectedQuestion);

        Question actualQuestion = questionDAO.find(index);
        assertEquals(expectedQuestion.getQuestion(), actualQuestion.getQuestion());
    }

    @Test
    void find() {
        Question question = DAOFactory.getQuestionDAO().find(7);
        int size = question.getAnswers().size();
        assertEquals(2, size);
    }

    @Test
    void findAll() {
        List<Question> questions = DAOFactory.getQuestionDAO().findAll(1);
        assertEquals(4, questions.size());
    }

    @Test
    void testFindAll() {
        List<Question> questions = DAOFactory.getQuestionDAO().findAll(DAOFactory.getTestDAO().find(1));
        assertEquals(4, questions.size());
    }

    @Test
    void update() {
        IQuestionDAO questionDAO = DAOFactory.getQuestionDAO();
        Question expectedQuestion = questionDAO.find(10);
        expectedQuestion.setQuestion("Test");
        questionDAO.update(expectedQuestion);
        Question actualQuestion = questionDAO.find(10);
        assertEquals(expectedQuestion.getQuestion(), actualQuestion.getQuestion());
    }

    @Test
    void delete() {
        IQuestionDAO questionDAO = DAOFactory.getQuestionDAO();
        questionDAO.delete(22);
        assertNull(questionDAO.find(22));
    }

}