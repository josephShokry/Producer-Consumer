package com.producer_consumer.models;

import com.producer_consumer.DTOs.Dto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class Element {
    private String id;
    private int x;
    private int y;
    private String color;
    private String text;
    private List<Product> products = new ArrayList<>();

    public Element(Dto dto) {
        this.id = dto.id;
        this.x = dto.x;
        this.y = dto.y;
        this.color = "#ddd";
        this.text = dto.text;

    }
}