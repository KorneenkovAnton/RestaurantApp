package com.example.myapplication.validator;

public interface Validator<T> {

    boolean validate(T entity);

    boolean validateString(String string, String regex);
}
