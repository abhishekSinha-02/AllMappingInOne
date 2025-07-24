package com.kuchBhi.KuchBhi.controller;

import com.kuchBhi.KuchBhi.dto.HospitalDto;
import com.kuchBhi.KuchBhi.dto.HospitalResponseDto;
import com.kuchBhi.KuchBhi.service.HospitalService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hospitals")
public class HospitalController {

    private final HospitalService hospitalService;

    public HospitalController(HospitalService hospitalService) {
        this.hospitalService = hospitalService;
    }

    @PostMapping("/save-hospitals")
    public ResponseEntity<HospitalResponseDto> saveHospitals(@RequestBody HospitalDto dto){
        HospitalResponseDto responseDto = hospitalService.saveHospitals(dto);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @GetMapping("/assign-depts")
    public ResponseEntity<String> assignDeptToHospital(@RequestParam Long deptId, @RequestParam Long hosId){
        String s = hospitalService.saveDepartmentToHospital(deptId,hosId);
        return new ResponseEntity<>(s,HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteHospital(@PathVariable Long id){
        String s = hospitalService.deleteHospitalById(id);
        return new ResponseEntity<>(s,HttpStatus.OK);
    }
}
