package com.example.myapplication.async;

import android.os.AsyncTask;

import com.example.myapplication.DTO.TypeDto;
import com.example.myapplication.service.NetworkService;

import java.util.List;

import lombok.SneakyThrows;

public class TypesAsyncTask extends AsyncTask<String,Void, List<TypeDto>> {
    @SneakyThrows
    @Override
    protected List<TypeDto> doInBackground(String... strings) {
        String  token = strings[0];
        return NetworkService.getInstance()
                .getJSONApi()
                .getTypes("Token_" + token)
                .execute().body();
    }
}
