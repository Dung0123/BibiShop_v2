package com.example.bibishop.repository;

import com.example.bibishop.entity.HinhAnhEntity;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HinhAnhRepository extends JpaRepository<HinhAnhEntity,UUID> {
}
