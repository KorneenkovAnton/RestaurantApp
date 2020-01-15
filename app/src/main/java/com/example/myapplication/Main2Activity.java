package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Intent intent = getIntent();
        String accessToken = intent.getStringExtra("accessToken");
        String refreshToken = intent.getStringExtra("refreshToken");

        System.out.println("accessToken " + accessToken);
        System.out.println("refreshToken " + refreshToken);

        TextView accessView = findViewById(R.id.accessToken);
        TextView refreshView = findViewById(R.id.refreshToken);

        accessView.setText(accessToken);
        refreshView.setText(refreshToken);
    }
}
