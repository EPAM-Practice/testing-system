package com.epam.practice.testingsystem.data.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Answer {
    @Builder.Default
    private int id = 0;

    @NonNull
    private String answer;

    private Boolean isCorrect;
}
