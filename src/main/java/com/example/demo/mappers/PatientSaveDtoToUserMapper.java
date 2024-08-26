package com.example.demo.mappers;

import com.example.demo.model.*;
import com.example.demo.model.dto.PatientSaveDto;
import com.example.demo.model.dto.UserSaveDto;

import java.util.List;

public class PatientSaveDtoToUserMapper {
    public static Patient fromUserDtoToPatientEntity(PatientSaveDto patientSaveDto) {

        var address = List.of(Adresses.builder()
                .zipCode(patientSaveDto.getZipCode())
                .street(patientSaveDto.getStreet())
                .city(patientSaveDto.getCity())
                .build());

        var phones = List.of(PhoneNumber.builder()
                .prefix(patientSaveDto.getPrefix())
                .phone(patientSaveDto.getPhone()).build());

        return Patient.builder()
                .firstName(patientSaveDto.getFirstName())
                .lastName(patientSaveDto.getLastName())
                .email(patientSaveDto.getEmail())
                .role(Role.PATIENT)
                .phoneNumber(phones)
                .address(address)
                .pesel(patientSaveDto.getPesel())
                .medicalHistory(patientSaveDto.getMedicalHistory())
                .build();
    }

}
