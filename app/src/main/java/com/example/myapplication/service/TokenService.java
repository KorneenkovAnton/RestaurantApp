package com.example.myapplication.service;

import com.example.myapplication.DTO.LoginResponseDto;
import com.example.myapplication.DTO.RefreshRequestDto;

import retrofit2.Callback;

public class TokenService {

    private Callback<LoginResponseDto> callback;

    public void refreshToken(String refreshToken){

        NetworkService.getInstance()
                .getJSONApi()
                .refresh(new RefreshRequestDto(refreshToken))
                .enqueue(callback);
    }

    public void setCallback(Callback<LoginResponseDto> callback) {
        this.callback = callback;
    }
}
