package com.producer_consumer.models;

import com.producer_consumer.DTOs.Dto;
import lombok.Getter;
import lombok.Setter;
import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class Queue extends Element implements Runnable{
    private Map<String, Machine> outMachines = new HashMap<>();
    private Map<String, Machine> freeMachines = new HashMap<>();
    private Thread thread;
    public Queue(Dto dto) {
        super(dto);
    }
    public void addFreeMachine(String machineID){
        freeMachines.put(machineID, outMachines.get(machineID));
    }
    public void removeBusyMachine(String machineID){
        freeMachines.remove(machineID, outMachines.get(machineID));
    }


    public void addToProducts(Product product){
        super.getProducts().add(product);
        if(super.getProducts().size()==1){
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
        while(!super.getProducts().isEmpty()) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            if (!freeMachines.isEmpty()) {
                freeMachines.entrySet().iterator().next().getValue().setProduct(super.getProducts().get(0));
                super.getProducts().remove(0);
            }
        }
    }

    @Override
    public String toString() {
        return "Queue{" +
                "products=" + super.getProducts() +
                ", freeMachines=" + freeMachines +
                '}';
    }

}
