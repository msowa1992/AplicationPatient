package com.example.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;


@Entity
@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    @Column(unique = true)
    private String email;
    @Enumerated(EnumType.STRING)
    private Role role = Role.PATIENT;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "patient_address_pivot", joinColumns = {
            @JoinColumn(name = "userID")
    }, inverseJoinColumns = { @JoinColumn(name = "addressID") })
    private List<Adresses> address;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinTable(name = "patient_phone_pivot", joinColumns = {
            @JoinColumn(name = "patientID")
    }, inverseJoinColumns = { @JoinColumn(name = "phoneID") })
    private List<PhoneNumber> phoneNumber;
    @Column(unique = true)
    private String pesel;
    private String medicalHistory;}