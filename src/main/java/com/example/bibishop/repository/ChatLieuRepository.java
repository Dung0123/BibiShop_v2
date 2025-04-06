package com.example.bibishop.repository;


import com.example.bibishop.entity.ChatLieuEntity;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ChatLieuRepository extends JpaRepository<ChatLieuEntity, UUID> {

  @Query("SELECT cl FROM ChatLieuEntity cl WHERE cl.trangThai = 1")
  List<ChatLieuEntity> findAllActive();

}
