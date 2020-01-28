package com.example.myapplication.async;

import android.app.Activity;

import com.example.myapplication.DTO.TypeDto;
import com.example.myapplication.service.NetworkService;

import java.util.List;

import lombok.SneakyThrows;
import retrofit2.Response;

public class TypesAsyncTask extends CustomAsyncTask<String,Void, List<TypeDto>> {

    @SneakyThrows
    @Override
    protected AsyncTaskResult<List<TypeDto>> doInBackground(String... strings) {
        String  token = strings[0];
        Response<List<TypeDto>> response = NetworkService.getInstance()
                .getJSONApi()
                .getTypes()
                .execute();
        return new AsyncTaskResult<>(response.body(),response.code());
    }
}
