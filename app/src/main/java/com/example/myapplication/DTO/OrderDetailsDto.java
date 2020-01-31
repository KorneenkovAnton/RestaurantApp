package com.example.myapplication.DTO;

import java.io.Serializable;
import java.util.Objects;

import lombok.Data;

@Data
public class OrderDetailsDto extends BaseEntity implements Serializable {
    private Integer num;
    private OrderDto orderDto;
    private DishDto dish;

    public OrderDetailsDto(Integer num, OrderDto orderDto, DishDto dishDto) {
        this.num = num;
        this.orderDto = orderDto;
        this.dish = dishDto;
    }

    public OrderDetailsDto() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        OrderDetailsDto that = (OrderDetailsDto) o;
        return dish.equals(that.dish);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), dish);
    }
}
