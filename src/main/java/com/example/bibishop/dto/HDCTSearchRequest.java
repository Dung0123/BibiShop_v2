package com.example.bibishop.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HDCTSearchRequest {
    private String productName;
    private String color;
    private String size;
    private String categoryId;
    private Long khachHangId;
    public HDCTSearchRequest(String name, String color, String size){
        this.productName = name;
        this.color = color;
        this.size = size;
        this.khachHangId = khachHangId;
    }
}
