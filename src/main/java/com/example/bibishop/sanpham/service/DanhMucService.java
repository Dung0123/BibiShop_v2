package com.example.bibishop.sanpham.service;

import com.example.bibishop.dto.DanhMucDTO;

import java.util.List;

public interface DanhMucService {
    List<DanhMucDTO> getAllDanhMuc();
     DanhMucDTO addDanhMuc(DanhMucDTO dto);
     DanhMucDTO upDateDanhMuc(DanhMucDTO dto);
    public boolean existsByTenDanhMuc(String tenDanhMuc);
}
