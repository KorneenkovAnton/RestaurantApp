package com.example.myapplication.DTO;

import java.io.Serializable;

import lombok.Data;

@Data
public class DishDto extends BaseEntity implements Serializable {
    private String name;
    private String description;
    private Integer cost;
    private Boolean availability;

    public DishDto(String name, String description, Integer cost, Boolean availability) {
        this.name = name;
        this.description = description;
        this.cost = cost;
        this.availability = availability;
    }

    public DishDto(String name) {
        this.name = name;
    }

    public DishDto() {
    }
}
