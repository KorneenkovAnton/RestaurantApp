package com.example.myapplication.async;

import com.example.myapplication.DTO.OrderDto;
import com.example.myapplication.service.NetworkService;

import java.util.Collection;

import lombok.SneakyThrows;
import retrofit2.Response;

public class GetUserOrdersAsyncTask extends CustomAsyncTask<Void,Void, Collection<OrderDto>> {
    private Response<Collection<OrderDto>> result;

    @SneakyThrows
    @Override
    protected AsyncTaskResult<Collection<OrderDto>> doInBackground(Void... voids) {

            result = NetworkService.getInstance()
                    .getJSONApi()
                    .getOrdersByUser()
                    .execute();

        return new AsyncTaskResult<>(result.body(),result.code());
    }

}
