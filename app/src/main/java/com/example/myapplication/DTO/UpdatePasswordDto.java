package com.example.myapplication.DTO;

import lombok.Data;

@Data
public class UpdatePasswordDto {
    private String oldPassword;
    private String newPassword;
}
