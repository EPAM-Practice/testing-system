package com.epam.practice.testingsystem.data.dao;

import com.epam.practice.testingsystem.data.dto.UserRole;

import java.util.List;

public interface IUserRoleDAO {
    UserRole find(int id);
    List<UserRole> findAll();
}
