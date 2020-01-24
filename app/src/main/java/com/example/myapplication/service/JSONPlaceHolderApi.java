package com.example.myapplication.service;

import com.example.myapplication.DTO.DishDto;
import com.example.myapplication.DTO.LoginRequestDto;
import com.example.myapplication.DTO.LoginResponseDto;
import com.example.myapplication.DTO.RefreshRequestDto;
import com.example.myapplication.DTO.UserDto;
import com.example.myapplication.DTO.TypeDto;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface JSONPlaceHolderApi {
    @POST("auth/login")
    Call<LoginResponseDto> login(@Body LoginRequestDto requestDto);

    @POST("auth/register")
    Call<Void> register(@Body UserDto requestDto);

    @POST("auth/refresh")
    Call<LoginResponseDto> refresh(@Body RefreshRequestDto requestDto);

    @GET("dishType/getAll/")
    Call<List<TypeDto>> getTypes(@Header("Authorization") String token);

    @GET("user/update")
    Call<LoginResponseDto> updateUser(@Body UserDto requestDto);

    @GET("dish/getByType/{type}")
    Call<List<DishDto>> getDishesByType(@Header("Authorization") String token,@Path("type") String type);

    @GET("user/getForUpdate")
    Call<UserDto> getUserForUpdate(@Header("Authorization") String token);

    @PUT("user/update")
    Call<LoginResponseDto> updateUser(@Header("Authorization") String token,@Body UserDto userDto);
}
