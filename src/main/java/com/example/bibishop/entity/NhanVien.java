package com.example.bibishop.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.time.Instant;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "NHAN_VIEN")
public class NhanVien extends SuperEntity {

  @Column(name = "ngay_sua")
  private LocalDateTime ngaySua;
  @Column(name = "Ma_nhan_vien")
  private String maNhanVien;

  @Column(name = "ngay_tao")
  private LocalDateTime ngayTao;

  @Column(name = "gioi_tinh")
  private String gioiTinh;

  @Column(name = "ho")
  private String ho;

  @Column(name = "mat_khau")
  private String matKhau;

  private String passwordRaw;

  @Column(name = "ngay_sinh")
  private Instant ngaySinh;

  @Column(name = "sdt")
  private String sdt;

  @Column(name = "email")
  private String email;

  @Column(name = "tai_khoan")
  private String taiKhoan;

  @Column(name = "ten")
  private String ten;

  @Column(name = "ten_dem")
  private String tenDem;

  @Column(name = "trang_thai")
  private Integer trangThai;

//  @OneToOne
//  @JoinColumn(name = "dia_chi_id", referencedColumnName = "id")
//  private DiaChi diaChi;

  @Column(name = "vai_tro")
  private int vaiTro;

  public String getRoleAsString() {
    switch (this.vaiTro) {
      case 0: return "ROLE_ADMIN";
      case 1: return "ROLE_SELLER";
      case 2: return "ROLE_CUSTOMER";
      default: return "ROLE_CUSTOMER"; // fallback
    }
  }
}
