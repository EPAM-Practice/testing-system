package com.epam.practice.testingsystem.servlets.pojo;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ViewGroup {
    private String group;

    private List<ViewTest> tests;
}
