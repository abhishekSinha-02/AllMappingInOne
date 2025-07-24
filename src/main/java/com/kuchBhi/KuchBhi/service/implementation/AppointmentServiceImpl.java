package com.kuchBhi.KuchBhi.service.implementation;

import com.kuchBhi.KuchBhi.dto.AppointmentDto;
import com.kuchBhi.KuchBhi.dto.AppointmentResponse;
import com.kuchBhi.KuchBhi.entity.Appointment;
import com.kuchBhi.KuchBhi.entity.Doctor;
import com.kuchBhi.KuchBhi.repository.AppointmentRepository;
import com.kuchBhi.KuchBhi.repository.DoctorRepository;
import com.kuchBhi.KuchBhi.service.AppointmentService;
import org.springframework.stereotype.Service;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final DoctorRepository doctorRepository;

    public AppointmentServiceImpl(AppointmentRepository appointmentRepository, DoctorRepository doctorRepository) {
        this.appointmentRepository = appointmentRepository;
        this.doctorRepository = doctorRepository;
    }

    @Override
    public AppointmentResponse createAppointment(AppointmentDto dto) {

        Appointment appointment = new Appointment();

        appointment.setReason(dto.getReason());
        appointmentRepository.save(appointment);

        AppointmentResponse response = new AppointmentResponse();
        response.setReason(appointment.getReason());
        response.setCreatedAt(appointment.getCreatedAt());

        return response;
    }

    @Override
    public String assignAppointmentToDoctor(Long appId, Long docId) {
        Appointment appointment = appointmentRepository.findById(appId).orElseThrow(()-> new RuntimeException("Enter correct Id"));
        Doctor doctor = doctorRepository.findById(docId).orElseThrow(()-> new RuntimeException("Enter correct Id"));

        appointment.setDoctor(doctor);
        appointmentRepository.save(appointment);
        return "Doctor assigned successfully";
    }
}
