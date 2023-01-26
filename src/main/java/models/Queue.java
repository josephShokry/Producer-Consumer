package models;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Queue extends Element{
    private java.util.Queue<Product> products;
    private java.util.Queue<Machine> outMachines;
}
