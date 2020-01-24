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

    private Button b;
    private EditText editLogin;
    private TextInputLayout editLoginLayout;
    private EditText editPassword;
    private TextInputLayout editPasswordLayout;
    private EditText editName;
    private TextInputLayout editNameLayout;
    private EditText editLastName;
    private TextInputLayout editLastNameLayout;
    private EditText editAddress;
    private TextInputLayout editAddressLayout;
    private EditText editPhone;
    private TextInputLayout editPhoneLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_activity);
        init();
    }

    public void init(){

        b = findViewById(R.id.register_button);

        editLogin = findViewById(R.id.login);
        editLoginLayout = findViewById(R.id.input_layout_login);
        editLogin.setFilters(new InputFilter[]{new RegexInputFilter(UserValidator.EMAIL_REGEX)});
        editLogin.addTextChangedListener(new RegistrationTextWathcer(editLogin,
                UserValidator.EMAIL_REGEX, editLoginLayout, this));

        editPassword = findViewById(R.id.password);
        editPasswordLayout = findViewById(R.id.input_layout_password);
        editPassword.setFilters(new InputFilter[]{new RegexInputFilter(UserValidator.PASSWORD_REGEX)});
        editPassword.addTextChangedListener(new RegistrationTextWathcer(editPassword,
                UserValidator.PASSWORD_REGEX, editPasswordLayout, this));

        editName = findViewById(R.id.name);
        editNameLayout = findViewById(R.id.input_layout_name);
        editName.setFilters(new InputFilter[]{new RegexInputFilter(UserValidator.NAME_REGEX)});
        editName.addTextChangedListener(new RegistrationTextWathcer(editName,
                UserValidator.NAME_REGEX, editNameLayout, this));

        editLastName = findViewById(R.id.lastname);
        editLastNameLayout = findViewById(R.id.input_layout_lastname);
        editLastName.setFilters(new InputFilter[]{new RegexInputFilter(UserValidator.NAME_REGEX)});
        editLastName.addTextChangedListener(new RegistrationTextWathcer(editLastName,
                UserValidator.NAME_REGEX, editLastNameLayout, this));

        editAddress = findViewById(R.id.address);
        editAddressLayout = findViewById(R.id.input_layout_address);
        editAddress.setFilters(new InputFilter[]{new RegexInputFilter(UserValidator.ADDRESS_REGEX)});
        editAddress.addTextChangedListener(new RegistrationTextWathcer(editAddress,
                UserValidator.ADDRESS_REGEX, editAddressLayout, this));

        editPhone = findViewById(R.id.phonenumber);
        editPhoneLayout = findViewById(R.id.input_layout_phonenumber);
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
