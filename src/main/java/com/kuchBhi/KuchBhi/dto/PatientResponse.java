package com.kuchBhi.KuchBhi.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class PatientResponse {
    private String name;
    private String reason;
    private String assignedDoctor;
    private LocalDateTime visitedAt;
}
