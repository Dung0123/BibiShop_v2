package com.example.bibishop.repository;

import com.example.bibishop.dto.HDCTSearchRequest;
import com.example.bibishop.dto.HDCTSearchResponse;
import com.example.bibishop.entity.HoaDonChiTiet;
import com.example.bibishop.repository.custom.HoadonRepoCustom;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface HoaDonChiTietRepository extends JpaRepository<HoaDonChiTiet,Integer>, HoadonRepoCustom {

  @Query("SELECT hdct FROM HoaDonChiTiet hdct where hdct.hoaDon.id =:hoadonId")
  List<HoaDonChiTiet> findByHoaDonId(@Param("hoadonId") Integer hoadonId);

}
