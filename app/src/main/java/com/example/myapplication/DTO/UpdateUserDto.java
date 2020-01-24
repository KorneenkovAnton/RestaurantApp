package com.example.myapplication.DTO;

import lombok.Data;

@Data
public class UpdateUserDto {
    private UserDto userDto;
    private String token;

    public UpdateUserDto(UserDto userDto, String token) {
        this.userDto = userDto;
        this.token = token;
    }

    public UpdateUserDto() {
    }

    public UpdateUserDto(String token, UserDto userDto) {
        this.token = token;
        this.userDto = userDto;
    }
}
