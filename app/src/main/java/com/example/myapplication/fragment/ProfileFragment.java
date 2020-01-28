package com.example.myapplication.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myapplication.DTO.LoginResponseDto;
import com.example.myapplication.DTO.UpdateUserDto;
import com.example.myapplication.DTO.UserDto;
import com.example.myapplication.R;
import com.example.myapplication.async.AsyncTaskResult;
import com.example.myapplication.async.UpdateUserAsyncTask;
import com.example.myapplication.service.TokenService;
import com.santalu.maskedittext.MaskEditText;

import lombok.SneakyThrows;

public class ProfileFragment extends Fragment implements View.OnClickListener {

    private UserDto userDto;
    private EditText editEmail;
    private EditText editPassword;
    private EditText editName;
    private EditText editLastName;
    private EditText editAddress;
    private MaskEditText editPhone;
    private Button updateButton;
    private String token;


    public ProfileFragment(UserDto userDto, String token) {
        this.userDto = userDto;
        this.token = token;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        init();
    }

    public void init(){
        editEmail = getView().findViewById(R.id.login);
        editEmail.setEnabled(false);
        editEmail.setText(userDto.getEmail());
        editPassword = getView().findViewById(R.id.password);
        editPassword.setText(userDto.getPassword());
        editName = getView().findViewById(R.id.name);
        editName.setText(userDto.getName());
        editLastName = getView().findViewById(R.id.lastname);
        editLastName.setText(userDto.getLastname());
        editAddress = getView().findViewById(R.id.address);
        editAddress.setText(userDto.getAddress());
        editPhone = getView().findViewById(R.id.phonenumber);
        editPhone.setText(userDto.getPhonenumber());
        updateButton = getView().findViewById(R.id.update_button);
        updateButton.setOnClickListener(this);
    }

    public void changeUser(){
        userDto.setPassword("Test1234");
        userDto.setName(editName.getText().toString());
        userDto.setLastname(editLastName.getText().toString());
        userDto.setAddress(editAddress.getText().toString());
        userDto.setPhonenumber(editPhone.getText().toString());
    }

    @SneakyThrows
    @Override
    public void onClick(View v) {
        changeUser();
        AsyncTaskResult<LoginResponseDto> result = new UpdateUserAsyncTask().execute(new UpdateUserDto(token,userDto)).get();
        LoginResponseDto loginResponseDto = result.getResult();
        token = loginResponseDto.getAccessToken();
        new TokenService().saveTokens(loginResponseDto.getRefreshToken(),loginResponseDto.getAccessToken());

        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                new HomeFragment(token)).commit();
    }
}
