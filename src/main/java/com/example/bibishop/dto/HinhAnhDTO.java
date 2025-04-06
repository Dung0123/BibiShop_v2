package com.example.bibishop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HinhAnhDTO extends SuperDTO{

    private String ten;

    private String duongDan;


    private int trangThai;
}
