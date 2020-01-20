package com.example.myapplication.listeners;

import android.view.View;
import android.widget.EditText;

import com.example.myapplication.DTO.RegistrationRequestDto;
import com.example.myapplication.service.NetworkService;

import retrofit2.Callback;

public class RegistrationListener implements View.OnClickListener {

    private Callback currentActivity;

    private EditText editUsName;
    private EditText editPass;
    private EditText editName;
    private EditText editLastName;
    private EditText editAddress;
    private EditText editPhone;

    public RegistrationListener(Callback currentActivity, EditText editUsName, EditText editPass,
                                EditText editName, EditText editLastName, EditText editAddress, EditText editPhone) {
        this.currentActivity = currentActivity;
        this.editUsName = editUsName;
        this.editPass = editPass;
        this.editName = editName;
        this.editLastName = editLastName;
        this.editAddress = editAddress;
        this.editPhone = editPhone;
    }

    @Override
    public void onClick(View v) {

        RegistrationRequestDto requestDto = new RegistrationRequestDto(editUsName.getText().toString(),editPass.getText().toString(),
                editName.getText().toString(),editLastName.getText().toString(),editAddress.getText().toString()
                ,editPhone.getText().toString(),"USER");

            NetworkService.getInstance()
                    .getJSONApi()
                    .register(requestDto)
                    .enqueue(currentActivity);
    }
}
