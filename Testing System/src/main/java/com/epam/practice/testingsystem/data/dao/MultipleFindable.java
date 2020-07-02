package com.epam.practice.testingsystem.data.dao;

import java.util.List;

public interface MultipleFindable<T> {
    List<T> findAll();
}
