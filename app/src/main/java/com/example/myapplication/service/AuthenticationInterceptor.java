package com.example.myapplication.service;

import com.example.myapplication.DTO.LoginResponseDto;
import com.example.myapplication.DTO.RefreshRequestDto;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class AuthenticationInterceptor implements Interceptor {

    private String refreshToken;
    private TokenService tokenService;

    @Override
    public Response intercept(Chain chain) throws IOException {
        tokenService = new TokenService();
        Request original = chain.request();
        if(original.url().uri().getPath().equals("/resto/V1/auth/login")||
                original.url().uri().getPath().equals("/resto/V1/auth/refresh")){
            return chain.proceed(original);
        }

        Request request = original.newBuilder()
                .header("Authorization","Token_" + tokenService.getAccessToken())
                .build();

        Response response = chain.proceed(request);

        if(response.code() == 403) {

            retrofit2.Response<LoginResponseDto> responseDto = NetworkService.getInstance()
                    .getJSONApi()
                    .refresh(new RefreshRequestDto(refreshToken))
                    .execute();

            Request newReq = original.newBuilder()
                    .header("Authorization","Token_"+responseDto.body().getAccessToken())
                    .build();

            return chain.proceed(newReq);

       }
        return response;
    }

    public AuthenticationInterceptor() {
        tokenService = new TokenService();
        refreshToken = tokenService.getRefreshToken();
    }
}
