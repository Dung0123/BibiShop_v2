package com.example.bibishop.repository.sanpham;

import com.example.bibishop.entity.ChatLieuEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatLieuRepository extends JpaRepository<ChatLieuEntity, Integer> {
    boolean existsByTen(String ten);  // ✅ Kiểm tra trùng tên
}
