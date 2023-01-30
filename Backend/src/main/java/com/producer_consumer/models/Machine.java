package com.producer_consumer.models;

import com.producer_consumer.DTOs.Dto;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Setter
@Getter
public class Machine extends Element implements Runnable{
    private int operatingTime;
    private List<Queue> inQueues = new ArrayList<>();
    private Queue outQueue;
    private Product product = null;

    public Machine(Dto dto, int operatingTime) {
        super(dto);
        this.operatingTime = operatingTime;

    }

    public void addToInQueues(Queue queue){
        inQueues.add(queue);
    }

    public void machineNotifyFree(){
        this.setColor("#ddd");
        for(Queue q: inQueues) {
            q.addFreeMachine(this.getId());
        }
    }

    public synchronized void setProduct(Product product) {
        this.product = product;
        machineNotifyBusy();
        Thread thread = new Thread(this::run);
        thread.start();
        this.setColor(product.getColor());
    }

    public void machineNotifyBusy(){
        for(Queue q: inQueues) {
            q.removeBusyMachine(this.getId());
        }
    }

    @Override
    public void run() {
        try {
            Thread.sleep(operatingTime*1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        outQueue.addToProducts(product);
        product = null;
        machineNotifyFree();
    }

    @Override
    public String toString() {
        return "Machine{" +
                "product=" + product +
                '}';
    }
}