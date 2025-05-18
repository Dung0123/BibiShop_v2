package com.example.bibishop.sanpham.service;

import com.example.bibishop.sanpham.dto.KichCoDTO;
import com.example.bibishop.sanpham.dto.MauSacDTO;

import java.util.List;

public interface KichCoService {
    List<KichCoDTO> getAllKichCo();
    KichCoDTO addKichCo(KichCoDTO kichCoDTO);
    KichCoDTO updateKichCo(KichCoDTO kichCoDTO);
    public boolean isTenKichCoExists(String tenKichCo);
}
