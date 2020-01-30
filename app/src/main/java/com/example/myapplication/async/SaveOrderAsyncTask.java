package com.example.myapplication.async;

import com.example.myapplication.DTO.OrderDto;
import com.example.myapplication.service.NetworkService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SaveOrderAsyncTask extends CustomAsyncTask<OrderDto,Void, Void> implements Callback<Void> {

    @Override
    protected AsyncTaskResult<Void> doInBackground(OrderDto... orderDtos) {
         NetworkService.getInstance()
                .getJSONApi()
                .saveOrder(orderDtos[0])
                .enqueue(this);
         return new AsyncTaskResult<>();
    }

    @Override
    public void onResponse(Call<Void> call, Response<Void> response) {

    }

    @Override
    public void onFailure(Call<Void> call, Throwable t) {

    }
}
