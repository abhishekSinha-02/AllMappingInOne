package com.kuchBhi.KuchBhi.dto;

import com.kuchBhi.KuchBhi.entity.RoleType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HospitalUserRequestDto {
    private String username;
    private String password;
    private RoleType role;
}
