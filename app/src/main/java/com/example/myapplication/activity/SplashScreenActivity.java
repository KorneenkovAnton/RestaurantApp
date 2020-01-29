package com.example.myapplication.activity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.service.DBHelper;
import com.example.myapplication.service.TokenService;


public class SplashScreenActivity extends AppCompatActivity {
    private String refreshToken;
    private TokenService tokenService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        init();

       if (isNetworkAvailable()) {
           if(!refreshToken.equals("")){
               startActivity(new Intent(this, Main2Activity.class));
               finish();
           }else {
               startActivity(new Intent(this,MainActivity.class));
               finish();
           }

        }else{
            Toast.makeText(this,"Check internet",Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    private boolean isNetworkAvailable(){
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null;
    }

    private void init(){
        DBHelper.getInstance(getApplicationContext());
        tokenService = new TokenService();
        refreshToken = tokenService.getRefreshToken();
    }
}
