package com.epam.practice.testingsystem.data.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Deadline {
    @NonNull
    private Test test;

    @NonNull
    private UniversityGroup universityGroup;

    private LocalDate deadline;
}
