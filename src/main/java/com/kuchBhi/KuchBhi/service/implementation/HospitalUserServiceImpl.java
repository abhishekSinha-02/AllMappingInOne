package com.kuchBhi.KuchBhi.service.implementation;

import com.kuchBhi.KuchBhi.dto.HospitalUserRequestDto;
import com.kuchBhi.KuchBhi.entity.HospitalUser;
import com.kuchBhi.KuchBhi.dto.LoginRequest;
import com.kuchBhi.KuchBhi.dto.LoginResponse;
import com.kuchBhi.KuchBhi.exception.ObjectAlreadyExistException;
import com.kuchBhi.KuchBhi.exception.ResourceNotFoundException;
import com.kuchBhi.KuchBhi.repository.HospitalUserRepo;
import com.kuchBhi.KuchBhi.security.JwtSecurityTokenUtils;
import com.kuchBhi.KuchBhi.service.HospitalUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.security.auth.login.CredentialNotFoundException;

@Service
public class HospitalUserServiceImpl implements HospitalUserService {

    @Autowired
    private HospitalUserRepo usersRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtSecurityTokenUtils jwtSecurityTokenUtils;

    @Override
    public HospitalUserRequestDto registerUsers(HospitalUserRequestDto userRequestDto) throws ObjectAlreadyExistException {

        HospitalUser existingUser = usersRepo.findByUsername(userRequestDto.getUsername());
        if (existingUser != null){
            throw new ObjectAlreadyExistException("User already exist with this username, please provide new");
        }
        HospitalUser user = new HospitalUser();

        user.setRole(userRequestDto.getRole());
        user.setUsername(userRequestDto.getUsername());
        user.setPassword(passwordEncoder.encode(userRequestDto.getPassword()));
        
        usersRepo.save(user);
        
        return userToResponse(user);
    }

    @Override
    public LoginResponse loginUsers(LoginRequest loginRequest) throws CredentialNotFoundException {

        HospitalUser users = usersRepo.findByUsername(loginRequest.getUsername());

        if(users == null){
            throw new ResourceNotFoundException("User doesn't exist, please enter correct username or register yourself");
        }
        if (passwordEncoder.matches(loginRequest.getPassword(), users.getPassword())) {
            String token = jwtSecurityTokenUtils.generateToken(loginRequest.getUsername());

            LoginResponse response = new LoginResponse();
            response.setResponseDto(userToResponse(users));
            response.setToken(token);
            return response;
        }
        else {
            throw new CredentialNotFoundException("Password incorrect, please enter valid password");
        }
    }

    public HospitalUserRequestDto userToResponse(HospitalUser users){
        HospitalUserRequestDto responseDto = new HospitalUserRequestDto();
        responseDto.setRole(users.getRole());
        responseDto.setUsername(users.getUsername());
        return responseDto;
    }
}
