package com.epam.practice.testingsystem.data.dto;

import lombok.Data;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;

@Data
public class Question {
    private int id;

    @NonNull
    private String question;

    private int score;

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
