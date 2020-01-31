package com.example.myapplication.async;

import com.example.myapplication.DTO.TableDto;
import com.example.myapplication.service.NetworkService;

import java.util.List;

import lombok.SneakyThrows;
import retrofit2.Response;

public class GetTablesAsyncTask extends CustomAsyncTask<Void,Void, List<TableDto>> {
    @SneakyThrows
    @Override
    protected AsyncTaskResult<List<TableDto>> doInBackground(Void... voids) {
        Response<List<TableDto>> response = NetworkService.getInstance()
                .getJSONApi()
                .getTables()
                .execute();
        return new AsyncTaskResult<>(response.body(),response.code());
    }
}
