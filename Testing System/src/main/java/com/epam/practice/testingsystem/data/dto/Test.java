package com.epam.practice.testingsystem.data.dto;

import lombok.Data;
import lombok.NonNull;

@Data
public class Test {
    private int id;

    @NonNull
    private String name;

    public Test() {
    }

    public Test(int id, @NonNull String name) {
        this.id = id;
        this.name = name;
    }
}
