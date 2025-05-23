package com.example.bibishop.repository;


import com.example.bibishop.entity.MauSacEntity;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface MauSacRepository_1 extends JpaRepository<MauSacEntity, Integer> {
@Query("SELECT ms FROM MauSacEntity ms WHERE ms.trangThai = 1")
  List<MauSacEntity> findAllActive();   
 boolean existsByTen(String ten); // ✅ Kiểm tra trùng tên
    Page<MauSacEntity> findAll(Pageable pageable);

}
