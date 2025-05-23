package com.example.bibishop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DiaChiDTO extends SuperDTO{
    private String diaChi;
    private String xa;

    private String huyen;

    private String tinh;


    private int trangThai;
}
