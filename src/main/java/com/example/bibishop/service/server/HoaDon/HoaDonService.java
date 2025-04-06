package com.example.bibishop.service.server.HoaDon;

import com.example.bibishop.dto.HoaDonChiTietDTO;
import com.example.bibishop.dto.HoaDonDTO;
import com.example.bibishop.entity.*;
import com.example.bibishop.repository.GioHangRepository;
import com.example.bibishop.repository.HoaDonChiTietRepository;
import com.example.bibishop.repository.HoaDonRepository;
import com.example.bibishop.repository.KhachHangRepository;
import com.example.bibishop.repository.MaGiamGiaRepository;
import com.example.bibishop.repository.SanPhamChiTietRepository;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class HoaDonService {

  public final SanPhamChiTietRepository sanPhamChiTietRepository;

  MaGiamGiaRepository maGiamGiaRepository;

  private final HoaDonRepository hoaDonRepository;

  private final HoaDonChiTietRepository hoaDonChiTietRepository;

  private final GioHangRepository gioHangRepository;


  public final KhachHangRepository getKhachHangRepository;

  public HoaDonService(SanPhamChiTietRepository sanPhamChiTietRepository,
      HoaDonRepository hoaDonRepository, HoaDonChiTietRepository hoaDonChiTietRepository,
      GioHangRepository gioHangRepository, KhachHangRepository getKhachHangRepository) {
    this.sanPhamChiTietRepository = sanPhamChiTietRepository;
    this.hoaDonRepository = hoaDonRepository;
    this.hoaDonChiTietRepository = hoaDonChiTietRepository;
    this.gioHangRepository = gioHangRepository;
    this.getKhachHangRepository = getKhachHangRepository;
  }

  // Lấy tất cả hóa đơn
  public Page<HoaDon> getAllHoaDon(Pageable pageable) {
    return hoaDonRepository.findAll(pageable);
  }

  public Page<HoaDon> filterHoaDon(String maHoaDon, Pageable pageable) {
    return hoaDonRepository.filterHoaDon(maHoaDon, pageable);
  }

  public HoaDon getHoaDonByMaHoaDon(String maHoaDon) {
    return hoaDonRepository.findByMaHoaDon(maHoaDon).orElse(null);
  }

  public HoaDon getHoaDonById(Integer id) {
    return hoaDonRepository.findById(id).orElse(null);
  }

  public HoaDonChiTiet deleteProductFromBill(Integer chiTietId) {
    HoaDonChiTiet chiTiet = hoaDonChiTietRepository.findById(chiTietId).orElse(null);
    if (chiTiet != null) {
      HoaDon hoaDon = chiTiet.getHoaDon();
      BigDecimal amountToSubtract = chiTiet.getGia()
          .multiply(BigDecimal.valueOf(chiTiet.getSoLuong()));
      hoaDon.setTongTien(hoaDon.getTongTien().subtract(amountToSubtract));
      hoaDonChiTietRepository.delete(chiTiet);
      hoaDonRepository.save(hoaDon);
    }
    return chiTiet;
  }

  public void updateTrangThai(String maHoaDon, String trangThai) {
    HoaDon hoaDon = hoaDonRepository.findByMaHoaDon(maHoaDon).orElse(null);
    if (hoaDon != null) {
      hoaDon.setTrangThai(trangThai);
      hoaDonRepository.save(hoaDon);
    }
  }


  public HoaDonDTO createHoaDon(HoaDonDTO hoaDonDTO) {
    HoaDon hoaDon = new HoaDon();
    hoaDon.setMaHoaDon("HD" + System.currentTimeMillis());
    hoaDon.setNgayTao(LocalDateTime.now());
    hoaDon.setTrangThai("DA_THANH_TOAN");
    hoaDon.setLoaiHoaDon(hoaDonDTO.getLoaiHoaDon());
    hoaDon.setHinhThucThanhToan(hoaDonDTO.getHinhThucThanhToan());
    hoaDon.setChiTietHoaDon(new ArrayList<>());

    // Khởi tạo giamGia mặc định là 0
    hoaDon.setGiamGia(BigDecimal.ZERO);

    // Gán mã giảm giá (MaGiamGia) và giá trị giảm (giamGia)
    if (hoaDonDTO.getGiamGia() != null) {
      Optional<MaGiamGia> maGiamGiaOptional = maGiamGiaRepository.findById(hoaDonDTO.getGiamGia());
      if (maGiamGiaOptional.isPresent()) {
        MaGiamGia maGiamGia = maGiamGiaOptional.get();
        hoaDon.setMaGiamGia(maGiamGia); // Gán MaGiamGia để liên kết với id_ma_giam_gia
        hoaDon.setGiamGia(maGiamGia.getGiaTriGiam()); // Gán giá trị giảm vào giamGia
      } else {
        throw new RuntimeException("Mã giảm giá không tồn tại");
      }
    }

    BigDecimal totalAmount = BigDecimal.ZERO;
    for (HoaDonChiTietDTO hoaDonChiTietDTO : hoaDonDTO.getHoaDonChiTiets()) {
      HoaDonChiTiet chiTiet = new HoaDonChiTiet();
      chiTiet.setHoaDon(hoaDon);

      SanPhamChiTietEntity sanPhamChiTiet = sanPhamChiTietRepository.findById(
              hoaDonChiTietDTO.getChiTietSanPhamId())
          .orElseThrow(() -> new RuntimeException("Sản phẩm chi tiết không tồn tại"));
      chiTiet.setSanPhamChiTiet(sanPhamChiTiet); // Sửa lỗi: Gán đối tượng SanPhamChiTietEntity

      if (sanPhamChiTiet.getSoLuong() < hoaDonChiTietDTO.getSoLuong()) {
        throw new RuntimeException("Số lượng không đủ cho sản phẩm: " + sanPhamChiTiet);
      }

      sanPhamChiTiet.setSoLuong(sanPhamChiTiet.getSoLuong() - hoaDonChiTietDTO.getSoLuong());

      chiTiet.setSanPham(sanPhamChiTiet.getSanPham());

      chiTiet.setSoLuong(hoaDonChiTietDTO.getSoLuong());
      chiTiet.setGia(sanPhamChiTiet.getGiaSanPham());
      hoaDon.getChiTietHoaDon().add(chiTiet);
      totalAmount = totalAmount.add(
          sanPhamChiTiet.getGiaSanPham().multiply(new BigDecimal(hoaDonChiTietDTO.getSoLuong())));

      sanPhamChiTietRepository.save(sanPhamChiTiet);
    }

    hoaDon.setTongTien(totalAmount);
    HoaDon savedHoaDon = hoaDonRepository.save(hoaDon);
    return HoaDonDTO.from(savedHoaDon);
  }

  // Tạo hóa đơn từ giỏ hàng
  public HoaDon createHoaDon(GioHang gioHang, String tenNguoiNhan, String sdt, String diaChi) {
    HoaDon hoaDon = new HoaDon();
    hoaDon.setMaHoaDon("HD" + System.currentTimeMillis());

    // Tìm khách hàng từ idKhachHang
    KhachHang khachHang = getKhachHangRepository.findById(gioHang.getIdKhachHang())
        .orElseThrow(() -> new RuntimeException("Khách hàng không tồn tại"));
    hoaDon.setKhachHang(khachHang); // Sửa lỗi: Gán đối tượng KhachHang thay vì ID

    hoaDon.setNgayTao(LocalDateTime.now());
    hoaDon.setTongTien(calculateTotal(gioHang));
    hoaDon.setTrangThai("CHO_XAC_NHAN");
    hoaDon.setTenKhachhang(tenNguoiNhan);
    hoaDon.setSdtKhachhang(sdt);
    hoaDon.setDiaChi(diaChi);

// Tạo chi tiết hóa đơn từ giỏ hàng
    for (GioHangChiTiet item : gioHang.getChiTietGioHang()) {
      HoaDonChiTiet chiTiet = new HoaDonChiTiet();
      chiTiet.setHoaDon(hoaDon);

      // Tìm SanPhamChiTietEntity từ idSanPham
      SanPhamChiTietEntity sanPhamChiTiet = sanPhamChiTietRepository.findById(
              Math.toIntExact(item.getIdSanPham()))
          .orElseThrow(() -> new RuntimeException("Sản phẩm chi tiết không tồn tại"));
      chiTiet.setSanPhamChiTiet(sanPhamChiTiet); // Sửa lỗi: Gán đối tượng SanPhamChiTietEntity

      // Gán sản phẩm (SanPhamEntity) từ SanPhamChiTietEntity
      chiTiet.setSanPham(sanPhamChiTiet.getSanPham());

      chiTiet.setSoLuong(item.getSoLuong());
      chiTiet.setGia(item.getGia());
      hoaDon.getChiTietHoaDon().add(chiTiet);
    }

    gioHang.setTrangThai(TrangThaiGioHang.HOAN_THANH);
    gioHangRepository.save(gioHang);
    return hoaDonRepository.save(hoaDon);
  }

  // Cập nhật trạng thái hóa đơn
  public void updateTrangThai(Integer idHoaDon, String trangThai) {
    HoaDon hoaDon = hoaDonRepository.findById(idHoaDon)
        .orElseThrow(() -> new RuntimeException("Hóa đơn không tồn tại"));
    hoaDon.setTrangThai(trangThai);
    hoaDonRepository.save(hoaDon);
  }

  // Tính tổng tiền hóa đơn
  public BigDecimal calculateTotal(HoaDon hoaDon) {
    return hoaDon.getChiTietHoaDon().stream()
        .map(item -> item.getGia().multiply(BigDecimal.valueOf(item.getSoLuong())))
        .reduce(BigDecimal.ZERO, BigDecimal::add);
  }

  public BigDecimal calculateTotal(GioHang gioHang) {
    return gioHang.getChiTietGioHang().stream()
        .map(item -> item.getGia().multiply(BigDecimal.valueOf(item.getSoLuong())))
        .reduce(BigDecimal.ZERO, BigDecimal::add);
  }

}

