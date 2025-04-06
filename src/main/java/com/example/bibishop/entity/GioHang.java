package com.example.bibishop.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.criteria.CriteriaBuilder.In;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "gio_hang")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GioHang {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "id_khachhang", nullable = false)
  private Integer idKhachHang;

  @Column(name = "ngay_tao", nullable = false)
  private LocalDateTime ngayTao = LocalDateTime.now();

  @Column(name = "trang_thai", nullable = false)
  @Enumerated(EnumType.STRING)
  private TrangThaiGioHang trangThai;

  // Quan hệ với Mã giảm giá
  @ManyToOne
  @JoinColumn(name = "id_magiamgia")
  private MaGiamGia maGiamGia;

  // Quan hệ với Đợt giảm giá
  @ManyToOne
  @JoinColumn(name = "id_dotgiamgia")
  private DotGiamGia dotGiamGia;

  @OneToMany(mappedBy = "gioHang", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private List<GioHangChiTiet> chiTietGioHang;
}


