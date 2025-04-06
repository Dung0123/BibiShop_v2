package com.example.bibishop.service.server.HoaDon;

import com.example.bibishop.entity.GiamGiaSanPham;
import com.example.bibishop.entity.GioHangChiTiet;
import com.example.bibishop.repository.GiamGiaSanPhamRepository;
import com.example.bibishop.repository.GioHangChiTietRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GioHangChiTietService {
  private final GioHangChiTietRepository chiTietRepository;
  private final GiamGiaSanPhamRepository giamGiaSanPhamRepository;

  public GioHangChiTiet apDunggiamGiaSanPham(Integer chiTietId, Long idGiamGia) {
    GioHangChiTiet chiTiet = chiTietRepository.findById(Long.valueOf(chiTietId))
        .orElseThrow(() -> new RuntimeException("Sản phẩm không tồn tại"));

    GiamGiaSanPham giamGia = giamGiaSanPhamRepository.findById(idGiamGia)
        .orElseThrow(() -> new RuntimeException("Giảm giá không hợp lệ"));

    chiTiet.setGiamGiaSanPham(giamGia);
    return chiTietRepository.save(chiTiet);
  }
}

