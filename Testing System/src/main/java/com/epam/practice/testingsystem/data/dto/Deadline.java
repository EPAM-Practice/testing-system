package com.epam.practice.testingsystem.data.dto;

import lombok.Data;
import lombok.NonNull;

import java.time.LocalDate;

@Data
public class Deadline {
    @NonNull
    private Test test;

    @NonNull
    private UniversityGroup universityGroup;

    private LocalDate deadline;

    public Deadline() {
    }

    public Deadline(@NonNull Test test, @NonNull UniversityGroup universityGroup, @NonNull LocalDate deadline) {
        this.test = test;
        this.universityGroup = universityGroup;
        this.deadline = deadline;
    }
}
