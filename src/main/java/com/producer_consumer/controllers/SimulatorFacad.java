package com.producer_consumer.controllers;

import com.producer_consumer.DTOs.Dto;

import java.util.List;

public class SimulatorFacad {
    private SimulatorService simulatorService;
    public SimulatorFacad(List<Dto> dtos , int productsNumberInStock) {
        simulatorService = SimulatorService.getInstance();
        simulatorService.setRootGraph(dtos);
        simulatorService.setProductsNumberInStock(productsNumberInStock);
//        simulatorService = new SimulatorService(dtos, productsNumberInStock);
    }
    public void startSimulation() throws InterruptedException {
        simulatorService.buildElements();
        simulatorService.makeRelations();
        simulatorService.runSimulation();
    }

    public SimulatorService getSimulatorService() {
        return simulatorService;
    }
}
