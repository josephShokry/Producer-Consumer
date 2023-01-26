package models;

import DTOs.Dto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.apache.catalina.LifecycleState;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Queue extends Element{
    private List<Product> products = new ArrayList<>();
    private List<Machine> outMachines = new ArrayList<>();

    public Queue(Dto dto) {
        super(dto);
    }

    public void addToProducts(Product product){
        products.add(product);
    }
    public void addToOutMachines(Machine machine){
        outMachines.add(machine);
    }
}
