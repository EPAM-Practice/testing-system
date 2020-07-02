package com.epam.practice.testingsystem.data.dao;

import com.epam.practice.testingsystem.data.dto.Answer;
import com.epam.practice.testingsystem.data.dto.Question;

import java.util.List;

public interface IAnswerDAO extends CRUDDAO<Answer> {
    List<Answer> findAll(int questionId);
    List<Answer> findAll(Question question);
}
