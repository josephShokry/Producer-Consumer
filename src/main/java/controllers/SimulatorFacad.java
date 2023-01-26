package controllers;

import DTOs.Dto;
import org.apache.catalina.LifecycleState;

import javax.xml.stream.events.DTD;
import java.util.List;

public class SimulatorFacad {
    private SimulatorService simulatorService;
    public SimulatorFacad(List<Dto> dto) {
        simulatorService = new SimulatorService(dto);
    }
    public void startSimulation() throws InterruptedException {
        simulatorService.buildElements();
        simulatorService.makeRelations();
        simulatorService.runSimulation();
    }
}
