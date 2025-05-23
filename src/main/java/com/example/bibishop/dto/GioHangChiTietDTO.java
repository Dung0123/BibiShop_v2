package com.example.bibishop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GioHangChiTietDTO  {

    private com.example.bibishop.dto.GioHangDTO gioHang;

    private SanPhamChiTietDTO sanPhamChiTiet;

    private int soLuong;


    private LocalDate createDate;


    private LocalDate updateDate;

    private int trangThai;

    private LocalDate modifyDate;
}
