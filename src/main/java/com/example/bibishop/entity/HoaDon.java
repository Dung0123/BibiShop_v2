package com.example.bibishop.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "hoa_don")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HoaDon extends SuperEntity {

  @Column(name = "ma_hoa_don", nullable = false)
  private String maHoaDon;

  @ManyToOne
  @JoinColumn(name = "id_khach_hang", referencedColumnName = "id", nullable = true)
  public KhachHang khachHang;  // Đây là khóa ngoại, không cần thêm idKhachHang riêng

  @Column(name = "ngay_tao", nullable = false)
  private LocalDateTime ngayTao = LocalDateTime.now();

  @Column(name = "tong_tien", nullable = false)
  private BigDecimal tongTien;

  @ManyToOne
  @JoinColumn(name = "id_ma_giam_gia")
  private MaGiamGia maGiamGia; // Có thể null

  @Column(name = "giam_gia", nullable = false)
  private BigDecimal giamGia;

  @Column(name = "trang_thai", nullable = false)
  private String trangThai;

  @Column(name = "ten_khachhang")
  private String tenKhachhang;

  @Column(name = "sdt_khachhang")
  private String sdtKhachhang;

  @Column(name = "dia_chi")
  private String diaChi;

  @Column(name = "hinh_thuc_thanh_toan")
  private String hinhThucThanhToan;

  @Column(name = "loai_hoa_don")
  private String loaiHoaDon;

  @OneToMany(mappedBy = "hoaDon", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private List<HoaDonChiTiet> chiTietHoaDon;

}
