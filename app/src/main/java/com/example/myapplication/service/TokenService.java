package com.example.myapplication.service;

import android.content.SharedPreferences;

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

    public void deleteTokens(SharedPreferences sharedPreferences){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove("accessToken");
        editor.remove("refreshToken");
        editor.commit();
    }

    public void writeRefreshToken(SharedPreferences sharedPreferences,String refreshToken){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("refreshToken", refreshToken);
        editor.commit();
    }

    public void setCallback(Callback<LoginResponseDto> callback) {
        this.callback = callback;
    }
}
