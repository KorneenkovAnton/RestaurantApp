package com.example.myapplication.async;

import com.example.myapplication.DTO.UserDto;
import com.example.myapplication.service.NetworkService;

import java.io.IOException;

import retrofit2.Response;

public class UserAsyncTask extends CustomAsyncTask<String,Void, UserDto> {

    @Override
    protected AsyncTaskResult<UserDto> doInBackground(String... token) {
        try {
            Response<UserDto> response = NetworkService.getInstance()
                    .getJSONApi()
                    .getUserForUpdate()
                    .execute();
            return new AsyncTaskResult<>(response.body(),response.code());
        } catch (IOException e) {
            return new AsyncTaskResult<>(e);
        }
    }


}
