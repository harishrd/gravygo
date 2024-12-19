package com.gravygo.model;

public class OrderItemDetails {

    private String menuName;
    private float price;
    private int quantity;
    private float subTotal;

    // Constructor
    public OrderItemDetails(String menuName, float price, int quantity, float subTotal) {
        this.menuName = menuName;
        this.price = price;
        this.quantity = quantity;
        this.subTotal = subTotal;
    }

    // Getters and Setters
    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(float subTotal) {
        this.subTotal = subTotal;
    }
}