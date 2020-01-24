package com.example.myapplication.DTO;

import lombok.Data;

@Data
public class TypeDto extends BaseEntity {
    private String name;

    public TypeDto(String name) {
        this.name = name;
    }

    public TypeDto() {
    }
}
