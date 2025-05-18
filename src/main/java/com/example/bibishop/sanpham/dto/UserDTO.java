package com.example.bibishop.sanpham.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO extends SuperDTO{

    private String taiKhoan;

    private String ten;

    private String tenDem;

    private String ho;

    private String sdt;

    private String matKhau;

    private LocalDate ngaySinh;

    private int gioiTinh;

    private int trangThai;
    private String email;

    private VaiTroDTO vaiTro;

    private DiaChiDTO diaChi;
}
