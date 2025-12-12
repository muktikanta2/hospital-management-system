package com.example.hospital.service.impl;

import com.example.hospital.dto.AppointmentDto;
import com.example.hospital.entity.Appointment;
import com.example.hospital.entity.Doctor;
import com.example.hospital.entity.Patient;
import com.example.hospital.exception.ResourceNotFoundException;
import com.example.hospital.repository.AppointmentRepository;
import com.example.hospital.repository.DoctorRepository;
import com.example.hospital.repository.PatientRepository;
import com.example.hospital.service.AppointmentService;
import com.example.hospital.util.EntityDtoMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AppointmentServiceImpl implements AppointmentService {
    private final AppointmentRepository repo;
    private final PatientRepository patientRepo;
    private final DoctorRepository doctorRepo;

    public AppointmentServiceImpl(AppointmentRepository repo, PatientRepository patientRepo, DoctorRepository doctorRepo) {
        this.repo = repo;
        this.patientRepo = patientRepo;
        this.doctorRepo = doctorRepo;
    }

    @Override
    public AppointmentDto create(AppointmentDto dto) {
        Patient patient = patientRepo.findById(dto.getPatientId()).orElseThrow(() -> new ResourceNotFoundException("Patient not found: " + dto.getPatientId()));
        Doctor doctor = doctorRepo.findById(dto.getDoctorId()).orElseThrow(() -> new ResourceNotFoundException("Doctor not found: " + dto.getDoctorId()));

        Appointment ap = Appointment.builder()
                .patient(patient)
                .doctor(doctor)
                .appointmentTime(dto.getAppointmentTime())
                .notes(dto.getNotes())
                .status(dto.getStatus() == null ? "SCHEDULED" : dto.getStatus())
                .build();

        Appointment saved = repo.save(ap);
        return EntityDtoMapper.toAppointmentDto(saved);
    }

    @Override
    public AppointmentDto updateStatus(Long id, String status) {
        Appointment ap = repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Appointment not found: " + id));
        ap.setStatus(status);
        return EntityDtoMapper.toAppointmentDto(repo.save(ap));
    }

    @Override
    public List<AppointmentDto> getByDoctor(Long doctorId) {
        return repo.findByDoctorId(doctorId).stream().map(EntityDtoMapper::toAppointmentDto).collect(Collectors.toList());
    }

    @Override
    public List<AppointmentDto> getByPatient(Long patientId) {
        return repo.findByPatientId(patientId).stream().map(EntityDtoMapper::toAppointmentDto).collect(Collectors.toList());
    }

    @Override
    public List<AppointmentDto> getAll() {
        return repo.findAll().stream().map(EntityDtoMapper::toAppointmentDto).collect(Collectors.toList());
    }

    @Override
    public AppointmentDto getById(Long id) {
        return repo.findById(id).map(EntityDtoMapper::toAppointmentDto)
                .orElseThrow(() -> new ResourceNotFoundException("Appointment not found: " + id));
    }

    @Override
    public void delete(Long id) {
        repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Appointment not found: " + id));
        repo.deleteById(id);
    }
}
