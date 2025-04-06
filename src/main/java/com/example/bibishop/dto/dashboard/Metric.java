package com.example.bibishop.dto.dashboard;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Metric {
    private String label;
    private int value;
    private String iconClass;

    public Metric(String label, int value, String iconClass) {
        this.label = label;
        this.value = value;
        this.iconClass = iconClass;
    }

    // Getters
    public String getLabel() { return label; }
    public int getValue() { return value; }
    public String getIconClass() { return iconClass; }
}
