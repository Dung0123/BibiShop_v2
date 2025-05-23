package com.example.bibishop.repository;


import com.example.bibishop.entity.ChatLieuEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ChatLieuRepository_1 extends JpaRepository<ChatLieuEntity, Integer> {

  @Query("SELECT cl FROM ChatLieuEntity cl WHERE cl.trangThai = 1")
  List<ChatLieuEntity> findAllActive();

  boolean existsByTen(String ten);  // ✅ Kiểm tra trùng tên

}
