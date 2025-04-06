package com.example.bibishop.service;

import com.example.bibishop.entity.DotGiamGia;
import com.example.bibishop.repository.DotGiamGiaRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DotGiamGiaService {

    @Autowired
    private DotGiamGiaRepository dotGiamGiaRepository;

    public List<DotGiamGia> findAll() {
        return dotGiamGiaRepository.findAll();
    }

}
