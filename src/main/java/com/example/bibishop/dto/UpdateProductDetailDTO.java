package com.example.bibishop.dto;

import com.example.bibishop.dto.MauSacDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateProductDetailDTO {
    private UUID id ;
    private String tenSanPham;
    private MauSacDTO mauSac;
    private KichCoDTO kichCo;
    private int soLuong;
}
