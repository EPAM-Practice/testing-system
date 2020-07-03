package com.epam.practice.testingsystem.data.dto;

import lombok.Data;
import lombok.NonNull;

@Data
public class UniversityGroup {
    private int id;

    @NonNull
    private String name;

    public UniversityGroup() {
    }

    public UniversityGroup(int id, @NonNull String name) {
        this.id = id;
        this.name = name;
    }
}
