package com.model;

import java.util.UUID;

public class Item {
    private String id;
    private String name;
    private String description;
    private double price;
    private int quantity;

    public Item(String name, String description, double price, int quantity) {
        this.id = UUID.randomUUID().toString(); // Generates a unique ID for each item
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
    }

    public Item(String id, String name, String description, double price, int quantity) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
