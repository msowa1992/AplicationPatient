package com.example.demo.model.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class UserSaveDto {
    @NotBlank(message = "Imię jest wymagane")
    private String firstName;

    @NotBlank(message = "Nazwisko jest wymagane")
    private String lastName;

    @NotBlank(message = "Adres e-mail jest wymagany")
    private String email;

    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$", message = "Minimalna długość hasła to osiem znaków, w tym przynajmniej jedna litera, jedna cyfra i jeden znak specjalny")
    private String password;

    @NotBlank(message = "Kod pocztowy jest wymagany")
    private String zipCode;

    @NotBlank(message = "Nazwa ulicy jest wymagana")
    private String street;

    @NotBlank(message = "Nazwa miasta jest wymagana")
    private String city;

    @NotBlank(message = "Prefiks numeru telefonu jest wymagany")
    private String prefix;

    @NotBlank(message = "Numer telefonu jest wymagany")
    private String phone;

    public String role;
}
