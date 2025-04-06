package com.example.bibishop.service.server.HoaDon;

import com.example.bibishop.entity.HoaDon;
import com.example.bibishop.entity.HoaDonChiTiet;
import com.example.bibishop.repository.HoaDonChiTietRepository;
import java.util.List;
import java.util.Optional;

public class HoaDonChiTietService {

  private HoaDonChiTietRepository hoaDonChiTietRepository;

  public List<HoaDonChiTiet> getByHoaDonId(Integer hoaDonId) {
    return hoaDonChiTietRepository.findByHoaDonId(hoaDonId);
  }

  public HoaDonChiTiet getHoaDonChiTietById(Integer id) {
    Optional<HoaDonChiTiet> hoaDonChiTiet = hoaDonChiTietRepository.findById(id);
    return hoaDonChiTiet.orElseThrow(() -> new RuntimeException("HoaDonChiTiet not found"));
  }
  public HoaDonChiTiet updateHoaDonChiTiet(HoaDonChiTiet hoaDonChiTiet) {
    // Assuming the HoaDonChiTiet exists, we save it again
    return hoaDonChiTietRepository.save(hoaDonChiTiet);
  }

  public void deleteHoaDonChiTiet(Integer id) {
    hoaDonChiTietRepository.deleteById(id);
  }

  public HoaDonChiTiet saveHoaDonChiTiet(HoaDonChiTiet hoaDonChiTiet) {
    // If HoaDon is not null, set it (assuming it is not already set)
    if (hoaDonChiTiet.getHoaDon() != null) {
      HoaDon hoaDon = hoaDonChiTietRepository.findById(hoaDonChiTiet.getHoaDon().getId())
          .orElseThrow(() -> new RuntimeException("HoaDon not found")).getHoaDon();
      hoaDonChiTiet.setHoaDon(hoaDon);
    }
    return hoaDonChiTietRepository.save(hoaDonChiTiet);
  }

}
