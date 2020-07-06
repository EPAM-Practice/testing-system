package com.epam.practice.testingsystem.data.dto;

import lombok.Data;
import lombok.NonNull;

import java.time.LocalTime;

@Data
public class Attempt {
    private int id;

    @NonNull
    private User user;

    @NonNull
    private Test test;

    private int score;

    @NonNull
    private LocalTime dateTime;

    public Attempt() {
    }

    public Attempt(int id, @NonNull User user, @NonNull Test test, int score, @NonNull LocalTime dateTime) {
        this.id = id;
        this.user = user;
        this.test = test;
        this.score = score;
        this.dateTime = dateTime;
    }
}
