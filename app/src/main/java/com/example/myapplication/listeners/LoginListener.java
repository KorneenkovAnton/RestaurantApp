package com.example.myapplication.listeners;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.DTO.LoginRequestDto;
import com.example.myapplication.DTO.LoginResponseDto;
import com.example.myapplication.Main2Activity;
import com.example.myapplication.R;
import com.example.myapplication.service.NetworkService;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.SocketTimeoutException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginListener implements View.OnClickListener {

    private AppCompatActivity currentActivity;

    public LoginListener(AppCompatActivity currentActivity) {
        this.currentActivity = currentActivity;
    }


    public LoginListener() {
    }

    @Override
    public void onClick(View v) {


        EditText editUsName = currentActivity.findViewById(R.id.name);
        EditText editPass = currentActivity.findViewById(R.id.password);

        NetworkService.getInstance()
                .getJSONApi()
                .login(new LoginRequestDto(editUsName.getText().toString(),editPass.getText().toString()))
                .enqueue(new Callback<LoginResponseDto>() {
                    @Override
                    public void onResponse(@NonNull Call<LoginResponseDto> call, @NonNull Response<LoginResponseDto> response) {
                        LoginResponseDto loginResponseDto = response.body();

                        if(loginResponseDto != null) {
                            Intent intent = new Intent(currentActivity, Main2Activity.class);
                            intent.putExtra("accessToken", loginResponseDto.getAccessToken());
                            intent.putExtra("refreshToken", loginResponseDto.getRefreshToken());
                            currentActivity.startActivity(intent);
                        }else {
                            System.out.println("Empty");
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<LoginResponseDto> call, @NonNull Throwable t) {
                        if(t instanceof SocketTimeoutException){
                            System.out.println("connection timeout");
                        }
                        System.out.println("Error");
                        t.printStackTrace();
                    }
                });
    }
}
