package com.example.demo.mappers;

import java.util.List;

import com.example.demo.model.Adresses;
import com.example.demo.model.PhoneNumber;
import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.model.dto.UserSaveDto;

public class UserSaveDtoToUserMapper {
    public static User fromUserDtoToUserEntity(UserSaveDto uSaveDto) {

        var address = List.of(Adresses.builder()
                .zipCode(uSaveDto.getZipCode())
                .street(uSaveDto.getStreet())
                .city(uSaveDto.getCity())
                .build());

        var phones = List.of(PhoneNumber.builder()
                .prefix(uSaveDto.getPrefix())
                .phone(uSaveDto.getPhone()).build());

        return User.builder()
                .firstName(uSaveDto.getFirstName())
                .lastName(uSaveDto.getLastName())
                .email(uSaveDto.getEmail())
                .password(uSaveDto.getPassword())
                .role(Role.CLIENT)
                .phoneNumber(phones)
                .address(address)
                .build();
    }

}
