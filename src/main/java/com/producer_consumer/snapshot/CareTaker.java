package com.producer_consumer.snapshot;

import java.util.List;

public class CareTaker {
    private static CareTaker careTaker = null;
    public static CareTaker getInstance(){
        if(careTaker == null){
            careTaker = new CareTaker();
        }
        return careTaker;
    }

    private List<Snapshot> snapshots;

    public void addSnapshot(Snapshot snapshot){
        snapshots.add(snapshot);
    }

    public Snapshot getSnapshot(int index){
        return snapshots.get(index);
    }
}
