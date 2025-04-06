package com.example.bibishop.repository;

import com.example.bibishop.entity.KhachHang;
import jakarta.persistence.criteria.CriteriaBuilder.In;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface KhachHangRepository extends JpaRepository<KhachHang, Integer> {
  Page<KhachHang> findAll(Pageable pageable);
  @Query(value = "SELECT * " +
      "FROM khach_hang as k WHERE ((?1 is null or  k.ten LIKE CONCAT('%',?1,'%') or k.ho LIKE CONCAT('%',?1,'%') or  k.ten_dem LIKE CONCAT('%',?1,'%') ))" +
      "AND (?2 is null or  k.sdt LIKE CONCAT('%',?2,'%') )" +
      "AND (?3 is null or k.email LIKE CONCAT('%',?3,'%') ) ",
      countQuery = "SELECT count(*) " +
          "FROM khach_hang as k WHERE ((?1 is null or  k.ten LIKE CONCAT('%',?1,'%') or k.ho LIKE CONCAT('%',?1,'%') or  k.ten_dem LIKE CONCAT('%',?1,'%') ))" +
          "AND (?2 is null or  k.sdt LIKE CONCAT('%',?2,'%') )" +
          "AND (?3 is null or k.email LIKE CONCAT('%',?3,'%') ) "
      ,nativeQuery = true)
  Page<KhachHang> adminListUserPages(String fullName, String phone, String email, Pageable pageable);
  boolean existsByEmailAndIdNot(String email, Integer id);
  boolean existsBySdtAndIdNot(String sdt, Integer id);
  boolean existsByEmail(String email);
  boolean existsBySdt(String email);


}