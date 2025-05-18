package com.example.bibishop.sanpham.dto;


import com.example.bibishop.entity.KichCoEntity;
import com.example.bibishop.entity.MauSacEntity;
import com.example.bibishop.entity.SanPhamEntity;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HoaDonChiTietDTO {
    private HoaDonDTO hoaDon;

    private SanPhamChiTietDTO sanPhamChiTiet;

    private int soLuong;

    private BigDecimal thanhTien;

    private LocalDate createDate;


    private LocalDate updateDate;

}
