package com.epam.practice.testingsystem.data.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UniversityGroup {
    private int id;

    @NonNull
    private String name;
}
