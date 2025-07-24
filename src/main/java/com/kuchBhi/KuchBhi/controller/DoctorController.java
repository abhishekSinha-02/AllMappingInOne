package com.kuchBhi.KuchBhi.controller;

import com.kuchBhi.KuchBhi.dto.DoctorDto;
import com.kuchBhi.KuchBhi.dto.DoctorResponseDto;
import com.kuchBhi.KuchBhi.service.DoctorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/doctors")
public class DoctorController {

    private final DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @PostMapping("/save-doctors")
    public ResponseEntity<DoctorResponseDto> saveDoctors(@RequestBody DoctorDto dto){
        DoctorResponseDto responseDto = doctorService.saveDoctors(dto);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }
}
