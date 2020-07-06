package com.epam.practice.testingsystem.data.dao;

import com.epam.practice.testingsystem.data.dto.Attempt;

import java.util.List;

public interface IAttemptDAO extends CRDAO<Attempt>, MultipleFindable<Attempt> {
    List<Attempt> findAllByUser(int userId);
    List<Attempt> findAllByTest(int testId);
}
