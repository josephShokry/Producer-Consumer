package models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@AllArgsConstructor
@Getter
@Setter
public class Queue extends Element{
    private java.util.Queue<Product> products;
    private Map<String, Machine> outMachines;
    private Map<String, Machine> freeMachines;

    public void addFreeMachine(String machineID){
        freeMachines.put(machineID, outMachines.get(machineID));
    }
    public void removeBusyMachine(String machineID){
        freeMachines.remove(machineID, outMachines.get(machineID));
    }
}
