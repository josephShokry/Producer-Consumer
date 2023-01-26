package models;

public class Product {
    private String color;

    public Product() {
        this.color = Double.toString(Math.random() * 0x1000000);
    }
}
