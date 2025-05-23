package com.example.bibishop.dto;

import com.example.bibishop.entity.SanPhamChiTietEntity;
import com.example.bibishop.entity.SanPhamEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SanPhamDTO extends SuperDTO{

    private String tenSanPham;

    private int trangThai;

    private List<SanPhamChiTietDTO> productDetailDtos;

    public static SanPhamDTO toDTO(SanPhamEntity sanPhamEntity) {
        SanPhamDTO sanPhamDTO = new SanPhamDTO();
        sanPhamDTO.setId(sanPhamEntity.getId());
        sanPhamDTO.setTenSanPham(sanPhamEntity.getTenSanPham());
        List<SanPhamChiTietDTO> sanPhamChiTietList = new ArrayList<>();
        for (SanPhamChiTietEntity sanPhamChiTietEntity: sanPhamEntity.getSanPhamChiTiets()) {
            if (sanPhamChiTietEntity.getSoLuong() > 0) {
                sanPhamChiTietList.add(SanPhamChiTietDTO.toDTO(sanPhamChiTietEntity));
            }
        }
        sanPhamDTO.setProductDetailDtos(sanPhamChiTietList);
        return sanPhamDTO;
    }

}
