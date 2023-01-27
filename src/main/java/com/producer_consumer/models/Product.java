package com.producer_consumer.models;

import lombok.Getter;

import java.util.List;

@Getter
public class Product {
    private String color;
    private static String[] colorss = {"red","green","yellow","pink","blue","#ffba00"};
    public Product() {
        int min = 0;
        int max = colorss.length-1;
        int randomInd = (int)Math.floor(Math.random() *(max - min + 1) + min) % 6;
        this.color = colorss[randomInd];
    }
}
