package models;

import DTOs.Dto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Setter
@Getter
public class Machine extends Element implements Runnable{
    private int operatingTime;
    private List<Queue> inQueues;
    private models.Queue outQueue;
    private Product product = null;

    public Machine(Dto dto, int operatingTime) {
        super(dto);
        this.operatingTime = operatingTime;
        Thread thread = new Thread();
        thread.start();
    }

    public void addToInQueues(models.Queue queue){
        inQueues.add(queue);
    }

    public void machineNotifyFree(){
        for(Queue q: inQueues) {
            q.addFreeMachine(this.getId());
        }
    }

    public synchronized void setProduct(Product product) {
        this.product = product;
        machineNotifyBusy();
    }

    public void machineNotifyBusy(){
        for(Queue q: inQueues) {
            q.removeBusyMachine(this.getId());
        }
    }

    @Override
    public void run() {
        if(product!=null){
            try {
                Thread.sleep(operatingTime*1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            product = null;
            machineNotifyFree();
        }
    }
}
