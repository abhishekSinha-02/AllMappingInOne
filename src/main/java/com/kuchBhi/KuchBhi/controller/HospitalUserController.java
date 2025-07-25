package com.kuchBhi.KuchBhi.controller;

import com.kuchBhi.KuchBhi.dto.HospitalUserRequestDto;
import com.kuchBhi.KuchBhi.dto.LoginRequest;
import com.kuchBhi.KuchBhi.dto.LoginResponse;
import com.kuchBhi.KuchBhi.exception.ObjectAlreadyExistException;
import com.kuchBhi.KuchBhi.service.implementation.HospitalUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.security.auth.login.CredentialNotFoundException;

@RestController
@RequestMapping("/hospital-user")
public class HospitalUserController {

    @Autowired
    private HospitalUserServiceImpl service;

    @PostMapping("/register-users")
    public ResponseEntity<HospitalUserRequestDto> registerUsers(@RequestBody HospitalUserRequestDto userRequestDto) throws ObjectAlreadyExistException {
        HospitalUserRequestDto responseDto = service.registerUsers(userRequestDto);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @PostMapping("/login-users")
    public ResponseEntity<LoginResponse> loginUsers(@RequestBody LoginRequest loginRequest) throws CredentialNotFoundException {
        LoginResponse loginResponse = service.loginUsers(loginRequest);
        return new ResponseEntity<>(loginResponse,HttpStatus.ACCEPTED);

    }

}
