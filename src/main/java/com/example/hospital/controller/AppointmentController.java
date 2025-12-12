package com.example.hospital.controller;

import com.example.hospital.dto.AppointmentDto;
import com.example.hospital.service.AppointmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {
    private final AppointmentService service;

    public AppointmentController(AppointmentService service) { this.service = service; }

    @PostMapping
    public ResponseEntity<AppointmentDto> create(@RequestBody AppointmentDto dto) {
        return ResponseEntity.ok(service.create(dto));
    }

    @GetMapping
    public ResponseEntity<List<AppointmentDto>> all() { return ResponseEntity.ok(service.getAll()); }

    @GetMapping("/{id}")
    public ResponseEntity<AppointmentDto> get(@PathVariable Long id) { return ResponseEntity.ok(service.getById(id)); }

    @GetMapping("/doctor/{doctorId}")
    public ResponseEntity<List<AppointmentDto>> byDoctor(@PathVariable Long doctorId) { return ResponseEntity.ok(service.getByDoctor(doctorId)); }

    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<AppointmentDto>> byPatient(@PathVariable Long patientId) { return ResponseEntity.ok(service.getByPatient(patientId)); }

    @PatchMapping("/{id}/status")
    public ResponseEntity<AppointmentDto> updateStatus(@PathVariable Long id, @RequestParam String status) {
        return ResponseEntity.ok(service.updateStatus(id, status));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) { service.delete(id); return ResponseEntity.noContent().build(); }
}
