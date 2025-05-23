package com.example.bibishop.repository.sanpham;

import com.example.bibishop.dto.KichCoDTO;
import com.example.bibishop.entity.KichCoEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KichCoRepository extends JpaRepository<KichCoEntity, Integer> {
    boolean existsByTenKichCo(String tenKichCo); // ✅ Kiểm tra trùng tên

  Optional<KichCoDTO> findById(Long id);
}
