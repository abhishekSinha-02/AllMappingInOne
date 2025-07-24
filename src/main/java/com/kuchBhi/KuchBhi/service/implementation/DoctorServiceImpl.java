package com.kuchBhi.KuchBhi.service.implementation;

import com.kuchBhi.KuchBhi.dto.AppointmentResponse;
import com.kuchBhi.KuchBhi.dto.DoctorDto;
import com.kuchBhi.KuchBhi.dto.DoctorResponseDto;
import com.kuchBhi.KuchBhi.entity.Department;
import com.kuchBhi.KuchBhi.entity.Doctor;
import com.kuchBhi.KuchBhi.repository.DepartmentRepository;
import com.kuchBhi.KuchBhi.repository.DoctorRepository;
import com.kuchBhi.KuchBhi.service.DoctorService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorServiceImpl implements DoctorService {

    private final DoctorRepository doctorRepository;
    private final DepartmentRepository departmentRepository;

    public DoctorServiceImpl(DoctorRepository doctorRepository, DepartmentRepository departmentRepository) {
        this.doctorRepository = doctorRepository;
        this.departmentRepository = departmentRepository;
    }

    @Override
    public DoctorResponseDto saveDoctors(DoctorDto dto) {

        Doctor doctor = new Doctor();

        doctor.setName(dto.getName());
        doctor.setSpecialization(dto.getSpecialization());

        doctorRepository.save(doctor);

        return doctorToResponse(doctor);
    }

    @Override
    public List<String> getAppointmentDetailsOfDoctor(Long doctorId) {
        Doctor doctor = doctorRepository.findById(doctorId).orElseThrow(()-> new RuntimeException("Enter correct Id"));
        return doctor.getAppointment()
                .stream()
                .map(x -> x.getReason())
                .toList();
    }

    private DoctorResponseDto doctorToResponse(Doctor doctor){
        DoctorResponseDto responseDto = new DoctorResponseDto();
        responseDto.setName(doctor.getName());
        responseDto.setSpecialization(doctor.getSpecialization());
        responseDto.setJoinedAt(doctor.getJoinedAt());
        return responseDto;
    }
}
