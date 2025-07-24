package com.kuchBhi.KuchBhi.controller;

import com.kuchBhi.KuchBhi.dto.AppointmentDto;
import com.kuchBhi.KuchBhi.dto.AppointmentResponse;
import com.kuchBhi.KuchBhi.service.AppointmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/appointment")
public class AppointmentController {

    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @PostMapping("/create")
    public ResponseEntity<AppointmentResponse> createAppointment(@RequestBody AppointmentDto dto){
        AppointmentResponse response = appointmentService.createAppointment(dto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/assign-doctor")
    public ResponseEntity<String> assignDoctor(@RequestParam Long appId, @RequestParam Long docId){
        String s = appointmentService.assignAppointmentToDoctor(appId,docId);
        return new ResponseEntity<>(s,HttpStatus.OK);
    }
}
