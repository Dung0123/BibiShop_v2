package com.example.bibishop.sanpham.service;

import com.example.bibishop.sanpham.dto.HinhAnhDTO;


import java.util.List;

public interface HinhAnhService {
    List<HinhAnhDTO> getAllHinhAnh();
    HinhAnhDTO addHinhAnh(HinhAnhDTO hinhAnhDTO);
    HinhAnhDTO UpdateHinhAnh(HinhAnhDTO hinhAnhDTO);
}
