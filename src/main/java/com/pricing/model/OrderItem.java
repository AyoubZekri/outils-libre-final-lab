package com.pricing.model;

public class OrderItem {
    private final double price;
    private final int quantity;

    public OrderItem(double price, int quantity) {
        this.price = price;
        this.quantity = quantity;
    }

    public double getSubtotal() {
        return price * quantity;
    }
}
