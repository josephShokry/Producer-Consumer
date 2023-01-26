package controllers;

import DTOs.Dto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class systemControllers {
    @PostMapping("/start_simulate")
    public void startSimulation(@RequestBody Dto dto) throws InterruptedException {
        SimulatorFacad simulatorFacad = new SimulatorFacad(dto.rootGraph);
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
