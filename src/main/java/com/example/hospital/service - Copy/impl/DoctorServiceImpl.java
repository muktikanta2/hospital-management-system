package com.example.hospital.service.impl;

import com.example.hospital.dto.DoctorDto;
import com.example.hospital.entity.Doctor;
import com.example.hospital.exception.ResourceNotFoundException;
import com.example.hospital.repository.DoctorRepository;
import com.example.hospital.service.DoctorService;
import com.example.hospital.util.EntityDtoMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DoctorServiceImpl implements DoctorService {
    private final DoctorRepository repo;

    public DoctorServiceImpl(DoctorRepository repo) {
        this.repo = repo;
    }

    @Override
    public DoctorDto create(DoctorDto dto) {
        Doctor d = EntityDtoMapper.toDoctorEntity(dto);
        return EntityDtoMapper.toDoctorDto(repo.save(d));
    }

    @Override
    public DoctorDto update(Long id, DoctorDto dto) {
        Doctor ex = repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Doctor not found: " + id));
        ex.setFirstName(dto.getFirstName());
        ex.setLastName(dto.getLastName());
        ex.setSpecialization(dto.getSpecialization());
        ex.setEmail(dto.getEmail());
        ex.setPhone(dto.getPhone());
        return EntityDtoMapper.toDoctorDto(repo.save(ex));
    }

    @Override
    public DoctorDto getById(Long id) {
        return repo.findById(id).map(EntityDtoMapper::toDoctorDto)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor not found: " + id));
    }

    @Override
    public List<DoctorDto> getAll() {
        return repo.findAll().stream().map(EntityDtoMapper::toDoctorDto).collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Doctor not found: " + id));
        repo.deleteById(id);
    }
}
