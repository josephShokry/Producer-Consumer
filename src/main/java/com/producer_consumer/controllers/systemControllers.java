package com.producer_consumer.controllers;

import com.producer_consumer.DTOs.Dto;
import com.producer_consumer.models.Element;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
    @GetMapping("/get_changes")
    public Dto getChanges(){
        System.out.println("Get changes called");
        SimulatorService simulatorService = SimulatorService.getInstance();
        List<Dto> dtos = new ArrayList<>();
        for(Map.Entry<String, Element> entry : simulatorService.getElements().entrySet()){
            Dto dto = new Dto();
            dto.id = entry.getValue().getId();
            dto.color = entry.getValue().getColor();
            dto.numberOfProducts = entry.getValue().getProducts().size();
            dtos.add(dto);
        }
        Dto dtoo = new Dto();
        dtoo.rootGraph = dtos;
        return dtoo;
    }
    @PostMapping("/add_product")
    public void addProduct(){
        SimulatorService simulatorService = SimulatorService.getInstance();
        simulatorService.addProduct();
    }
}
