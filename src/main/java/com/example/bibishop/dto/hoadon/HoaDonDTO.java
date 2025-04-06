package com.example.bibishop.dto.hoadon;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class HoaDonDTO {
    private Integer id;
    private String maHoaDon;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime ngayTao;
    private BigDecimal tongTien;
    private String trangThai;
    private List<HoaDonChiTietDTO> chiTietHoaDon;
    // getters & setters
}