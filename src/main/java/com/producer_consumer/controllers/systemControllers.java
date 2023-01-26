package com.producer_consumer.controllers;

import com.producer_consumer.DTOs.Dto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class systemControllers {
    @PostMapping("/start_simulate")
    public void startSimulation(@RequestBody Dto dto) throws InterruptedException {
        System.out.println("heloo jos");
        SimulatorFacad simulatorFacad = new SimulatorFacad(dto.rootGraph, dto.productsNumberInStock);
        simulatorFacad.startSimulation();
    }

//    @PostMapping("/resimulate")
//    public void resimulate(){
//
//    }
//    @GetMapping("/get_changes")
//    public List<Object> getChanges(){
//
//    }
}
