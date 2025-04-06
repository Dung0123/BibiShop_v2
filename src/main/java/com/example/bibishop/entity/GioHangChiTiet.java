package com.example.bibishop.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "gio_hang_chi_tiet")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GioHangChiTiet extends SuperEntity {

  @ManyToOne
  @JoinColumn(name = "id_giohang", nullable = false)
  private GioHang gioHang;

  @Column(name = "id_sanpham", nullable = false)
  private Long idSanPham;

  @Column(name = "so_luong", nullable = false)
  private Integer soLuong;

  @Column(name = "gia", nullable = false)
  private BigDecimal gia;
  /*
giá sẽ bằng giá sản phẩm chi tiết
*/

  // Quan hệ với giảm giá theo sản phẩm
  @ManyToOne
  @JoinColumn(name = "id_giamgiasanpham")
  private GiamGiaSanPham giamGiaSanPham;
}

