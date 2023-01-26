package models;

import java.util.Queue;

public class Machine {
    private String id;
    private int x;
    private int y;
    private String operatingTime;
    private Queue<models.Queue> inQueues;
    private models.Queue outQueue;
    private String color;
    private Product product;
}
