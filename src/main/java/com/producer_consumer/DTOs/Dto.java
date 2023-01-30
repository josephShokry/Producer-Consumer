package com.producer_consumer.DTOs;

import java.util.List;

public class Dto {
    public int x;
    public int y;
    public int numberOfProducts;
    public int productsNumberInStock;
    public String type;
    public String color;
    public String id;
    public String text;
    public List<String> machineToQueue;// all ids of machine that connected as input to that queue
    public List<String> queueToMachine;// all ids of machine that connected as output to that queue
    public List<Dto> rootGraph;
}