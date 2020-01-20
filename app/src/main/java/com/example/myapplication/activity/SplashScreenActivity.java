package com.example.myapplication.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.DTO.LoginResponseDto;
import com.example.myapplication.R;
import com.example.myapplication.service.TokenService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SplashScreenActivity extends AppCompatActivity implements Callback<LoginResponseDto> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        String refreshToken = sharedPreferences.getString("refreshToken", null);

        TokenService tokenService = new TokenService();

        if (isNetworkAvailable()) {
            if (refreshToken != null) {
                tokenService.setCallback(this);
                tokenService.refreshToken(refreshToken);
                finish();
            } else {
                startActivity(new Intent(this, MainActivity.class));
                finish();
            }
        }else{
            Toast.makeText(this,"Check internet",Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onResponse(Call<LoginResponseDto> call, Response<LoginResponseDto> response) {

        LoginResponseDto loginResponseDto = response.body();

        if(loginResponseDto != null){
            Intent intent = new Intent(this, Main2Activity.class);
               intent.putExtra("accessToken",loginResponseDto.getAccessToken());
               SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
               SharedPreferences.Editor editor = sharedPreferences.edit();
               editor.putString("refreshToken", loginResponseDto.getRefreshToken());
               editor.commit();

               startActivity(intent);
        }else {
               Intent intent = new Intent(this, MainActivity.class);
               startActivity(intent);
           }
    }

    @Override
    public void onFailure(Call<LoginResponseDto> call, Throwable t) {
        Toast.makeText(getApplicationContext(), getString(R.string.err_msg_server_error), Toast.LENGTH_SHORT).show();
          Intent intent = new Intent(this,MainActivity.class);
           startActivity(intent);
    }

    private boolean isNetworkAvailable(){
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null;
    }
}
