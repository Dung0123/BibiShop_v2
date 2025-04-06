package com.example.bibishop.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class HoaDonChiTietDTO {
  private Integer id;
  private BigDecimal donGia;
  private String ghiChu;
  private LocalDateTime ngayTao;
  private LocalDateTime ngaySua;
  private String nguoiTao;
  private String nguoiSua;
  private Integer soLuong;
  private Integer trangThai;
  private Integer chiTietSanPhamId;
  private Integer sanPhamId;
  private Integer hoaDonId;
}
