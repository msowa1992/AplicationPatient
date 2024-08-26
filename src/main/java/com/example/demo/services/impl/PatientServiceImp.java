package com.example.demo.services.impl;

import java.util.List;
import java.util.Optional;

import com.example.demo.model.Patient;
import com.example.demo.repositories.PatientRepository;
import com.example.demo.services.PatientService;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PatientServiceImp implements PatientService {
     final PatientRepository patientRepository;


    @Override
    public List<Patient> findAllPatient() {
        return patientRepository.findAll();
    }

    @Override
    public Optional<Patient> findPatientById(Long id) {
        return patientRepository.findById(id);
    }

    @Override
    public boolean existByEmail(String email) {
        return patientRepository.existsByEmail(email);
    }

    @Override
    public boolean existByPesel(String pesel) {
        return  patientRepository.existsByPesel(pesel);
    }

    @Override
    public Optional<Patient> findPatientByEmail(String email) {
        return patientRepository.findByEmail(email);
    }

    @Override
    public void savePatient(Patient patient) {
patientRepository.save(patient);
    }

    @Override
    public void updatePatient(Long id, Patient patient) {
patient.setId(id);
patientRepository.save(patient);
    }

    @Override
    public void removePatientById(Long id) {
patientRepository.deleteById(id);
    }
}
