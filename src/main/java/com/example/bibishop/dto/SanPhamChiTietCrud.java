package com.example.bibishop.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;

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
    private Integer sanPhamId;

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

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private MultipartFile hinhAnhfile;

    @JsonProperty("baoHanhId")
    private Integer baoHanhId;

    private int trangThai;
}
