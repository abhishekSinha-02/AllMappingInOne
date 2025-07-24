package com.kuchBhi.KuchBhi.service;

import com.kuchBhi.KuchBhi.dto.DepartmentDto;
import com.kuchBhi.KuchBhi.dto.DepartmentResponseDto;

public interface DepartmentService {

    DepartmentResponseDto saveDepartments(DepartmentDto dto);
    String addDoctorsToDept(Long docId, Long deptId);
}
