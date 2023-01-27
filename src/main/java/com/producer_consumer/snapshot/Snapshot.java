package com.producer_consumer.snapshot;


import com.producer_consumer.DTOs.Dto;
import com.producer_consumer.models.Element;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Snapshot {
    private Map<String, Element> elements;
    private int productsNumberInStock;
    private List<Dto> rootGraph;
    private List<Dto> dtos;
    private String elementtttttt;

    public Snapshot(Map<String,Element> elements, int productsNumberInStock, List<Dto> rootGraph){
//        this.elements = new HashMap<>();
        this.elementtttttt = elements.toString();
//        for(Map.Entry<String,Element> entry : elements.entrySet()){
//            this.elements.put(entry.getKey(),entry.getValue());
//        }
//        this.productsNumberInStock = productsNumberInStock;
//        this.rootGraph = rootGraph;
        dtos = new ArrayList<>();
        for(Map.Entry<String,Element> entry : elements.entrySet()){
            Dto dto = new Dto();
            dto.id = entry.getValue().getId();
            dto.color = entry.getValue().getColor();
            dto.numberOfProducts = entry.getValue().getProducts().size();
            dtos.add(dto);
        }

    }

    public Map<String, Element> getElements(){
        return this.elements;
    }

    public int getProductsNumberInStock(){
        return this.productsNumberInStock;
    }

    public List<Dto> getRootGraph(){
        return this.rootGraph;
    }

}
