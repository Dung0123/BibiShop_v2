package com.example.bibishop.repository;

import com.example.bibishop.entity.GiamGiaSanPham;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GiamGiaSanPhamRepository extends JpaRepository<GiamGiaSanPham, Long> {}
