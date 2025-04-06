package com.example.bibishop.dto.hoadon;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class HoaDonChiTietDTO {
    private Integer soLuong;
    private BigDecimal gia;
    private BigDecimal thanhTien;
    private SanPhamDTO sanPham;
    // getters & setters
}