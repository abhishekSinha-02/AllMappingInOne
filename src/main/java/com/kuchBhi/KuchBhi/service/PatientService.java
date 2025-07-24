package com.kuchBhi.KuchBhi.service;

import com.kuchBhi.KuchBhi.dto.PatientDto;
import com.kuchBhi.KuchBhi.dto.PatientResponse;

public interface PatientService {

    PatientResponse createPatient(PatientDto dto, Long appId);

    String deletePatient(Long id);

    PatientResponse getPatient(Long id);

    PatientResponse updatePerson(Long id, PatientDto dto);
}
