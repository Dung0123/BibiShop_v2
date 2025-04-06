package com.example.bibishop.repository;

import com.example.bibishop.entity.MaGiamGia;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MaGiamGiaRepository extends JpaRepository<MaGiamGia, Integer> {

  @Query("SELECT m FROM MaGiamGia m")
  Page<MaGiamGia> findAllMaGiamGia(Pageable pageable);

  @Query(value = "SELECT * FROM ma_giam_gia WHERE ma = :ma", nativeQuery = true)
  Optional<MaGiamGia> findByMa(@Param("ma") String ma);

//  @Query(value = "SELECT * FROM ma_giam_gia WHERE id = :id", nativeQuery = true)
//  Optional<MaGiamGia> findByIdnew(@Param("ma") String id);
}
