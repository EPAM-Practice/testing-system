package com.epam.practice.testingsystem.data.dto;

import lombok.Data;
import lombok.NonNull;

@Data
public class User {
    private int id;

    @NonNull
    private String name;

    @NonNull
    private String passwordHash;

    private int roleId;

    public User() {
    }

    public User(int id, @NonNull String name, @NonNull String passwordHash, int roleId) {
        this.id = id;
        this.name = name;
        this.passwordHash = passwordHash;
        this.roleId = roleId;
    }
}
