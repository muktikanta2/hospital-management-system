package com.example.hospital.service.impl;

import com.example.hospital.dto.PatientDto;
import com.example.hospital.entity.Patient;
import com.example.hospital.exception.ResourceNotFoundException;
import com.example.hospital.repository.PatientRepository;
import com.example.hospital.service.PatientService;
import com.example.hospital.util.EntityDtoMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PatientServiceImpl implements PatientService {
    private final PatientRepository repo;

    public PatientServiceImpl(PatientRepository repo) {
        this.repo = repo;
    }

    @Override
    public PatientDto create(PatientDto dto) {
        Patient p = EntityDtoMapper.toPatientEntity(dto);
        Patient saved = repo.save(p);
        return EntityDtoMapper.toPatientDto(saved);
    }

    @Override
    public PatientDto update(Long id, PatientDto dto) {
        Patient ex = repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Patient not found: " + id));
        ex.setFirstName(dto.getFirstName());
        ex.setLastName(dto.getLastName());
        ex.setEmail(dto.getEmail());
        ex.setPhone(dto.getPhone());
        ex.setAge(dto.getAge());
        ex.setGender(dto.getGender());
        return EntityDtoMapper.toPatientDto(repo.save(ex));
    }

    @Override
    public PatientDto getById(Long id) {
        return repo.findById(id).map(EntityDtoMapper::toPatientDto)
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found: " + id));
    }

    @Override
    public List<PatientDto> getAll() {
        return repo.findAll().stream().map(EntityDtoMapper::toPatientDto).collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Patient not found: " + id));
        repo.deleteById(id);
    }
}
