package models;

import java.util.Queue;

public class Machine extends Element{
    private String operatingTime;
    private Queue<models.Queue> inQueues;
    private models.Queue outQueue;
    private Product product;
}
