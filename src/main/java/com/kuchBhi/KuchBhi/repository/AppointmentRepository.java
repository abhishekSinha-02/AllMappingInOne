package com.kuchBhi.KuchBhi.repository;

import com.kuchBhi.KuchBhi.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
}