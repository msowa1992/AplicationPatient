package com.example.demo.repositories;

import com.example.demo.model.Patient;
import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PatientRepository extends JpaRepository<Patient,Long> {
    boolean existsByEmail(String email);
    boolean existsByPesel(String pesel);
    Optional<Patient> findByEmail(String email);
    Optional<Patient> findByPesel(String pesel);
}
