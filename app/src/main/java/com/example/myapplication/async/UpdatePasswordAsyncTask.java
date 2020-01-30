package com.example.myapplication.async;

import com.example.myapplication.DTO.UpdatePasswordDto;
import com.example.myapplication.service.NetworkService;

import lombok.SneakyThrows;
import retrofit2.Response;

public class UpdatePasswordAsyncTask extends CustomAsyncTask<UpdatePasswordDto,Void,UpdatePasswordDto> {
    @SneakyThrows
    @Override
    protected AsyncTaskResult<UpdatePasswordDto> doInBackground(UpdatePasswordDto... updatePasswordDtos) {
        UpdatePasswordDto updatePasswordDto = updatePasswordDtos[0];

        Response<UpdatePasswordDto> response = NetworkService.getInstance()
                .getJSONApi()
                .updatePassword(updatePasswordDto)
                .execute();

        return new AsyncTaskResult<>(response.body(),response.code());
    }
}
