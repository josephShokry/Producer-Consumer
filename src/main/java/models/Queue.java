package models;

import DTOs.Dto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.apache.catalina.LifecycleState;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Queue extends Element implements Runnable{
    private List<Product> products;
    private Map<String, Machine> outMachines;
    private Map<String, Machine> freeMachines;
    public Queue(Dto dto) {
        super(dto);
        Thread thread = new Thread();
        thread.start();
    }
    public void addFreeMachine(String machineID){
        freeMachines.put(machineID, outMachines.get(machineID));
    }
    public void removeBusyMachine(String machineID){
        freeMachines.remove(machineID, outMachines.get(machineID));
    }


    public void addToProducts(Product product){
        products.add(product);
    }
    public void addToOutMachines(Machine machine){
        outMachines.put(machine.getId(), machine);
    }

    @Override
    public void run() {
        if(!(freeMachines.isEmpty() || products.isEmpty())){
            freeMachines.entrySet().iterator().next().getValue().setProduct(products.get(0));
            products.remove(0);
        }
    }
}
