package com.producer_consumer.snapshot;

import com.producer_consumer.controllers.SimulatorService;

import java.util.ArrayList;
import java.util.List;

public class CareTaker {
    private static CareTaker careTaker = null;
    private static SimulatorService simulatorService;
    public static CareTaker getInstance(){
        if(careTaker == null){
            careTaker = new CareTaker();
            simulatorService = SimulatorService.getInstance();
        }
        return careTaker;
    }

    private List<Snapshot> snapshots = new ArrayList<>();

    public void addSnapshot(){
        snapshots.add(makeSnapshot());
    }

    public Snapshot getSnapshot(int index){
        return snapshots.get(index);
    }
    public Snapshot makeSnapshot(){
//        System.out.println("##########################################################");
//        System.out.println(SimulatorService.getInstance().getElements().get("0").toString());
//        System.out.println(SimulatorService.getInstance().getElements().get("m1").toString());
//        System.out.println(SimulatorService.getInstance().getElements().get("1").toString());
//        System.out.println(SimulatorService.getInstance().getElements().get("m2").toString());
//        System.out.println(SimulatorService.getInstance().getElements().get("100").toString());
//        System.out.println("##########################################################");
        return new Snapshot(
                SimulatorService.getInstance().getElements(),
                SimulatorService.getInstance().getProductsNumberInStock(),
                SimulatorService.getInstance().getRootGraph());
    }
}
