package com.epam.practice.testingsystem.data.dao;

import com.epam.practice.testingsystem.data.dto.UniversityGroup;
import com.epam.practice.testingsystem.data.dto.User;

import java.util.List;

public interface IUserDAO extends CRUDDAO<User>, MultipleFindable<User> {
    void assignUniversityGroup(User user, UniversityGroup group);
    void removeUniversityGroup(User user, UniversityGroup group);
    List<UniversityGroup> findAssignedUniversityGroups(User user);
}
