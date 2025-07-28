package com.example.firstproject.dto;

import com.example.firstproject.entity.Coffee;
import lombok.AllArgsConstructor;
import lombok.ToString;

@ToString
@AllArgsConstructor
public class CoffeeDTO {
    private Long id;
    private String name;
    private Long price;

    public Coffee toEntity() {return new Coffee(id,name,price);}
}
