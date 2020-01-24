package com.example.myapplication.entity;

import com.example.myapplication.DTO.OrderDetailsDto;

import java.util.List;

import lombok.Data;

@Data
public class Cart {
    private static final Cart instance = new Cart();
    private List<OrderDetailsDto> dishes;

    public static Cart getInstance() {
        return instance;
    }

    private Cart() {
    }
}
