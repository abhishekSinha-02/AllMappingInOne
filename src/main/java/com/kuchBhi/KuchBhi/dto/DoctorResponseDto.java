package com.kuchBhi.KuchBhi.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class DoctorResponseDto {

    private String name;
    private String specialization;
    private LocalDate joinedAt = LocalDate.now();
}
