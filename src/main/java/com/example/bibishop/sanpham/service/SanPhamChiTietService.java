package com.example.bibishop.sanpham.service;

import com.example.bibishop.sanpham.dto.*;
import com.example.bibishop.entity.SanPhamChiTietEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SanPhamChiTietService {
    Page<SanPhamChiTietDTO> getAllSanPhamChiTiet(Integer totalPage, Integer totalItem, SanPhamChiTietFiterDTO form);
    Page<SanPhamChiTietDTO> getAllSanPhamChiTietBYidSP(Integer idSP, Integer totalPage, Integer totalItem, SanPhamCtFiterDTO fiterDTO);
    SanPhamChiTietCrud addSanPhamChiTiet(SanPhamChiTietCrud sanPhamChiTietCrud);
    SanPhamChiTietCrud upDateSanPhamChiTiet(SanPhamChiTietCrud sanPhamChiTietCrud);
    SanPhamChiTietDTO findById(Integer id);
    List<SanPhamChiTietDTO> GetForSP(Pageable pageable);
    List<SanPhamChiTietDTO> AllSanPhamChiTietByidSP(Integer idSP);


    Optional<SanPhamChiTietEntity> checkExistingSanPhamCT(
            BigDecimal giaSanPham,
            Integer sanPhamId,
            String gioiTinh,
            String trongLuong,
            Integer danhMucId,
            Integer hinhAnhId,
            Integer kichCoId,
            Integer mauSacId,
            Integer chatLieuId);
}
