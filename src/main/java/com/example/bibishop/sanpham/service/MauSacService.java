package com.example.bibishop.sanpham.service;

import com.example.bibishop.sanpham.dto.DanhMucDTO;
import com.example.bibishop.sanpham.dto.MauSacDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface MauSacService {
    List<MauSacDTO> getAllMauSac();
    MauSacDTO addMauSac(MauSacDTO mauSacDTO);
    MauSacDTO updateMauSac(MauSacDTO mauSacDTO);
    public boolean isTenMauSacExists(String ten);
    public Page<MauSacDTO> getAllMauSac(int page, int size);
}
