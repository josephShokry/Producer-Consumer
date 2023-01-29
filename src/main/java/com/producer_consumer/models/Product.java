package com.producer_consumer.models;

import lombok.Getter;

import java.util.List;

@Getter
public class Product {
    private String color;
    private static String[] colorss = {"red","green","yellow","pink","blue","#ffba00", "orange", "purple", "#224B0C" ,"#CE49BF"};
    private static int index = 0;
    public Product() {
        int min = 0;
        int max = colorss.length-1;
        int randomInd = (index++) % 10;
        this.color = colorss[randomInd];
    }
}
