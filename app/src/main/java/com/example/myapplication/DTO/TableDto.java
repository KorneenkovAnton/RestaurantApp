package com.example.myapplication.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TableDto extends BaseEntity {
    private String name;
    private String status;

    private UserDto userDto;

    @Override
    public String toString() {
        return  name;
    }
}
