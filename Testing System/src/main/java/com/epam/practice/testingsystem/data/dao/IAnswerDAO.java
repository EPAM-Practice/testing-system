package com.epam.practice.testingsystem.data.dao;

import com.epam.practice.testingsystem.data.dto.Answer;
import com.epam.practice.testingsystem.data.dto.Question;

import java.util.List;

interface IAnswerDAO extends CRUDDAO<Answer> {
    List<Answer> findAllByQuestion(int questionId);
    List<Answer> findAllByQuestion(Question question);
    void link(Question question, Answer answer);
    void unlink(Question question, Answer answer);
}
