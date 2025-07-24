package com.kuchBhi.KuchBhi.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class HospitalResponseDto {

    private String name;
    private String address;
    private LocalDate establishedAt;
}
