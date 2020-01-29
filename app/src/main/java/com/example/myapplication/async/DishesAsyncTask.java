package com.example.myapplication.async;

import com.example.myapplication.DTO.DishDto;
import com.example.myapplication.service.NetworkService;

import java.util.List;

import lombok.SneakyThrows;
import retrofit2.Response;

public class DishesAsyncTask extends CustomAsyncTask<String,Void,List<DishDto>> {

    @SneakyThrows
    @Override
    protected AsyncTaskResult<List<DishDto>> doInBackground(String... strings) {
        String typeName= strings[0];

        Response<List<DishDto>> response = NetworkService.getInstance()
                .getJSONApi()
                .getDishesByType(typeName)
                .execute();

        return new AsyncTaskResult<>(response.body(),response.code());
    }
}
