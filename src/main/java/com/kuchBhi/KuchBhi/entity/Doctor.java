package com.kuchBhi.KuchBhi.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String name;

    private String specialization;

    private LocalDate joinedAt = LocalDate.now();

    @ManyToMany(mappedBy = "doctors")
    private List<Department> departments = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "appointment_id")
    private Appointment appointment;
}
