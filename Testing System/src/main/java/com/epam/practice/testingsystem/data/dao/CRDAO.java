package com.epam.practice.testingsystem.data.dao;

interface CRDAO<T> {
    int add(T data);
    T find(int id);
}
