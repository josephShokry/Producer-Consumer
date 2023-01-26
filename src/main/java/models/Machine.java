package models;

import lombok.AllArgsConstructor;

import java.util.Queue;

@AllArgsConstructor
public class Machine extends Element{
    private String operatingTime;
    private Queue<models.Queue> inQueues;
    private models.Queue outQueue;
    private Product product;

}
