package com.example.bibishop.dto;


import jakarta.persistence.Column;

public class OrderReceived {

    @Column
    private int orderReceived;

    @Column
    private String dateReceived;

    public int getOrderReceived() {
        return orderReceived;
    }

    public void setOrderReceived(int orderReceived) {
        this.orderReceived = orderReceived;
    }

    public String getDateReceived() {
        return dateReceived;
    }

    public void setDateReceived(String dateReceived) {
        this.dateReceived = dateReceived;
    }
}
