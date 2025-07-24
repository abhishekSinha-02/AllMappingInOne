package com.kuchBhi.KuchBhi.service.implementation;

import com.kuchBhi.KuchBhi.dto.DepartmentDto;
import com.kuchBhi.KuchBhi.dto.DepartmentResponseDto;
import com.kuchBhi.KuchBhi.entity.Department;
import com.kuchBhi.KuchBhi.entity.Doctor;
import com.kuchBhi.KuchBhi.entity.Patient;
import com.kuchBhi.KuchBhi.repository.DepartmentRepository;
import com.kuchBhi.KuchBhi.repository.DoctorRepository;
import com.kuchBhi.KuchBhi.service.DepartmentService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final DoctorRepository doctorRepository;

    public DepartmentServiceImpl(DepartmentRepository departmentRepository, DoctorRepository doctorRepository) {
        this.departmentRepository = departmentRepository;
        this.doctorRepository = doctorRepository;
    }

    @Override
    public DepartmentResponseDto saveDepartments(DepartmentDto dto) {

        Department dept = new Department();

        dept.setName(dto.getName());

        departmentRepository.save(dept);
        return departmentToResponse(dept);
    }

    @Override
    public String addDoctorsToDept(Long docId, Long deptId) {

        Doctor doctor = doctorRepository.findById(docId).orElseThrow(()-> new RuntimeException("Enter Valid Doctor Id"));

        Department dept = departmentRepository.findById(deptId).orElseThrow(()-> new RuntimeException("Enter valid Dept Id"));

        dept.getDoctors().add(doctor);
        departmentRepository.save(dept);

        return "Doctors added to Department Successfully";
    }

    @Override
    public String updateDepartment(Long deptId, String deptName) {
        Department department = departmentRepository.findById(deptId).orElseThrow(()-> new RuntimeException("Enter valid Dept Id"));
        department.setName(deptName);
        departmentRepository.save(department);
        return "Department Updated";
    }

    @Override
    public List<String> findAllPatientOfDoctor(Long deptId) {
        Department department = departmentRepository.findById(deptId).orElseThrow(()-> new RuntimeException("Enter correct Id"));
        List<Doctor> doctorList = doctorRepository.findAllByDepartments(department);

        List<String> patientList = new ArrayList<>();
        for (Doctor doctor : doctorList){
            String patientName = doctor.getAppointment().stream().map(x -> x.getPatient().getName()).toList().toString();
            patientList.add(patientName);
        }
        return patientList;
    }

    @Override
    public Long countAllAppointment(Long deptId){

        Department dept = departmentRepository.findById(deptId).orElseThrow(()-> new RuntimeException("Enter correct Id"));
        List<Doctor> doctorList = doctorRepository.findAllByDepartments(dept);

        long appointments = 0L;
        long totalAppointments = 0L;

        for(Doctor doctor : doctorList){
            appointments = doctor.getAppointment().size();
            totalAppointments += appointments;
        }

        return totalAppointments;
    }

    @Override
    public String departmentHavingMaxAppointment(){
        List<Department> departmentList = departmentRepository.findAll();

        Map<Long,String> longMap = new HashMap<>();
        ArrayList<Long> longArrayList = new ArrayList<>();

        for (Department department : departmentList){

            Long appointmentPerDepartment = countAllAppointment(department.getId());

            longArrayList.add(appointmentPerDepartment);

            longMap.put(appointmentPerDepartment,department.getName());
        }
        Long longValue = longArrayList.stream().max((a,b) -> Math.toIntExact(a - b)).orElse(0L);

        return longMap.get(longValue) +" : " + longValue;
    }
    private DepartmentResponseDto departmentToResponse(Department department){
        DepartmentResponseDto dto = new DepartmentResponseDto();
        dto.setName(department.getName());
        dto.setCreatedAt(department.getCreatedAt());
        return dto;
    }
}
