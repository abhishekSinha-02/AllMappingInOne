package com.kuchBhi.KuchBhi.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class DepartmentResponseDto {

    private String name;
    private LocalDate createdAt;
}
