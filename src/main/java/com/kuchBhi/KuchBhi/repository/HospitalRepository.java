package com.kuchBhi.KuchBhi.repository;

import com.kuchBhi.KuchBhi.entity.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HospitalRepository extends JpaRepository<Hospital, Long> {
}