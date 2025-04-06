package com.example.bibishop.repository;


import com.example.bibishop.entity.KichCoEntity;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KichCoRepository extends JpaRepository<KichCoEntity, UUID> {
}
