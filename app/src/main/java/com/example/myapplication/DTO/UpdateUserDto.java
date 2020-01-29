package com.example.myapplication.DTO;

import lombok.Data;

@Data
public class UpdateUserDto {
    private UserDto userDto;

    public UpdateUserDto() {
    }

    public UpdateUserDto(UserDto userDto) {
        this.userDto = userDto;
    }
}
