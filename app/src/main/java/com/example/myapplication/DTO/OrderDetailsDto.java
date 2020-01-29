package com.example.myapplication.DTO;

import lombok.Data;

@Data
public class OrderDetailsDto extends BaseEntity {
    private Integer num;
    private OrderDto orderDto;
    private DishDto dishDto;

    public OrderDetailsDto(Integer num, OrderDto orderDto, DishDto dishDto) {
        this.num = num;
        this.orderDto = orderDto;
        this.dishDto = dishDto;
    }

    public OrderDetailsDto() {
    }
}
