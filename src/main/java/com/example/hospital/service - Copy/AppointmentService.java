package com.example.hospital.service;

import com.example.hospital.dto.AppointmentDto;

import java.util.List;

public interface AppointmentService {
    AppointmentDto create(AppointmentDto dto);
    AppointmentDto updateStatus(Long id, String status);
    List<AppointmentDto> getByDoctor(Long doctorId);
    List<AppointmentDto> getByPatient(Long patientId);
    List<AppointmentDto> getAll();
    AppointmentDto getById(Long id);
    void delete(Long id);
}
