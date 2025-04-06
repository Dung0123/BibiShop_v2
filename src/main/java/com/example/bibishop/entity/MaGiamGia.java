package com.example.bibishop.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.sql.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "ma_giam_gia")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MaGiamGia extends SuperEntity {

  @Column(name = "don_toi_thieu", precision = 38, scale = 2)
  private BigDecimal donToiThieu; // Minimum order value (for value-based)

  @Column(name = "gia_tri_giam", precision = 38, scale = 2)
  private BigDecimal giaTriGiam; // Discount value (for value-based) or percentage (for percentage-based)

  @Column(name = "giam_toi_da", precision = 38, scale = 2)
  private BigDecimal giamToiDa; // Maximum discount (for percentage-based)

  @Column(name = "loai_voucher")
  private Integer loaiVoucher; // 0 = value-based, 1 = percentage-based

  @Column(name = "ma", length = 255)
  private String ma;

  @Column(name = "so_luong")
  private Integer soLuong; // Quantity of uses

  @Column(name = "ngay_bat_dau")
  private Date ngayBatDau;

  @Column(name = "ngay_ket_thuc")
  private Date ngayKetThuc;

  @Column(name = "ngay_tao")
  private Date ngayTao;

  @Column(name = "ten", length = 255)
  private String ten;

  @Column(name = "trang_thai")
  private Integer trangThai;
}