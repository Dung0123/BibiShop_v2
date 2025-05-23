package com.example.bibishop.dto;

import com.example.bibishop.entity.SanPhamEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SanPhamDTOO {
    private UUID id;
    private String tenSanPham;
    private String tenKichCo;
    private String tenMauSac;
    private int soLuong;

    private int soLuongDaMua;
}
