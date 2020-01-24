package com.example.myapplication.DTO;

import lombok.Data;

@Data
public class OrderDetailsDto extends BaseEntity {
    private int num;
    private OrderDto orderDto;
    private DishDto dishDto;
}
