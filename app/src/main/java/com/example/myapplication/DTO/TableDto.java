package com.example.myapplication.DTO;

import lombok.Data;

@Data
public class TableDto extends BaseEntity {
    private String name;
    private String status;

    private UserDto userDto;
}
