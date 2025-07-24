package com.kuchBhi.KuchBhi.service;

import com.kuchBhi.KuchBhi.dto.HospitalDto;
import com.kuchBhi.KuchBhi.dto.HospitalResponseDto;

public interface HospitalService {

    HospitalResponseDto saveHospitals(HospitalDto hospitalDto);

    String saveDepartmentToHospital(Long deptId, Long hosId);

    String deleteHospitalById(Long id);
}
