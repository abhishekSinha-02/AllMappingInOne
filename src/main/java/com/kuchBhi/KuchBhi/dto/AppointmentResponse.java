package com.kuchBhi.KuchBhi.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class AppointmentResponse {

    private String reason;

    private LocalDateTime createdAt;
}
