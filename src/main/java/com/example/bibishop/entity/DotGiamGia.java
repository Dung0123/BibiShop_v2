package com.example.bibishop.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Table(name = "dot_giam_gia")
public class DotGiamGia extends SuperEntity {

  @Column(name = "ten", length = 255)
  private String ten;

  @Column(name = "ma", length = 255)
  private String ma;

  @Column(name = "gia_tri_giam", precision = 38, scale = 2)
  private BigDecimal giaTriGiam;

  @Column(name = "ngay_bat_dau")
  private Date ngayBatDau;

  @Column(name = "ngay_ket_thuc")
  private Date ngayKetThuc;

  @Column(name = "ngay_tao")
  private LocalDateTime ngayTao;

  @Column(name = "ngay_sua")
  private LocalDateTime ngaySua;

  @Column(name = "trang_thai")
  private Integer trangThai;

  @Column(name = "loai_voucher")
  private Integer loaiVoucher;
}