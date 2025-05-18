package com.example.bibishop.sanpham.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HoaDonDTO extends SuperDTO {

    private UserDTO user;

    private TrangThaiHoaDonDTO trangThaiHD;

    private BigDecimal tongTien;

    private LocalDate ngayThanhToan;
    private VoucherDTO vouCher;

    private int trangThai;
}
