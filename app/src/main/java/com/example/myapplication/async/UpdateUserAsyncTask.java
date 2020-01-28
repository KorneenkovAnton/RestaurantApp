package com.example.myapplication.async;

import com.example.myapplication.DTO.LoginResponseDto;
import com.example.myapplication.DTO.UpdateUserDto;
import com.example.myapplication.service.NetworkService;

import lombok.SneakyThrows;
import retrofit2.Response;

public class UpdateUserAsyncTask extends CustomAsyncTask<UpdateUserDto,Void, LoginResponseDto> {

    @SneakyThrows
    @Override
    protected AsyncTaskResult<LoginResponseDto> doInBackground(UpdateUserDto... updateUserDtos) {

        Response<LoginResponseDto> responseDtoResponse = NetworkService.getInstance()
                .getJSONApi()
                .updateUser(updateUserDtos[0].getUserDto())
                .execute();

        return new AsyncTaskResult<>(responseDtoResponse.body(),responseDtoResponse.code());
    }
}
