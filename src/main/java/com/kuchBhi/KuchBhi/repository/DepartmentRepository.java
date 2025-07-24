package com.kuchBhi.KuchBhi.repository;

import com.kuchBhi.KuchBhi.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
}