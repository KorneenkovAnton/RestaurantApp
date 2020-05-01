package com.example.myapplication.async;

import com.example.myapplication.DTO.TableDto;
import com.example.myapplication.service.NetworkService;

import java.net.SocketTimeoutException;
import java.util.List;

import lombok.SneakyThrows;
import retrofit2.Response;

public class GetTablesAsyncTask extends CustomAsyncTask<Void,Void, List<TableDto>> {
    @SneakyThrows
    @Override
    protected AsyncTaskResult<List<TableDto>> doInBackground(Void... voids) {
        Response<List<TableDto>> response;
        AsyncTaskResult<List<TableDto>> result;
        try {
             response = NetworkService.getInstance()
                    .getJSONApi()
                    .getTables()
                    .execute();
             result = new AsyncTaskResult<>(response.body(),response.code());
        }catch (SocketTimeoutException e){
            e.printStackTrace();
            result = new AsyncTaskResult<>(null,500,e);
        }
        return result;
    }
}
