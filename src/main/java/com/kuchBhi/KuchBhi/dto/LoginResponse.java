package com.kuchBhi.KuchBhi.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponse {
    private HospitalUserRequestDto responseDto;
    private String token;
}
