package com.epam.practice.sample.data.dto;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

public class Test {
    @Getter
    @Setter
    private int id;

    @Getter
    @Setter
    @NonNull
    private String name;

    public Test() {
    }

    public Test(int id, @NonNull String name) {
        this.id = id;
        this.name = name;
    }
}
