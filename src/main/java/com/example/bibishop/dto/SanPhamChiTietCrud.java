package com.example.bibishop.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SanPhamChiTietCrud extends SuperDTO {
    @NotNull(message = "Giá sản phẩm không được để trống")
    private BigDecimal giaSanPham;

    private int soLuong;

    private String trongLuong;

    private int gioiTinh;

    private String moTa;

    @JsonProperty("sanPhamId")
    private UUID sanPham;
    @JsonProperty("mauSacId")
    private UUID mauSac;
    @JsonProperty("kichCoId")
    private UUID kichCo;
    @JsonProperty("nsxId")
    private UUID nsx;
    @JsonProperty("chatLieuId")
    private UUID chatLieu;
    @JsonProperty("hinhAnhId")
    private UUID hinhAnh;
    @JsonProperty("danhMucId")
    private UUID danhMuc;
    @JsonProperty("baoHanhId")
    private UUID baoHanh;
    private int trangThai;
}
