package models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Setter
@Getter
public class Machine extends Element{
    private String operatingTime;
    private List<models.Queue> inQueues;
    private models.Queue outQueue;
    private Product product;
    public void addToInQueues(models.Queue queue){
        inQueues.add(queue);
    }

    public void machineNotifyFree(){
        for(Queue q: inQueues) {
            q.addFreeMachine(this.getId());
        }
    }

    public void machineNotifyBusy(){
        for(Queue q: inQueues) {
            q.removeBusyMachine(this.getId());
        }
    }
}
