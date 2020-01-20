package com.example.myapplication.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.listeners.RegistrationListener;
import com.example.myapplication.validator.RegexInputFilter;
import com.example.myapplication.validator.RegistrationTextWathcer;
import com.example.myapplication.validator.UserValidator;
import com.google.android.material.textfield.TextInputLayout;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistrationActivity extends AppCompatActivity implements Callback<Void> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_activity);

        Button b = findViewById(R.id.register_button);

        EditText editLogin = findViewById(R.id.login);
        TextInputLayout editLoginLayout = findViewById(R.id.input_layout_login);
        editLogin.setFilters(new InputFilter[]{new RegexInputFilter(UserValidator.EMAIL_REGEX)});
        editLogin.addTextChangedListener(new RegistrationTextWathcer(editLogin,
                UserValidator.EMAIL_REGEX, editLoginLayout, this));

        EditText editPassword = findViewById(R.id.password);
        TextInputLayout editPasswordLayout = findViewById(R.id.input_layout_password);
        editPassword.setFilters(new InputFilter[]{new RegexInputFilter(UserValidator.PASSWORD_REGEX)});
        editPassword.addTextChangedListener(new RegistrationTextWathcer(editPassword,
                UserValidator.PASSWORD_REGEX, editPasswordLayout, this));

        EditText editName = findViewById(R.id.name);
        TextInputLayout editNameLayout = findViewById(R.id.input_layout_name);
        editName.setFilters(new InputFilter[]{new RegexInputFilter(UserValidator.NAME_REGEX)});
        editName.addTextChangedListener(new RegistrationTextWathcer(editName,
                UserValidator.NAME_REGEX, editNameLayout, this));

        EditText editLastName = findViewById(R.id.lastname);
        TextInputLayout editLastNameLayout = findViewById(R.id.input_layout_lastname);
        editLastName.setFilters(new InputFilter[]{new RegexInputFilter(UserValidator.NAME_REGEX)});
        editLastName.addTextChangedListener(new RegistrationTextWathcer(editLastName,
                UserValidator.NAME_REGEX, editLastNameLayout, this));

        EditText editAddress = findViewById(R.id.address);
        TextInputLayout editAddressLayout = findViewById(R.id.input_layout_address);
        editAddress.setFilters(new InputFilter[]{new RegexInputFilter(UserValidator.ADDRESS_REGEX)});
        editAddress.addTextChangedListener(new RegistrationTextWathcer(editAddress,
                UserValidator.ADDRESS_REGEX, editAddressLayout, this));

        EditText editPhone = findViewById(R.id.phonenumber);
        TextInputLayout editPhoneLayout = findViewById(R.id.input_layout_phonenumber);
        editPhone.addTextChangedListener(new RegistrationTextWathcer(editPhone,UserValidator.PHONE_REGEX,editPhoneLayout,this));

        b.setOnClickListener(new RegistrationListener(this, editLogin, editPassword, editName, editLastName, editAddress, editPhone));
    }

    @Override
    public void onResponse(Call<Void> call, Response<Void> response) {
        switch (response.code()) {
            case 200: {
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;
            }
            case 400: {
                Intent intent = new Intent(this, RegistrationActivity.class);
                startActivity(intent);
                break;
            }
        }
    }

    @Override
    public void onFailure(Call<Void> call, Throwable t) {
        Toast.makeText(getApplicationContext(), getString(R.string.err_msg_server_error),
                Toast.LENGTH_SHORT).show();
    }
}
