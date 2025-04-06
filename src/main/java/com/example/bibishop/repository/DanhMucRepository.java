package com.example.bibishop.repository;


import com.example.bibishop.entity.DanhMucEntity;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DanhMucRepository extends JpaRepository<DanhMucEntity, UUID> {

}
