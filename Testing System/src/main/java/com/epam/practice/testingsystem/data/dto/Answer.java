package com.epam.practice.testingsystem.data.dto;

import lombok.Data;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Data
public class Answer {
    private int id;

    @NonNull
    private String answer;

    private Boolean isCorrect;

    public Answer() {
    }

    public Answer(int id, @NonNull String answer, Boolean isCorrect) {
        this.id = id;
        this.answer = answer;
        this.isCorrect = isCorrect;
    }
}
