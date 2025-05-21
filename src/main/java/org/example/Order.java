package org.example;

public class Order {
    private String orderId;
    private String productId;
    private int quantity;
    private String status;

    public Order(String orderId, String productId, int quantity, String status) {
        this.orderId = orderId;
        this.productId = productId;
        this.quantity = quantity;
        this.status = status;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getProductId() {
        return productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String toCSV() {
        return orderId + "," + productId + "," + quantity + "," + status;
    }

    public static Order fromCSV(String line) {
        String[] parts = line.split(",");
        if (parts.length < 4) return null;
        return new Order(parts[0], parts[1], Integer.parseInt(parts[2]), parts[3]);
    }
}
