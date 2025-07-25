package com.kuchBhi.KuchBhi.service;

import com.kuchBhi.KuchBhi.dto.HospitalUserRequestDto;
import com.kuchBhi.KuchBhi.dto.LoginRequest;
import com.kuchBhi.KuchBhi.dto.LoginResponse;
import com.kuchBhi.KuchBhi.exception.ObjectAlreadyExistException;

import javax.security.auth.login.CredentialNotFoundException;

public interface HospitalUserService {

    HospitalUserRequestDto registerUsers(HospitalUserRequestDto userRequestDto) throws ObjectAlreadyExistException;
    LoginResponse loginUsers(LoginRequest loginRequest) throws CredentialNotFoundException;
}
