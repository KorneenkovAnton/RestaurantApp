package com.example.myapplication.validator;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.WindowManager;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.google.android.material.textfield.TextInputLayout;

public class RegistrationTextWathcer implements TextWatcher {
    private EditText currentField;
    private String regex;
    private TextInputLayout textInputLayout;
    private AppCompatActivity activity;

    public RegistrationTextWathcer(EditText currentField, String regex, TextInputLayout textInputLayout, AppCompatActivity activity) {
        this.currentField = currentField;
        this.regex = regex;
        this.textInputLayout = textInputLayout;
        this.activity = activity;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {

        String text = s.toString();
        if(text.matches(regex)){
            textInputLayout.setErrorEnabled(false);

        }else {
            showErrorMessage();
        }
    }

    private void showErrorMessage(){
        switch (currentField.getId()){
            case R.id.login:{
                textInputLayout.setError(activity.getString(R.string.err_msg_login));
                requestFocus();
                break;
            }
            case R.id.password:{
                textInputLayout.setError(activity.getString(R.string.err_msg_password));
                requestFocus();
                break;
            }
            case R.id.name:{
                textInputLayout.setError(activity.getString(R.string.err_msg_name));
                requestFocus();
                break;
            }
            case R.id.address:{
                textInputLayout.setError(activity.getString(R.string.err_msg_address));
                requestFocus();
                break;
            }
            case R.id.phonenumber:{
                textInputLayout.setError("Wrong Phone number");
                requestFocus();
                break;
            }
            default:{
                textInputLayout.setError(activity.getString(R.string.err_msg_name));
                requestFocus();
            }
        }
    }

    private void requestFocus(){
        if(currentField.requestFocus()){
            activity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);

        }
    }
}
