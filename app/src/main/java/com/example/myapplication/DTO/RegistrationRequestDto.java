package com.example.myapplication.DTO;

import lombok.Data;

@Data
public class RegistrationRequestDto {
    private String email;
    private String password;
    private String name;
    private String lastname;
    private String address;
    private String phonenumber;
    private String role;

    public RegistrationRequestDto(String email, String password, String name, String lastname, String address, String phonenumber, String role) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.lastname = lastname;
        this.address = address;
        this.phonenumber = phonenumber;
        this.role = role;
    }
}
