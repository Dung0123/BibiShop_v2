package com.example.bibishop.repository;

import com.example.bibishop.entity.GioHang;
import com.example.bibishop.entity.TrangThaiGioHang;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface GioHangRepository extends JpaRepository<GioHang, Integer> {
  @Query("SELECT g FROM GioHang g WHERE g.idKhachHang = :idKhachHang AND g.trangThai = :trangThai")
  Optional<GioHang> findByIdKhachHangAndTrangThai(@Param("idKhachHang") Integer idKhachHang, @Param("trangThai") TrangThaiGioHang trangThai);
}