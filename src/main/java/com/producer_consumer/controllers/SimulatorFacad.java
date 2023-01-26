package com.producer_consumer.controllers;

import com.producer_consumer.DTOs.Dto;

import java.util.List;

public class SimulatorFacad {
    private SimulatorService simulatorService;
    public SimulatorFacad(List<Dto> dto , int productsNumberInStock) {
        simulatorService = new SimulatorService(dto, productsNumberInStock);
    }
    public void startSimulation() throws InterruptedException {
        simulatorService.buildElements();
        simulatorService.makeRelations();
        simulatorService.runSimulation();
    }
}
