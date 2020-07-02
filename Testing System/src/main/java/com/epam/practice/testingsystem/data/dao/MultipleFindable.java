package com.epam.practice.testingsystem.data.dao;

import java.util.List;

interface MultipleFindable<T> {
    List<T> findAll();
}
