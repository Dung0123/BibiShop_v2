package com.example.bibishop.service;

import com.example.bibishop.entity.SanPhamChiTietEntity;
import com.example.bibishop.repository.SanPhamChiTietRepository_1;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SanPhamChiTietService {

    @Autowired
    SanPhamChiTietRepository_1 sanPhamChiTietRepository;

    public Optional<SanPhamChiTietEntity> findById(Integer id) {
        return sanPhamChiTietRepository.findById(id);
    }

}
