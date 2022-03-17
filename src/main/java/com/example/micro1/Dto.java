package com.example.micro1;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Dto {
    private Integer id;
    private String name;

    public Dto(String name) {
        this.name = name;
    }
}
