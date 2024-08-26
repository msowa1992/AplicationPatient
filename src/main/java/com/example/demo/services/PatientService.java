package com.example.demo.services;

import com.example.demo.exceptions.UserCanNotBeNullException;
import com.example.demo.model.Patient;
import com.example.demo.model.Role;
import com.example.demo.model.User;

import java.util.List;
import java.util.Optional;


public interface PatientService {
    List<Patient> findAllPatient();
    Optional<Patient> findPatientById(Long id);
    boolean existByEmail(String email);
    boolean existByPesel(String pesel);
    Optional<Patient> findPatientByEmail(String email);
    void savePatient(Patient patient);
    void updatePatient(Long id, Patient patient);
    void removePatientById(Long id);

}
