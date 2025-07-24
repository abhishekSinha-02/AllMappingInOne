package com.kuchBhi.KuchBhi.controller;

import com.kuchBhi.KuchBhi.dto.DepartmentDto;
import com.kuchBhi.KuchBhi.dto.DepartmentResponseDto;
import com.kuchBhi.KuchBhi.repository.DepartmentRepository;
import com.kuchBhi.KuchBhi.service.DepartmentService;
import com.kuchBhi.KuchBhi.service.implementation.DepartmentServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/depts")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @PostMapping("/save-depts")
    public ResponseEntity<DepartmentResponseDto> saveDepts(@RequestBody DepartmentDto dto){
        DepartmentResponseDto responseDto = departmentService.saveDepartments(dto);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @GetMapping("/assign-doctors")
    public ResponseEntity<String> assignDoctorsTpDept(@RequestParam Long docId, @RequestParam Long deptId){
        String s = departmentService.addDoctorsToDept(docId,deptId);
        return new ResponseEntity<>(s,HttpStatus.OK);
    }
}
