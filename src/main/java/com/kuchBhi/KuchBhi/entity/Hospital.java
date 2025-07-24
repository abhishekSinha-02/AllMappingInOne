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
public class Hospital {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String address;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "hospital")
    private List<Department> departments = new ArrayList<>();

    private LocalDate establishedAt = LocalDate.now();

}
