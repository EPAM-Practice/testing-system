package com.epam.practice.testingsystem.data.dao;

import com.epam.practice.testingsystem.data.dto.Answer;
import com.epam.practice.testingsystem.data.dto.Question;

import java.util.List;

interface IAnswerDAO extends CRUDDAO<Answer> {
    List<Answer> findAllByQuestionId(int questionId);
    List<Answer> findAllByQuestion(Question question);
    void linkToQuestion(Question question, Answer answer);
    void unlinkFromQuestion(Question question, Answer answer);
}
