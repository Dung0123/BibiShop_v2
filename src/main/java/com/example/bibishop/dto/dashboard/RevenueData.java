package com.example.bibishop.dto.dashboard;

import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class RevenueData {
    private LocalDate date;
    private double revenue;

    public RevenueData(LocalDate date, double revenue) {
        this.date = date;
        this.revenue = revenue;
    }

    public LocalDate getDate() { return date; }
    public double getRevenue() { return revenue; }
}