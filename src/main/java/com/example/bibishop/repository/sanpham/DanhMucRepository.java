package com.example.bibishop.repository.sanpham;

import com.example.bibishop.dto.DanhMucDTO;
import com.example.bibishop.entity.DanhMucEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DanhMucRepository extends JpaRepository<DanhMucEntity, Integer> {

  boolean existsByTenDanhMuc(String tenDanhMuc);

  DanhMucDTO findById(Long id);


}
