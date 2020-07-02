package com.epam.practice.testingsystem.data.dto;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.time.LocalDate;

public class Deadline {
    @Getter
    @Setter
    @NonNull
    private Test test;
    @Getter
    @Setter
    @NonNull
    private UniversityGroup universityGroup;

    @Getter
    @Setter
    @NonNull
    private LocalDate deadline;

    public Deadline() {
    }

    public Deadline(@NonNull Test test, @NonNull UniversityGroup universityGroup, @NonNull LocalDate deadline) {
        this.test = test;
        this.universityGroup = universityGroup;
        this.deadline = deadline;
    }
}
