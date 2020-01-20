package com.example.myapplication.service;

import com.example.myapplication.DTO.LoginRequestDto;
import com.example.myapplication.DTO.LoginResponseDto;
import com.example.myapplication.DTO.RefreshRequestDto;
import com.example.myapplication.DTO.RegistrationRequestDto;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface JSONPlaceHolderApi {
    @POST("login")
    Call<LoginResponseDto> login(@Body LoginRequestDto requestDto);

    @POST("register")
    Call<Void> register(@Body RegistrationRequestDto requestDto);

    @POST("refresh")
    Call<LoginResponseDto> refresh(@Body RefreshRequestDto requestDto);
}
