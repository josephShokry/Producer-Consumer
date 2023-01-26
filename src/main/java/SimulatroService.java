import DTOs.Dto;
import models.Element;
import models.Machine;
import models.Queue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SimulatroService {
    private Map<String,Element> elements = new HashMap<>();
    private int productsNumberInStock;
    private List<Dto> rootGraph;
    public SimulatroService(List<Dto> rootGraph){
        this.rootGraph = rootGraph;
    }

    //build the elemnets
    public void buildElements(){
        for(Dto dto: rootGraph){
            elements.put(dto.id,objectFactory.getObject(dto));
        }
    }

    // make the relation of the graph
    public void makeRelations(){
        for(Dto dto : rootGraph){
            if(dto.type == "queue"){
                for(String machineId : dto.machineToQueue){
                    ((Machine)elements.get(machineId)).setOutQueue((Queue) elements.get(dto.id));
                }
                for(String machineId : dto.QueueToMachine){
                    ((Machine)elements.get(machineId)).addToInQueues((Queue) elements.get(dto.id));
                    ((Queue)elements.get(dto.id)).getOutMachines().push(elements.get(machineId));
                }
            }
        }
    }

    // start simulation
    public void runSimulation(){

    }
}
