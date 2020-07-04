package com.epam.practice.testingsystem.data.dao;

import com.epam.practice.testingsystem.data.dto.Answer;
import com.epam.practice.testingsystem.data.dto.Question;

import java.util.List;

interface IAnswerDAO extends CRUDDAO<Answer> {
    List<Answer> findAll(int questionId);
    List<Answer> findAll(Question question);
    void link(Question question, Answer answer);
    void unlink(Question question, Answer answer);
}
