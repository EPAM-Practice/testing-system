package com.epam.practice.testingsystem.data.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.time.LocalTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Attempt {
    @Builder.Default
    private int id = 0;

    @NonNull
    private User user;

    @NonNull
    private Test test;

    private int score;

    @NonNull
    private LocalTime dateTime;
}
