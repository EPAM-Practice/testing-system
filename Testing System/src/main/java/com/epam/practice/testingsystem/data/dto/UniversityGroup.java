package com.epam.practice.testingsystem.data.dto;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

public class UniversityGroup {
    @Getter
    @Setter
    private int id;

    @Getter
    @Setter
    @NonNull
    private String name;

    public UniversityGroup() {
    }

    public UniversityGroup(int id, @NonNull String name) {
        this.id = id;
        this.name = name;
    }
}
