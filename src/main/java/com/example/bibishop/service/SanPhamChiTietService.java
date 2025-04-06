package com.example.bibishop.service;

import com.example.bibishop.entity.SanPhamChiTietEntity;
import com.example.bibishop.repository.SanPhamChiTietRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SanPhamChiTietService {

    @Autowired
    SanPhamChiTietRepository sanPhamChiTietRepository;

    public Optional<SanPhamChiTietEntity> findById(Integer id) {
        return sanPhamChiTietRepository.findById(id);
    }

}
