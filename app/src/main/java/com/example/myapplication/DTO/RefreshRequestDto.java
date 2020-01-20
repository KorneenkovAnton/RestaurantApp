package com.example.myapplication.DTO;

import lombok.Data;

@Data
public class RefreshRequestDto {
    private String refreshToken;

    public RefreshRequestDto(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
