package com.example.bibishop.repository;


import com.example.bibishop.entity.DanhMucEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface DanhMucRepository_1 extends JpaRepository<DanhMucEntity, Integer> {
    boolean existsByTenDanhMuc(String tenDanhMuc);
}
