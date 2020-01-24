package com.example.myapplication.validator;

import com.example.myapplication.DTO.UserDto;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserValidator implements Validator<UserDto> {

    public static final String NAME_REGEX = "[а-яА-ЯёЁa-zA-Z '-]+$";
    public static final String PASSWORD_REGEX = "^(?=.*\\d)(?=.*[a-z])(?!.*\\s).*$";
    public static final String EMAIL_REGEX = "([.[^@\\s]]+)@([.[^@\\s]]+)\\.([a-z]+)";
    public static final String PHONE_REGEX = "^[+]{1}[0-9]{1}[(]{0,1}[0-9]{3}[)]{0,1}[-\\s\\./0-9]{7}$" ;
    public static final String ADDRESS_REGEX = "^[a-zA-Zа-яА-Я][a-zA-Zа-яА-Я0-9]*";

    @Override
    public boolean validate(UserDto entity) {

        return validateString(entity.getEmail(),EMAIL_REGEX) && validateString(entity.getPassword(),PASSWORD_REGEX) &&
                validateString(entity.getName(),NAME_REGEX) && validateString(entity.getLastname(),NAME_REGEX) &&
                validateString(entity.getAddress(),ADDRESS_REGEX) && validateString(entity.getPhonenumber(),PHONE_REGEX);
    }

    @Override
    public boolean validateString(String string, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(string);
        return matcher.matches();
    }
}
