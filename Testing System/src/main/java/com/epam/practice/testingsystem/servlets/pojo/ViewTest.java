package com.epam.practice.testingsystem.servlets.pojo;

import com.epam.practice.testingsystem.data.dto.Test;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class ViewTest {
    private Test test;

    private LocalDate deadline;
}
