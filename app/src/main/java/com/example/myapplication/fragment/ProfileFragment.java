package com.example.myapplication.fragment;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myapplication.DTO.LoginResponseDto;
import com.example.myapplication.DTO.UpdateUserDto;
import com.example.myapplication.DTO.UserDto;
import com.example.myapplication.R;
import com.example.myapplication.activity.MainActivity;
import com.example.myapplication.async.AsyncTaskResult;
import com.example.myapplication.async.UpdateUserAsyncTask;
import com.example.myapplication.service.TokenService;
import com.santalu.maskedittext.MaskEditText;

import lombok.SneakyThrows;

public class ProfileFragment extends Fragment implements View.OnClickListener {

    private UserDto userDto;
    private EditText editEmail;
    private EditText editName;
    private EditText editLastName;
    private EditText editAddress;
    private MaskEditText editPhone;
    private Button updateButton;
    private Dialog changePasswordDialog;
    private Button showPupUp;
    private EditText oldPassword;
    private EditText newPassword;
    private EditText new2Password;
    private Button changePassword;
    private TextView txtClose;


    public ProfileFragment(UserDto userDto) {
        this.userDto = userDto;
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
        showPupUp = getView().findViewById(R.id.showChangePopUp);
        changePasswordDialog = new Dialog(getContext());
        changePasswordDialog.setContentView(R.layout.pop_up_password);
        oldPassword = changePasswordDialog.findViewById(R.id.old_password);
        newPassword = changePasswordDialog.findViewById(R.id.new_password);
        new2Password = changePasswordDialog.findViewById(R.id.new2_password);
        changePassword = changePasswordDialog.findViewById(R.id.update_password_button);
        txtClose = changePasswordDialog.findViewById(R.id.txt_close);

        txtClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changePasswordDialog.dismiss();
            }
        });

        changePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(newPassword.getText().toString().equals(new2Password.getText().toString())){
                    //
                    // userDto.setPassword(newPassword.getText().toString());
                    Toast.makeText(getContext(),"Changed",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getContext(),"Different passwords",Toast.LENGTH_SHORT).show();
                }
            }
        });

        showPupUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changePasswordDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                changePasswordDialog.show();
            }
        });

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
        AsyncTaskResult<LoginResponseDto> result = new UpdateUserAsyncTask().execute(new UpdateUserDto(userDto)).get();
        if(result.getStatus() == 200){
            LoginResponseDto loginResponseDto = result.getResult();
            new TokenService().saveTokens(loginResponseDto.getRefreshToken(),loginResponseDto.getAccessToken());

            Toast.makeText(getContext(),"Success",Toast.LENGTH_SHORT).show();
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new HomeFragment()).commit();
        }else {
            Toast.makeText(getContext(),"Need to re-login",Toast.LENGTH_SHORT).show();
            startActivity(new Intent(getContext(), MainActivity.class));
        }
    }
}
