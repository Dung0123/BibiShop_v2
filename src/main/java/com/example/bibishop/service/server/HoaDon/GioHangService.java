package com.example.bibishop.service.server.HoaDon;

import com.example.bibishop.entity.*;
import com.example.bibishop.repository.DotGiamGiaRepository;
import com.example.bibishop.repository.GioHangChiTietRepository;
import com.example.bibishop.repository.GioHangRepository;
import com.example.bibishop.repository.MaGiamGiaRepository;
import com.example.bibishop.repository.SanPhamChiTietRepository;
import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GioHangService {

  private final GioHangRepository gioHangRepository;
  private final GioHangChiTietRepository gioHangChiTietRepository;
  private final MaGiamGiaRepository maGiamGiaRepository;
  private final DotGiamGiaRepository dotGiamGiaRepository;
  private final SanPhamChiTietRepository sanPhamChiTietRepository;

  @Autowired
  public GioHangService(GioHangRepository gioHangRepository,
      GioHangChiTietRepository gioHangChiTietRepository,
      MaGiamGiaRepository maGiamGiaRepository,
      DotGiamGiaRepository dotGiamGiaRepository,
      SanPhamChiTietRepository sanPhamChiTietRepository) {
    this.gioHangRepository = gioHangRepository;
    this.gioHangChiTietRepository = gioHangChiTietRepository;
    this.maGiamGiaRepository = maGiamGiaRepository;
    this.dotGiamGiaRepository = dotGiamGiaRepository;
    this.sanPhamChiTietRepository = sanPhamChiTietRepository;
  }

  // Tìm hoặc tạo giỏ hàng cho khách hàng
  public GioHang getOrCreateGioHang(Integer idKhachHang) {
    Optional<GioHang> gioHangOpt = gioHangRepository.findByIdKhachHangAndTrangThai(idKhachHang, TrangThaiGioHang.CHO_XU_LY);
    if (gioHangOpt.isPresent()) {
      return gioHangOpt.get();
    } else {
      GioHang gioHang = new GioHang();
      gioHang.setIdKhachHang(idKhachHang);
      gioHang.setTrangThai(TrangThaiGioHang.CHO_XU_LY);
      gioHang.setNgayTao(LocalDateTime.now());
      return gioHangRepository.save(gioHang);
    }
  }

  // Thêm sản phẩm vào giỏ hàng
  public void addToCart(Integer idKhachHang, Integer idSanPhamChiTiet, Integer soLuong) {
    GioHang gioHang = getOrCreateGioHang(idKhachHang);
    SanPhamChiTietEntity sanPhamChiTiet = sanPhamChiTietRepository.findById(
            idSanPhamChiTiet)
        .orElseThrow(() -> new RuntimeException("Sản phẩm không tồn tại"));

    Optional<GioHangChiTiet> existingItem = gioHang.getChiTietGioHang().stream()
        .filter(item -> item.getIdSanPham().equals(idSanPhamChiTiet))
        .findFirst();

    if (existingItem.isPresent()) {
      GioHangChiTiet item = existingItem.get();
      item.setSoLuong(item.getSoLuong() + soLuong);
      gioHangChiTietRepository.save(item);
    } else {
      GioHangChiTiet newItem = new GioHangChiTiet();
      newItem.setGioHang(gioHang);
      newItem.setIdSanPham(Long.valueOf(idSanPhamChiTiet));
      newItem.setSoLuong(soLuong);
      newItem.setGia(sanPhamChiTiet.getGiaSanPham());
      gioHangChiTietRepository.save(newItem);
      gioHang.getChiTietGioHang().add(newItem);
    }
    gioHangRepository.save(gioHang);
  }

  // Sửa số lượng sản phẩm trong giỏ hàng
  public void updateQuantity(Long idGioHangChiTiet, Integer soLuong) {
    GioHangChiTiet item = gioHangChiTietRepository.findById(idGioHangChiTiet)
        .orElseThrow(() -> new RuntimeException("Sản phẩm không tồn tại trong giỏ hàng"));
    if (soLuong <= 0) {
      gioHangChiTietRepository.delete(item);
    } else {
      item.setSoLuong(soLuong);
      gioHangChiTietRepository.save(item);
    }
  }

  // Xóa sản phẩm khỏi giỏ hàng
  public void removeFromCart(Long idGioHangChiTiet) {
    gioHangChiTietRepository.deleteById(idGioHangChiTiet);
  }

  // Áp dụng mã giảm giá
  public BigDecimal applyDiscount(GioHang gioHang, String maGiamGiaCode) {
    BigDecimal total = calculateTotal(gioHang);

    // Áp dụng đợt giảm giá tự động
    Date today = Date.valueOf(LocalDate.now());
    List<DotGiamGia> dotGiamGias = dotGiamGiaRepository.findByNgayBatDauLessThanEqualAndNgayKetThucGreaterThanEqual(today, today);
    DotGiamGia applicableDotGiamGia = dotGiamGias.stream().findFirst().orElse(null);
    if (applicableDotGiamGia != null) {
      gioHang.setDotGiamGia(applicableDotGiamGia);
      total = total.subtract(applicableDotGiamGia.getGiaTriGiam());
    }

    // Áp dụng mã giảm giá
    if (maGiamGiaCode != null && !maGiamGiaCode.isEmpty()) {
      Optional<MaGiamGia> maGiamGiaOpt = maGiamGiaRepository.findByMa(maGiamGiaCode);
      if (maGiamGiaOpt.isPresent()) {
        MaGiamGia maGiamGia = maGiamGiaOpt.get();
        if (maGiamGia.getNgayBatDau() != null && maGiamGia.getNgayKetThuc() != null &&
            maGiamGia.getNgayBatDau().before(today) && maGiamGia.getNgayKetThuc().after(today)) {
          gioHang.setMaGiamGia(maGiamGia);
          BigDecimal giamGia = maGiamGia.getGiaTriGiam() != null ? maGiamGia.getGiaTriGiam() : BigDecimal.ZERO;
          total = total.subtract(giamGia);
        }
      }
    }

    if (total.compareTo(BigDecimal.ZERO) < 0) {
      total = BigDecimal.ZERO;
    }
    return total;
  }

  // Tính tổng tiền giỏ hàng (trước giảm giá)
  public BigDecimal calculateTotal(GioHang gioHang) {
    return gioHang.getChiTietGioHang().stream()
        .map(item -> item.getGia().multiply(BigDecimal.valueOf(item.getSoLuong())))
        .reduce(BigDecimal.ZERO, BigDecimal::add);
  }
}