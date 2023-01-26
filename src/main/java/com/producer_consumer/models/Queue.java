package com.producer_consumer.models;

import com.producer_consumer.DTOs.Dto;
import lombok.Getter;
import lombok.Setter;

import java.sql.SQLOutput;
import java.util.HashMap;
import java.util.Map;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Queue extends Element implements Runnable{
    private List<Product> products = new ArrayList<>();
    private Map<String, Machine> outMachines = new HashMap<>();
    private Map<String, Machine> freeMachines = new HashMap<>();
    private Thread thread;
    public Queue(Dto dto) {
        super(dto);
        System.out.println(thread);
    }
    public void addFreeMachine(String machineID){
        freeMachines.put(machineID, outMachines.get(machineID));
    }
    public void removeBusyMachine(String machineID){
        freeMachines.remove(machineID, outMachines.get(machineID));
    }


    public void addToProducts(Product product){
        products.add(product);
        if(products.size()==1){
            this.thread = new Thread(this::run);
            thread.start();
        }

    }
    public void addToOutMachines(Machine machine){
        outMachines.put(machine.getId(), machine);
    }
    public void addToFreeMachines(Machine machine){
        freeMachines.put(machine.getId(), machine);
    }

    @Override
    public void run() {
        System.out.println(thread + " has entered");
        while(!products.isEmpty()) {
            System.out.println(thread + " " + products.isEmpty() + " " + products.size() + " " + freeMachines.size() + " " + this.getId());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            if (!freeMachines.isEmpty()) {//!(freeMachines.isEmpty() || products.isEmpty())
                freeMachines.entrySet().iterator().next().getValue().setProduct(products.get(0));
                products.remove(0);
            }
        }
        System.out.println(thread + " has terminated");
    }
}
