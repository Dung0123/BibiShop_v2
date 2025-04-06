package com.example.bibishop.dto;

import com.example.bibishop.entity.SanPhamChiTietEntity;
import com.example.bibishop.entity.SanPhamEntity;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SanPhamDTO extends SuperDTO{

    private String tenSanPham;

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
