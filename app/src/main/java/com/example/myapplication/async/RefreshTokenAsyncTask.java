package com.example.myapplication.async;

import com.example.myapplication.DTO.LoginResponseDto;
import com.example.myapplication.DTO.RefreshRequestDto;
import com.example.myapplication.service.NetworkService;

import lombok.SneakyThrows;
import retrofit2.Response;

public class RefreshTokenAsyncTask extends CustomAsyncTask<String,Void, LoginResponseDto> {
    @SneakyThrows
    @Override
    protected AsyncTaskResult<LoginResponseDto> doInBackground(String... strings) {
        String refreshToken = strings[0];

        Response<LoginResponseDto> response = NetworkService.getInstance()
                .getJSONApi()
                .refresh(new RefreshRequestDto(refreshToken))
                .execute();

        return new AsyncTaskResult<>(response.body(),response.code());
    }
}
