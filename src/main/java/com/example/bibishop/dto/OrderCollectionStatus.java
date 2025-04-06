package com.example.bibishop.dto;


import jakarta.persistence.Column;

public class OrderCollectionStatus{

    @Column
    private int newOrders;

    @Column
    private double revenue;

    @Column
    private int shipped;

    @Column
    private int returned;

    public int getNewOrders() {
        return newOrders;
    }

    public void setNewOrders(int newOrders) {
        this.newOrders = newOrders;
    }

    public double getRevenue() {
        return revenue;
    }

    public void setRevenue(double revenue) {
        this.revenue = revenue;
    }

    public int getShipped() {
        return shipped;
    }

    public void setShipped(int shipped) {
        this.shipped = shipped;
    }

    public int getReturned() {
        return returned;
    }

    public void setReturned(int returned) {
        this.returned = returned;
    }
}
