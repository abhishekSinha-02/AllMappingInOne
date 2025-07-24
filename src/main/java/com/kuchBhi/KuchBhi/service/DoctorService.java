package com.kuchBhi.KuchBhi.service;

import com.kuchBhi.KuchBhi.dto.DoctorDto;
import com.kuchBhi.KuchBhi.dto.DoctorResponseDto;

public interface DoctorService {

    DoctorResponseDto saveDoctors(DoctorDto dto);
}
