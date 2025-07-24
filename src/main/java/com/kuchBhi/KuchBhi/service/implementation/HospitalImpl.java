package com.kuchBhi.KuchBhi.service.implementation;

import com.kuchBhi.KuchBhi.dto.HospitalDto;
import com.kuchBhi.KuchBhi.dto.HospitalResponseDto;
import com.kuchBhi.KuchBhi.entity.Department;
import com.kuchBhi.KuchBhi.entity.Hospital;
import com.kuchBhi.KuchBhi.repository.DepartmentRepository;
import com.kuchBhi.KuchBhi.repository.HospitalRepository;
import com.kuchBhi.KuchBhi.service.HospitalService;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class HospitalImpl implements HospitalService {

    private final HospitalRepository hospitalRepository;
    private final DepartmentRepository departmentRepository;

    public HospitalImpl(HospitalRepository hospitalRepository, DepartmentRepository departmentRepository) {
        this.hospitalRepository = hospitalRepository;
        this.departmentRepository = departmentRepository;
    }

    @Override
    public HospitalResponseDto saveHospitals(HospitalDto hospitalDto) {

        Hospital hospital = new Hospital();

        hospital.setName(hospitalDto.getName());
        hospital.setAddress(hospitalDto.getAddress());

        hospitalRepository.save(hospital);

        return hospitalToDto(hospital);

    }

    @Override
    public String saveDepartmentToHospital(Long deptId, Long hosId) {
        Hospital hospital = hospitalRepository.findById(hosId).orElseThrow(()-> new RuntimeException("Enter correct hospital Id"));
        Department dept = departmentRepository.findById(deptId).orElseThrow(()-> new RuntimeException("Enter correct department Id"));

        dept.setHospital(hospital);
        departmentRepository.save(dept);

        return "Department added to hospital successfully";
    }

    @Override
    public String deleteHospitalById(Long id) {
        Hospital hospital = hospitalRepository.findById(id).orElseThrow(()-> new RuntimeException("give correct id"));
        hospitalRepository.delete(hospital);
        return "Hospital deleted Successfully";
    }

    private HospitalResponseDto hospitalToDto(Hospital hospital){
        HospitalResponseDto dto = new HospitalResponseDto();
        dto.setName(hospital.getName());
        dto.setAddress(hospital.getAddress());
        dto.setEstablishedAt(hospital.getEstablishedAt());
        return dto;
    }
}
