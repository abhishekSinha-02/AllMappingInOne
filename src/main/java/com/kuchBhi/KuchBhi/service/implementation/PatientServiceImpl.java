package com.kuchBhi.KuchBhi.service.implementation;

import com.kuchBhi.KuchBhi.dto.PatientDto;
import com.kuchBhi.KuchBhi.dto.PatientResponse;
import com.kuchBhi.KuchBhi.entity.Appointment;
import com.kuchBhi.KuchBhi.entity.Patient;
import com.kuchBhi.KuchBhi.repository.AppointmentRepository;
import com.kuchBhi.KuchBhi.repository.PatientRepository;
import com.kuchBhi.KuchBhi.service.PatientService;
import org.springframework.stereotype.Service;

@Service
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;
    private final AppointmentRepository appointmentRepository;

    public PatientServiceImpl(PatientRepository patientRepository, AppointmentRepository appointmentRepository) {
        this.patientRepository = patientRepository;
        this.appointmentRepository = appointmentRepository;
    }

    @Override
    public PatientResponse createPatient(PatientDto dto, Long appId) {
        Patient patient = new Patient();
        patient.setName(dto.getName());

        Appointment appointment = appointmentRepository.findById(appId).orElseThrow(()-> new RuntimeException("Enter correct appointment id"));

        patient.getAppointment().add(appointment);
        appointment.setPatient(patient);
        patientRepository.save(patient);
        return patientToResponse(patient);
    }

    public String addAppointment(Long patientId, Long appointmentId){
        Patient patient = patientRepository.findById(patientId).orElseThrow(()-> new RuntimeException("Enter correct patient id"));
        Appointment appointment = appointmentRepository.findById(appointmentId).orElseThrow(()-> new RuntimeException("Enter correct appointment id"));

        patient.getAppointment().add(appointment);
        appointment.setPatient(patient);

        patientRepository.save(patient);
        appointmentRepository.save(appointment);
        return "Appointment made for patient successfully";
    }
    @Override
    public String deletePatient(Long id) {
        Patient patient = patientRepository.findById(id).orElseThrow(()-> new RuntimeException("Invalid Id"));
        patientRepository.delete(patient);
        return "Patient deleted successfully";
    }

    @Override
    public PatientResponse getPatient(Long id) {
        Patient patient = patientRepository.findById(id).orElseThrow(()-> new RuntimeException("Enter correct Id"));
        return patientToResponse(patient);
    }

    @Override
    public PatientResponse updatePerson(Long id, PatientDto dto) {
        Patient patient = patientRepository.findById(id).orElseThrow(()-> new RuntimeException("Enter correct id"));
        patient.setName(dto.getName());
        patientRepository.save(patient);
        return patientToResponse(patient);
    }

    private PatientResponse patientToResponse(Patient patient){

        PatientResponse response = new PatientResponse();

        response.setName(patient.getName());

        response.setReason(patient.getAppointment()
                .stream()
                .map(x -> x.getReason())
                .toList()
                .toString());

        response.setAssignedDoctor(patient.getAppointment()
                .stream()
                .map(x -> x.getDoctor().getName())
                .toList()
                .toString());

        response.setVisitedAt(patient.getVisitedAt());
        return response;
    }
}
