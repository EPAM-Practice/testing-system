package com.epam.practice.testingsystem.data.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Deadline {
    @NonNull
    private Test test;

    @NonNull
    private UniversityGroup universityGroup;

    private LocalDate deadline;
}
