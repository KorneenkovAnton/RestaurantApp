package com.example.myapplication.async;

import android.os.AsyncTask;

import com.example.myapplication.DTO.LoginResponseDto;
import com.example.myapplication.DTO.UpdateUserDto;
import com.example.myapplication.service.NetworkService;

import lombok.SneakyThrows;

public class UpdateUserAsyncTask extends AsyncTask<UpdateUserDto,Void, LoginResponseDto> {

    @SneakyThrows
    @Override
    protected LoginResponseDto doInBackground(UpdateUserDto... updateUserDtos) {
        return NetworkService.getInstance()
                .getJSONApi()
                .updateUser(updateUserDtos[0].getToken(),updateUserDtos[0].getUserDto())
                .execute().body();
    }
}
