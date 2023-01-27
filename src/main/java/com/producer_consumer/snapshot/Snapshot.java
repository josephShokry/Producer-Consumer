package com.producer_consumer.snapshot;


import com.producer_consumer.DTOs.Dto;
import com.producer_consumer.models.Element;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Snapshot {
    private Map<String, Element> elements;
    private int productsNumberInStock;
    private List<Dto> rootGraph;

    public Snapshot(Map<String,Element> elements, int productsNumberInStock, List<Dto> rootGraph){
        this.elements = new HashMap<>();
        for(Map.Entry<String,Element> entry : elements.entrySet()){
            this.elements.put(entry.getKey(),entry.getValue());
        }
        this.productsNumberInStock = productsNumberInStock;
        this.rootGraph = rootGraph;
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
