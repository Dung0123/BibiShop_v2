package com.example.bibishop.sanpham.service;

import com.example.bibishop.sanpham.dto.SanPhamChiTietDTO;
import com.example.bibishop.sanpham.dto.SanPhamChiTietFiterDTO;
import com.example.bibishop.sanpham.dto.SanPhamDTO;
import com.example.bibishop.sanpham.dto.SanPhamFiterDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface SanPhamService {
    Page<SanPhamDTO> getAllSanPham(Integer totalPage, Integer totalItem, SanPhamFiterDTO form);
    SanPhamDTO addSanPham(SanPhamDTO sanPhamDTO);
    SanPhamDTO upDateSanPham(SanPhamDTO sanPhamDTO);
    boolean checkProductName(String name);

    List<SanPhamDTO> getAllSanPham(); // Thêm phương thức này


}
