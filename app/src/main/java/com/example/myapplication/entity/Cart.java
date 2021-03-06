package com.example.myapplication.entity;

import com.example.myapplication.DTO.OrderDetailsDto;
import com.example.myapplication.DTO.TableDto;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class Cart {
    private static Cart instance;
    private List<OrderDetailsDto> dishes = new ArrayList<>();
    private TableDto table;

    public static Cart getInstance() {
        if(instance == null){
            instance = new Cart();
        }
        return instance;
    }

    private Cart() {
    }
}
