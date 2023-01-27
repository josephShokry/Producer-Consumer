package com.producer_consumer.models;

import com.producer_consumer.DTOs.Dto;
import com.producer_consumer.snapshot.CareTaker;
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
//        CareTaker.getInstance().addSnapshot();
        this.setColor("grey");
        for(Queue q: inQueues) {
            q.addFreeMachine(this.getId());
        }
    }

    public synchronized void setProduct(Product product) {
//        CareTaker.getInstance().addSnapshot();
        this.product = product;
        machineNotifyBusy();
        Thread thread = new Thread(this::run);
        thread.start();
        this.setColor(product.getColor());
//        CareTaker.getInstance().addSnapshot();
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
//    @Override
//    public Dto toDto(){
//        Dto dto = new Dto();
//        dto.id= super.getId();
//        dto.x= super.getX();
//        dto.y= super.getY();
//        dto.color= super.getColor();
//        dto.text= super.getText();
//        dto.product = this.product.getColor();
//        return dto;
//    }
}
