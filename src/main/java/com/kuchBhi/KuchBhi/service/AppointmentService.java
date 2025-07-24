package com.kuchBhi.KuchBhi.service;

import com.kuchBhi.KuchBhi.dto.AppointmentDto;
import com.kuchBhi.KuchBhi.dto.AppointmentResponse;

public interface AppointmentService {

    AppointmentResponse createAppointment(AppointmentDto dto);
    String assignAppointmentToDoctor(Long appId, Long docId);
}
