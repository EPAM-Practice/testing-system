package com.epam.practice.testingsystem.data.dao;

import com.epam.practice.testingsystem.data.dto.Deadline;
import com.epam.practice.testingsystem.data.dto.Test;
import com.epam.practice.testingsystem.data.dto.UniversityGroup;

import java.time.LocalDate;
import java.util.List;

public interface IDeadlineDAO {
    void setDeadline(Test test, UniversityGroup group, LocalDate date);
    Deadline getDeadline(Test test, UniversityGroup group);
    List<Deadline> getDeadlines(Test test);
    List<Deadline> getDeadlines(UniversityGroup universityGroup);
}
