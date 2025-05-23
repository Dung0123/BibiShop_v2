package com.example.bibishop.repository.sanpham;

import com.example.bibishop.entity.HinhAnhEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HinhAnhRepository extends JpaRepository<HinhAnhEntity,Integer> {
}
