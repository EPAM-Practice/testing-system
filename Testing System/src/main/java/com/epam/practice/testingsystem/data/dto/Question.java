package com.epam.practice.testingsystem.data.dto;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

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

    @Getter
    @Setter
    private List<Answer> answers;

    public Question() {
        answers = new ArrayList<>();
    }

    public Question(int id, @NonNull String question, int score, List<Answer> answers) {
        this.id = id;
        this.question = question;
        this.score = score;
        this.answers = answers;
    }
}
