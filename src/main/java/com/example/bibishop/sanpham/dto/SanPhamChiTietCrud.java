package com.example.bibishop.sanpham.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SanPhamChiTietCrud extends SuperDTO {
    @NotNull(message = "Giá sản phẩm không được để trống")
    private BigDecimal giaSanPham;

    private int soLuong;

    private String trongLuong;

    private String gioiTinh;

    private String moTa;

    @JsonProperty("sanPhamId")
    private Integer sanPham;
    @JsonProperty("mauSacId")
    private Integer mauSacId;
    @JsonProperty("kichCoId")
    private Integer kichCoId;
    @JsonProperty("nsxId")
    private Integer nsxId;
    @JsonProperty("chatLieuId")
    private Integer chatLieuId;
    @JsonProperty("hinhAnhId")
    private Integer hinhAnhId;
    @JsonProperty("danhMucId")
    private Integer danhMucId;
    @JsonProperty("baoHanhId")
    private Integer baoHanhId;
    private int trangThai;
}
