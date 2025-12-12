package com.example.hospital.service;

import com.example.hospital.dto.PatientDto;

import java.util.List;

public interface PatientService {
    PatientDto create(PatientDto dto);
    PatientDto update(Long id, PatientDto dto);
    PatientDto getById(Long id);
    List<PatientDto> getAll();
    void delete(Long id);
}
