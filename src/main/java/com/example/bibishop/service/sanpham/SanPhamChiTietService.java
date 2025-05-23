package com.example.bibishop.service.sanpham;

import com.example.bibishop.dto.*;
import com.example.bibishop.entity.SanPhamChiTietEntity;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

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
