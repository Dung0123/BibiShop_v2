package com.example.bibishop.sanpham.repository;

import com.example.bibishop.entity.KichCoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KichCoRepository extends JpaRepository<KichCoEntity, Integer> {
    boolean existsByTenKichCo(String tenKichCo); // ✅ Kiểm tra trùng tên
}
