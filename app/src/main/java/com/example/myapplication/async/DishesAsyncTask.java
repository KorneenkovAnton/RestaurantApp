package com.example.myapplication.async;

import android.os.AsyncTask;

import com.example.myapplication.DTO.DishDto;
import com.example.myapplication.service.NetworkService;

import java.util.List;

import lombok.SneakyThrows;

public class DishesAsyncTask extends AsyncTask<String,Void,List<DishDto>> {
    @SneakyThrows
    @Override
    protected List<DishDto> doInBackground(String... strings) {
        String typeName= strings[1];
        String token = strings[0];

        return NetworkService.getInstance()
                .getJSONApi()
                .getDishesByType("Token_" + token,typeName)
                .execute().body();
    }
}
