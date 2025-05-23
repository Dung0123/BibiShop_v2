package com.example.bibishop.repository.sanpham;

import com.example.bibishop.entity.MauSacEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MauSacRepository extends JpaRepository<MauSacEntity, Integer> {
    boolean existsByTen(String ten); // ✅ Kiểm tra trùng tên
    Page<MauSacEntity> findAll(Pageable pageable);
}
