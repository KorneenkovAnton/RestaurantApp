package com.example.myapplication;

import com.example.myapplication.DTO.LoginRequestDto;
import com.example.myapplication.DTO.LoginResponseDto;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface JSONPlaceHolderApi {
    @POST("login")
    Call<LoginResponseDto> login(@Body LoginRequestDto requestDto);
}
