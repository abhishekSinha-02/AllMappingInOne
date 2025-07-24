package com.kuchBhi.KuchBhi.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String reason;

    @OneToOne(mappedBy = "appointment")
    private Patient patient;

    @OneToOne(mappedBy = "appointment")
    private Doctor doctor;

    private LocalDateTime createdAt = LocalDateTime.now();
}
