package com.example.hospital.service;

import com.example.hospital.dto.DoctorDto;

import java.util.List;

public interface DoctorService {
    DoctorDto create(DoctorDto dto);
    DoctorDto update(Long id, DoctorDto dto);
    DoctorDto getById(Long id);
    List<DoctorDto> getAll();
    void delete(Long id);
}
