package com.producer_consumer.controllers;

import com.producer_consumer.DTOs.Dto;
import com.producer_consumer.models.Element;
import com.producer_consumer.models.Machine;
import com.producer_consumer.models.Product;
import com.producer_consumer.models.Queue;
import com.producer_consumer.snapshot.CareTaker;
import com.producer_consumer.snapshot.Snapshot;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Getter
@Setter
public class SimulatorService {
    private Map<String,Element> elements = new HashMap<>();
    private static SimulatorService simulatorService = null;
    private int productsNumberInStock;
    private List<Dto> rootGraph;

    public static SimulatorService getInstance(){
        if (simulatorService == null){
            simulatorService = new SimulatorService();
        }
        return simulatorService;
    }
    public void addProduct(){
        this.productsNumberInStock++;
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
                for(String machineId : dto.queueToMachine){
                    ((Machine)elements.get(machineId)).addToInQueues((Queue) elements.get(dto.id));
                    ((Queue)elements.get(dto.id)).addToOutMachines((Machine) elements.get(machineId));
                    ((Queue)elements.get(dto.id)).addToFreeMachines((Machine) elements.get(machineId));
                }
            }
        }
    }

    // start simulation
    public void runSimulation() throws InterruptedException {
        int allCounts = productsNumberInStock;
        while(true){
            int min = 1;
            int max = 10;
            int randomtime = (int)Math.floor(Math.random() *(max - min + 1) + min);
            TimeUnit.SECONDS.sleep(randomtime);
            if(productsNumberInStock > 0){
                ((Queue) elements.get("0")).addToProducts(new Product());
                productsNumberInStock--;
            }

            CareTaker.getInstance().addSnapshot();
        }

    }

    // snapshot handling

}
