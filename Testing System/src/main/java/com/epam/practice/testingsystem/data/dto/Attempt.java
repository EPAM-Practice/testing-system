package com.epam.practice.testingsystem.data.dto;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.Date;

public class Attempt {
    @Getter
    @Setter
    private int id;

    @Getter
    @Setter
    @NonNull
    private User user;

    @Getter
    @Setter
    @NonNull
    private Test test;

    @Getter
    @Setter
    private int score;

    @Getter
    @Setter
    @NonNull
    private Date dateTime;

    public Attempt() {
    }

    public Attempt(int id, @NonNull User user, @NonNull Test test, int score, @NonNull Date dateTime) {
        this.id = id;
        this.user = user;
        this.test = test;
        this.score = score;
        this.dateTime = dateTime;
    }
}
