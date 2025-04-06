package com.example.bibishop.repository;


import com.example.bibishop.entity.MauSacEntity;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MauSacRepository extends JpaRepository<MauSacEntity, UUID> {

  @Query("SELECT ms FROM MauSacEntity ms WHERE ms.trangThai = 1")
  List<MauSacEntity> findAllActive();

}
