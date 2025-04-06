package com.example.bibishop.dto;

import com.example.bibishop.entity.SanPhamChiTietEntity;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SanPhamChiTietDTO extends SuperDTO {

    private BigDecimal giaSanPham;

    private Integer soLuong;

    private String trongLuong;

    private Integer gioiTinh;

    private String moTa;

    private String mauSac;

    public static SanPhamChiTietDTO toDTO(SanPhamChiTietEntity sanPhamChiTietEntity) {
        SanPhamChiTietDTO dto = new SanPhamChiTietDTO();
        dto.setId(sanPhamChiTietEntity.getId());
//        dto.setHinhAnh(sanPhamChiTietEntity.getHinhAnh().getDuongDan());
        dto.setMauSac(sanPhamChiTietEntity.getMauSac().getTen());
        dto.setGiaSanPham(sanPhamChiTietEntity.getGiaSanPham());
        dto.setKichCo(sanPhamChiTietEntity.getKichCo().getTenKichCo());
        dto.setSoLuong(sanPhamChiTietEntity.getSoLuong());
        return dto;
    }

    private String kichCo;

    private String chatLieu;

    private String hinhAnh;

    private String danhMuc;

    private int trangThai;

}
