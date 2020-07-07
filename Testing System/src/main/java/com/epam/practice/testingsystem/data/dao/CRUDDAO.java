package com.epam.practice.testingsystem.data.dao;

interface CRUDDAO<T> extends CRDAO<T> {
    void update(T data);
    void deleteById(int id);
    void delete(T data);
}
