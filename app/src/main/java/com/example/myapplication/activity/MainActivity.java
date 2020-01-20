package com.example.myapplication.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.InputFilter;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.DTO.LoginResponseDto;
import com.example.myapplication.R;
import com.example.myapplication.listeners.LoginListener;
import com.example.myapplication.validator.RegexInputFilter;
import com.example.myapplication.validator.RegistrationTextWathcer;
import com.example.myapplication.validator.UserValidator;
import com.google.android.material.textfield.TextInputLayout;

import java.net.SocketTimeoutException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, Callback<LoginResponseDto> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button loginButton = findViewById(R.id.login_button);
        Button registrationButton = findViewById(R.id.registration_button);
        EditText editLogin = findViewById(R.id.login);
        EditText editPassword = findViewById(R.id.password);
        TextInputLayout editLoginLayout = findViewById(R.id.input_layout_login);
        TextInputLayout editPasswordLayout = findViewById(R.id.input_layout_password);

        loginButton.setOnClickListener(new LoginListener( this, editLogin, editPassword));

        registrationButton.setOnClickListener(this);

        editLogin.setFilters(new InputFilter[]{new RegexInputFilter(UserValidator.EMAIL_REGEX)});
        editLogin.addTextChangedListener(new RegistrationTextWathcer(editLogin,
                UserValidator.EMAIL_REGEX, editLoginLayout, this));
        editPassword.setFilters(new InputFilter[]{new RegexInputFilter(UserValidator.PASSWORD_REGEX)});
        editPassword.addTextChangedListener(new RegistrationTextWathcer(editPassword,
                UserValidator.PASSWORD_REGEX, editPasswordLayout, this));
    }


    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, RegistrationActivity.class);
        startActivity(intent);
    }

    @Override
    public void onResponse(@NonNull Call<LoginResponseDto> call, @NonNull Response<LoginResponseDto> response) {
        LoginResponseDto loginResponseDto = response.body();

        if(loginResponseDto != null) {
            Intent intent = new Intent(this, Main2Activity.class);
            intent.putExtra("accessToken", loginResponseDto.getAccessToken());

            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("refreshToken", loginResponseDto.getRefreshToken());
            editor.commit();

            startActivity(intent);
            finish();
        }else {
            Toast.makeText(this.getApplicationContext(), getString(R.string.err_msg_wrong_login_or_password),
                    Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onFailure(@NonNull Call<LoginResponseDto> call,@NonNull Throwable t) {
        if(t instanceof SocketTimeoutException){
            Toast.makeText(getApplicationContext(), getString(R.string.err_msg_connection_timed_out),
                    Toast.LENGTH_SHORT).show();
        }
        t.printStackTrace();
    }
}
