package com.example.bibishop.dto;

import com.example.bibishop.entity.HoaDon;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HoaDonDTO extends SuperDTO {

  private Integer id;
  private Integer khachHangId;
  private String ghiChu;
  private Integer giamGia;
  private String loaiHoaDon;
  private String hinhThucThanhToan;
  private String maHoaDon;
  private LocalDateTime ngayNhan;
  private LocalDateTime ngayNhanDuKien;
  private LocalDateTime ngayShip;
  private LocalDateTime ngaySua;
  private LocalDateTime ngayTao;
  private String diaChi;
  private String soDienThoai;
  private String tenNguoiNhan;
  private String trangThai;

  private List<HoaDonChiTietDTO> hoaDonChiTiets;

  private TrangThaiHoaDonDTO trangThaiHD;

  public static HoaDonDTO from(HoaDon hoaDon) {
    HoaDonDTO dto = new HoaDonDTO();
    dto.setId(hoaDon.getId());
    return dto;
  }
    private UserDTO user;


    private BigDecimal tongTien;

    private LocalDate ngayThanhToan;
    private VoucherDTO vouCher;

}
