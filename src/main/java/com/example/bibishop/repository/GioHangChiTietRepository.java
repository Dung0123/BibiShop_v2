package com.example.bibishop.repository;

import com.example.bibishop.entity.GioHangChiTiet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GioHangChiTietRepository extends JpaRepository<GioHangChiTiet, Long> {
}