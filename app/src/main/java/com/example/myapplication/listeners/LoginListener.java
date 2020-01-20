package com.example.myapplication.listeners;

import android.view.View;
import android.widget.EditText;

import com.example.myapplication.DTO.LoginRequestDto;
import com.example.myapplication.service.NetworkService;

import retrofit2.Callback;

public class LoginListener implements View.OnClickListener {


    private Callback currentActivity;
    private EditText usName;
    private EditText password;

    public LoginListener(Callback currentActivity, EditText usName, EditText password) {
        this.currentActivity = currentActivity;
        this.usName = usName;
        this.password = password;
    }

    @Override
    public void onClick(View v) {

        NetworkService.getInstance()
                .getJSONApi()
                .login(new LoginRequestDto(usName.getText().toString(),password.getText().toString()))
                .enqueue(currentActivity);
    }
}
