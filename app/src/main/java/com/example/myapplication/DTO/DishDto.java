package com.example.myapplication.DTO;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DishDto extends BaseEntity implements Serializable {
    private String name;
    private String description;
    private Integer cost;
    private Boolean availability;
    private String imagePath;

    public DishDto(String name) {
        this.name = name;
    }
}
