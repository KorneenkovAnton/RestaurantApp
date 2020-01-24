package com.example.myapplication.async;

import android.os.AsyncTask;

import com.example.myapplication.DTO.UserDto;
import com.example.myapplication.service.NetworkService;

import lombok.SneakyThrows;

public class UserAsyncTask extends AsyncTask<String,Void, UserDto> {
    @SneakyThrows
    @Override
    protected UserDto doInBackground(String... token) {
        return NetworkService.getInstance()
                .getJSONApi()
                .getUserForUpdate("Token_"+token[0])
                .execute().body();
    }
}
