package com.producer_consumer.controllers;

import com.producer_consumer.DTOs.Dto;
import com.producer_consumer.models.Element;
import com.producer_consumer.models.Machine;
import com.producer_consumer.models.Product;
import com.producer_consumer.models.Queue;
import com.producer_consumer.snapshot.Snapshot;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class SimulatorService {
    private Map<String,Element> elements = new HashMap<>();
    private int productsNumberInStock;
    private List<Dto> rootGraph;
    public SimulatorService(List<Dto> rootGraph, int productsNumberInStock){
        this.rootGraph = rootGraph;
        this.productsNumberInStock = productsNumberInStock;
    }

    //build the elements
    public void buildElements(){
        for(Dto dto: rootGraph){
            elements.put(dto.id, objectFactory.getObject(dto));
        }
    }

    // make the relation of the graph
    public void makeRelations(){
        for(Dto dto : rootGraph){
            if(dto.type.equals("queue")){
                for(String machineId : dto.machineToQueue){
                    ((Machine)elements.get(machineId)).setOutQueue((Queue) elements.get(dto.id));
                }
                for(String machineId : dto.QueueToMachine){
                    ((Machine)elements.get(machineId)).addToInQueues((Queue) elements.get(dto.id));
                    ((Queue)elements.get(dto.id)).addToOutMachines((Machine) elements.get(machineId));
                    ((Queue)elements.get(dto.id)).addToFreeMachines((Machine) elements.get(machineId));
                }
            }
        }
    }

    // start simulation
    public void runSimulation() throws InterruptedException {
        while(productsNumberInStock-- > 0){
            TimeUnit.SECONDS.sleep((int)(Math.random()*(10)+1));
            ((Queue)elements.get("0")).addToProducts(new Product());
        }
    }

    // snapshot handling
    public Snapshot makeSnapshot(){
        return new Snapshot(this.elements, this.productsNumberInStock, this.rootGraph);
    }
}
