package controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class systemControllers {
    @PostMapping("/start_simulate")
    public void startSimulation(){

    }
    @PostMapping("/resimulate")
    public void resimulate(){

    }
    @GetMapping("/get_changes")
    public List<Object> getChanges(){

    }
}
