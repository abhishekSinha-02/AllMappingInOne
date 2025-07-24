package com.kuchBhi.KuchBhi.controller;

import com.kuchBhi.KuchBhi.dto.PatientDto;
import com.kuchBhi.KuchBhi.dto.PatientResponse;
import com.kuchBhi.KuchBhi.service.PatientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/patients")
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @PostMapping("/create")
    public ResponseEntity<PatientResponse> createPatient(@RequestBody PatientDto dto, @RequestParam Long appId) {
        PatientResponse response = patientService.createPatient(dto, appId);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deletePatient(@PathVariable Long id) {
        String s = patientService.deletePatient(id);
        return new ResponseEntity<>(s, HttpStatus.OK);
    }

    @GetMapping("/get-details/{id}")
    public ResponseEntity<PatientResponse> getPatient(@PathVariable Long id) {
        PatientResponse response = patientService.getPatient(id);
        return new ResponseEntity<>(response, HttpStatus.FOUND);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<PatientResponse> updatePerson(@PathVariable Long id, @RequestBody PatientDto dto){
        PatientResponse response = patientService.updatePerson(id,dto);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
}