package com.kuchBhi.KuchBhi.repository;

import com.kuchBhi.KuchBhi.entity.HospitalUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HospitalUserRepo extends JpaRepository<HospitalUser,Long> {

    HospitalUser findByUsername(String username);
}
