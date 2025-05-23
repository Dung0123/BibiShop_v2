package com.example.bibishop.dto;



import com.example.bibishop.entity.KichCoEntity;
import com.example.bibishop.entity.MauSacEntity;
import com.example.bibishop.entity.SanPhamEntity;
import java.time.LocalDateTime;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;

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

    private HoaDonDTO hoaDon;

    private SanPhamChiTietDTO sanPhamChiTiet;


    private BigDecimal thanhTien;

    private LocalDate createDate;


    private LocalDate updateDate;
}
