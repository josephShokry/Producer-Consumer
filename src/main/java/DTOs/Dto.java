package DTOs;

import java.util.List;

public class Dto {
    public int x;
    public int y;
    public String type;
    public String color;
    public String id;
    public String text;
    public List<String> machineToQueue;// all ids of machine that connected as input to that queue
    public List<String> QueueToMachine;// all ids of machine that connected as output to that queue
    public List<Dto> rootGraph;
}
