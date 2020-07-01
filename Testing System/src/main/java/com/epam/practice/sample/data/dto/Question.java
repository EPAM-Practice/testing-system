package com.epam.practice.sample.data.dto;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

public class Question {
    @Getter
    @Setter
    private int id;

    @Getter
    @Setter
    @NonNull
    private String question;

    @Getter
    @Setter
    private int score;

    public Question() {
    }

    public Question(int id, @NonNull String question, int score) {
        this.id = id;
        this.question = question;
        this.score = score;
    }
}
