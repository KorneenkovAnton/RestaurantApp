package com.example.myapplication.DTO;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class OrderDto extends BaseEntity{
    private Date date;
    private Integer amount;
    private String status;
    private String info;
    private UserDto user;
    private TableDto table;
    private List<OrderDetailsDto> dishes;

    public OrderDto(Date date, Integer amount, String status, String info, UserDto user, TableDto table, List<OrderDetailsDto> dishes) {
        this.date = date;
        this.amount = amount;
        this.status = status;
        this.info = info;
        this.user = user;
        this.table = table;
        this.dishes = dishes;
    }

    public OrderDto(Date date) {
        this.date = date;
    }

    public OrderDto() {
    }
}
