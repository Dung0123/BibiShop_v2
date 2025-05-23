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
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "KHACH_HANG")
public class KhachHang extends SuperEntity {

  @Column(name = "ngay_sua")
  private LocalDateTime ngaySua;

  @Column(name = "ngay_tao")
  private LocalDateTime ngayTao;

  @Column(name = "gioi_tinh")
  private String gioiTinh;

  @Column(name = "ho",  columnDefinition = "nvarchar(20)")
  private String ho;

  @Column(name = "mat_khau")
  private String matKhau;

  @Column(name = "ngay_sinh")
  private Instant ngaySinh;

  @Column(name = "sdt")
  private String sdt;

  @Column(name = "tai_khoan")
  private String taiKhoan;

  @Column(name = "ten",  columnDefinition = "nvarchar(20)")
  private String ten;

  @Column(name = "ten_dem",  columnDefinition = "nvarchar(20)")
  private String tenDem;
  private String email;
  private String password;
  @Column(name = "trang_thai")
  private Integer trangThai;

//  @OneToMany(mappedBy = "khachHang", cascade = CascadeType.ALL)
//  private Set<DiaChi> diaChi; // Changed to Set<DiaChi> to hold multiple addresses
@Column(name = "address",  columnDefinition = "nvarchar(250)")
  private String address;
  @Column(name = "vai_tro")
  private int vaiTro;

}
