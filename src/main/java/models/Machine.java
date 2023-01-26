package models;

import DTOs.Dto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Setter
@Getter
public class Machine extends Element{
    private int operatingTime;
    private List<Queue> inQueues;
    private models.Queue outQueue;
    private Product product;

    public Machine(Dto dto, int operatingTime) {
        super(dto);
        this.operatingTime = operatingTime;
    }

    public void addToInQueues(models.Queue queue){
        inQueues.add(queue);
    }

}
