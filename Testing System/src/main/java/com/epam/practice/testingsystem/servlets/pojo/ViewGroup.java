package com.epam.practice.testingsystem.servlets.pojo;

import com.epam.practice.testingsystem.data.dto.UniversityGroup;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ViewGroup {
    private UniversityGroup universityGroup;

    private List<ViewTest> tests;
}
