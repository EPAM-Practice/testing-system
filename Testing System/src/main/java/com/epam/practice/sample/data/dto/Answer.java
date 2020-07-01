package com.epam.practice.sample.data.dto;

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

    public Answer() {
    }

    public Answer(int id, @NonNull String answer) {
        this.id = id;
        this.answer = answer;
    }
}
