package com.example.hospital.util;

import com.example.hospital.dto.AppointmentDto;
import com.example.hospital.dto.DoctorDto;
import com.example.hospital.dto.PatientDto;
import com.example.hospital.entity.Appointment;
import com.example.hospital.entity.Doctor;
import com.example.hospital.entity.Patient;

import java.time.LocalDateTime;

public final class EntityDtoMapper {

    private EntityDtoMapper() { /* prevent instantiation */ }

    // ---------- Patient ----------
    public static PatientDto toPatientDto(Patient p) {
        if (p == null) return null;
        return PatientDto.builder()
                .id(p.getId())
                .firstName(p.getFirstName())
                .lastName(p.getLastName())
                .email(p.getEmail())
                .phone(p.getPhone())
                .age(p.getAge())
                .gender(p.getGender())
                .build();
    }

    public static Patient toPatientEntity(PatientDto dto) {
        if (dto == null) return null;
        return Patient.builder()
                .id(dto.getId())
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .email(dto.getEmail())
                .phone(dto.getPhone())
                .age(dto.getAge())
                .gender(dto.getGender())
                .build();
    }

    // ---------- Doctor ----------
    public static DoctorDto toDoctorDto(Doctor d) {
        if (d == null) return null;
        return DoctorDto.builder()
                .id(d.getId())
                .firstName(d.getFirstName())
                .lastName(d.getLastName())
                .specialization(d.getSpecialization())
                .email(d.getEmail())
                .phone(d.getPhone())
                .build();
    }

    public static Doctor toDoctorEntity(DoctorDto dto) {
        if (dto == null) return null;
        return Doctor.builder()
                .id(dto.getId())
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .specialization(dto.getSpecialization())
                .email(dto.getEmail())
                .phone(dto.getPhone())
                .build();
    }

    // ---------- Appointment ----------
    public static AppointmentDto toAppointmentDto(Appointment a) {
        if (a == null) return null;
        return AppointmentDto.builder()
                .id(a.getId())
                .patientId(a.getPatient() != null ? a.getPatient().getId() : null)
                .doctorId(a.getDoctor() != null ? a.getDoctor().getId() : null)
                .appointmentTime(a.getAppointmentTime())
                .status(a.getStatus())
                .notes(a.getNotes())
                .build();
    }

    /**
     * Convert DTO -> Appointment entity.
     * You must supply the Patient and Doctor entities (look them up via repositories before calling).
     */
    public static Appointment toAppointmentEntity(AppointmentDto dto, Patient patient, Doctor doctor) {
        if (dto == null) return null;

        // appointmentTime fallback - keep dto time or now if null
        LocalDateTime time = dto.getAppointmentTime();

        return Appointment.builder()
                .id(dto.getId())
                .patient(patient)
                .doctor(doctor)
                .appointmentTime(time)
                .status(dto.getStatus())
                .notes(dto.getNotes())
                .build();
    }
}
