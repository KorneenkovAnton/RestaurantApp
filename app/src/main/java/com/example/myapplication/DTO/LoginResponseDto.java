package com.example.myapplication.DTO;


import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class LoginResponseDto {
    @SerializedName("accessToken")
    private String accessToken;
    @SerializedName("refreshToken")
    private String refreshToken;

}
