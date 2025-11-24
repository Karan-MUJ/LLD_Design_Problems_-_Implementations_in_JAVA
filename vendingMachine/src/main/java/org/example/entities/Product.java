package org.example.entities;

import java.util.UUID;

public class Product extends Dimension {
    private final String id;
    private final String itemCode;
    private final String name;
    private final Integer price;

    public Product(String itemCode, String name, Integer price, Double length, Double height, Double width) {
        super(length, width, height);
        id = UUID.randomUUID().toString();
        this.itemCode = itemCode;
        this.name = name;
        this.price = price;
    }

    public Integer getPrice() { return price; }
    public String getItemCode() { return itemCode; }
    public String getName() { return name; }
    public String getId() { return id; }
}
