package DTOs;

import models.Product;
import models.Queue;

public class MachineDTO {
    public int x;
    public int y;
    public String id;
    public java.util.Queue<Queue> inQueues;
    public models.Queue outQueue;
    public String color;
    public Product product;
}
