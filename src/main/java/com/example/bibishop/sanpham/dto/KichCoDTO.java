package com.example.bibishop.sanpham.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class KichCoDTO extends SuperDTO{

    private String tenKichCo;

    private String doDai;
    private Integer trangThai;
}
