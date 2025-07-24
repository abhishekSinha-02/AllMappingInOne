package com.kuchBhi.KuchBhi.service.implementation;

import com.kuchBhi.KuchBhi.dto.DoctorDto;
import com.kuchBhi.KuchBhi.dto.DoctorResponseDto;
import com.kuchBhi.KuchBhi.entity.Doctor;
import com.kuchBhi.KuchBhi.repository.DoctorRepository;
import com.kuchBhi.KuchBhi.service.DoctorService;
import org.springframework.stereotype.Service;

@Service
public class DoctorServiceImpl implements DoctorService {

    private final DoctorRepository doctorRepository;

    public DoctorServiceImpl(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    @Override
    public DoctorResponseDto saveDoctors(DoctorDto dto) {

        Doctor doctor = new Doctor();

        doctor.setName(dto.getName());
        doctor.setSpecialization(dto.getSpecialization());

        doctorRepository.save(doctor);

        return doctorToResponse(doctor);
    }

    private DoctorResponseDto doctorToResponse(Doctor doctor){
        DoctorResponseDto responseDto = new DoctorResponseDto();
        responseDto.setName(doctor.getName());
        responseDto.setSpecialization(doctor.getSpecialization());
        responseDto.setJoinedAt(doctor.getJoinedAt());
        return responseDto;
    }
}
