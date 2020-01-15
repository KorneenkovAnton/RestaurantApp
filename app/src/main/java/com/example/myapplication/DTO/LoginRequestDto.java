package com.example.myapplication.DTO;

import lombok.Data;

@Data
public class LoginRequestDto {
    final String username;
    final String password;

    public LoginRequestDto(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
