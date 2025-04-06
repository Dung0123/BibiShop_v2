package com.example.bibishop.dto;


import jakarta.persistence.Column;

public class ProductCategory {

    @Column
    private String categoryName;

    @Column
    private int percentage;

    @Column
    private boolean bestCategory;

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public int getPercentage() {
        return percentage;
    }

    public void setPercentage(int percentage) {
        this.percentage = percentage;
    }

    public boolean isBestCategory() {
        return bestCategory;
    }

    public void setBestCategory(boolean bestCategory) {
        this.bestCategory = bestCategory;
    }
}
