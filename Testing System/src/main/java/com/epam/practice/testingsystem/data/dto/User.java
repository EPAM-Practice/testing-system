package com.epam.practice.testingsystem.data.dto;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

public class User {
    @Getter
    @Setter
    private int id;

    @Getter
    @Setter
    @NonNull
    private String name;

    @Getter
    @Setter
    @NonNull
    private String passwordHash;

    @Getter
    @Setter
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
