package com.producer_consumer.models;

import com.producer_consumer.DTOs.Dto;
import com.producer_consumer.snapshot.CareTaker;
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
//    private List<Product> products = new ArrayList<>();
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
        System.out.println(thread + " has entered");
        while(!super.getProducts().isEmpty()) {
            System.out.println(thread + " " + super.getProducts().isEmpty() + " " + super.getProducts().size() + " " + freeMachines.size() + " " + this.getId());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            if (!freeMachines.isEmpty()) {
//                CareTaker.getInstance().addSnapshot();
                freeMachines.entrySet().iterator().next().getValue().setProduct(super.getProducts().get(0));
                super.getProducts().remove(0);
//                CareTaker.getInstance().addSnapshot();
            }
        }
        System.out.println(thread + " has terminated");
    }

    @Override
    public String toString() {
        return "Queue{" +
                "products=" + super.getProducts() +
                ", freeMachines=" + freeMachines +
                '}';
    }
//    @Override
//    public Dto toDto(){
//        Dto dto = new Dto();
//        dto.id= super.getId();
//        dto.x= super.getX();
//        dto.y= super.getY();
//        dto.color= super.getColor();
//        dto.text= super.getText();
//        dto.freeMachines = (List<Machine>) this.freeMachines.values();
//        List<String> prods = new ArrayList<>();
//        for(Product p :this.products){
//            prods.add(p.getColor());
//        }
//        dto.products = prods;
//        return dto;
//    }
}
