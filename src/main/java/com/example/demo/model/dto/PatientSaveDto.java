package com.example.demo.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class PatientSaveDto {
    @NotBlank(message = "Imię pacjenta jest wymagane")
    private String firstName;
    @NotBlank(message = "Nazwisko pacjenta jest wymagane")
    private String lastName;
    @NotBlank(message = "Adres e-mail pacjenta jest wymagany")
    private String email;
    @NotBlank(message = "Kod pocztowy jest wymagany")
    private String zipCode;
    @NotBlank(message = "Nazwa ulicy jest wymagana")
    private String street;
    @NotBlank(message = "Nazwa miasta jest wymagana")
    private String city;
    @NotBlank(message = "Numer kierunkowy jest wymagany")
    private String prefix;
    @NotBlank(message = "Numer telefonu jest wymagany")
    private String phone;
    public String role;
    @NotBlank(message = "Pesel/numer dowodu osobistego jest wymagany")
    private String pesel;
    private String medicalHistory;
}
