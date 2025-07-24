package com.kuchBhi.KuchBhi.service.implementation;

import com.kuchBhi.KuchBhi.dto.DepartmentDto;
import com.kuchBhi.KuchBhi.dto.DepartmentResponseDto;
import com.kuchBhi.KuchBhi.entity.Department;
import com.kuchBhi.KuchBhi.entity.Doctor;
import com.kuchBhi.KuchBhi.repository.DepartmentRepository;
import com.kuchBhi.KuchBhi.repository.DoctorRepository;
import com.kuchBhi.KuchBhi.service.DepartmentService;
import org.springframework.stereotype.Service;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final DoctorRepository doctorRepository;

    public DepartmentServiceImpl(DepartmentRepository departmentRepository, DoctorRepository doctorRepository) {
        this.departmentRepository = departmentRepository;
        this.doctorRepository = doctorRepository;
    }

    @Override
    public DepartmentResponseDto saveDepartments(DepartmentDto dto) {

        Department dept = new Department();

        dept.setName(dto.getName());

        departmentRepository.save(dept);
        return departmentToResponse(dept);
    }

    @Override
    public String addDoctorsToDept(Long docId, Long deptId) {

        Doctor doctor = doctorRepository.findById(docId).orElseThrow(()-> new RuntimeException("Enter Valid Doctor Id"));

        Department dept = departmentRepository.findById(deptId).orElseThrow(()-> new RuntimeException("Enter valid Dept Id"));

        dept.getDoctors().add(doctor);
        departmentRepository.save(dept);

        return "Doctors added to Department Successfully";
    }

    private DepartmentResponseDto departmentToResponse(Department department){
        DepartmentResponseDto dto = new DepartmentResponseDto();
        dto.setName(department.getName());
        dto.setCreatedAt(department.getCreatedAt());
        return dto;
    }
}
