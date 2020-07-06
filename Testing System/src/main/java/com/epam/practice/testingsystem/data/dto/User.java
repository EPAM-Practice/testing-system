package com.epam.practice.testingsystem.data.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private int id;

    @NonNull
    private String name;

    @NonNull
    private String passwordHash;

    private int roleId;
}
