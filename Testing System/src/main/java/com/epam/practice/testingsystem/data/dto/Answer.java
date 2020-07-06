package com.epam.practice.testingsystem.data.dto;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

public class Answer {

    @Getter
    @Setter
    private int id;

    @Getter
    @Setter
    @NonNull
    private String answer;

    @Getter
    @Setter
    private Boolean isCorrect;

    public Answer() {
    }

    public Answer(int id, @NonNull String answer, Boolean isCorrect) {
        this.id = id;
        this.answer = answer;
        this.isCorrect = isCorrect;
    }
}
