package com.example.bibishop.service.sanpham;

import com.example.bibishop.dto.SanPhamDTO;
import com.example.bibishop.dto.SanPhamFiterDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface SanPhamService {
    Page<SanPhamDTO> getAllSanPham(Integer totalPage, Integer totalItem, SanPhamFiterDTO form);

    SanPhamDTO addSanPham(SanPhamDTO sanPhamDTO);

    SanPhamDTO upDateSanPham(SanPhamDTO sanPhamDTO);

    boolean checkProductName(String name);

    public boolean checkProductNameForUpdate(String tenSanPham, Integer id);

    List<SanPhamDTO> getAllSanPham(); // Thêm phương thức này

}
