package com.kuchBhi.KuchBhi.repository;

import com.kuchBhi.KuchBhi.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {
}