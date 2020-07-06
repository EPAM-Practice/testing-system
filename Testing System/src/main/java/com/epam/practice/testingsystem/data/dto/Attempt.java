package com.epam.practice.testingsystem.data.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Attempt {
    private int id;

    @NonNull
    private User user;

    @NonNull
    private Test test;

    private int score;

    @NonNull
    private LocalTime dateTime;
}
