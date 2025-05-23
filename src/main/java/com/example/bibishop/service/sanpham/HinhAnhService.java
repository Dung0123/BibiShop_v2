package com.example.bibishop.service.sanpham;

import com.example.bibishop.dto.HinhAnhDTO;
import java.util.List;

public interface HinhAnhService {
    List<HinhAnhDTO> getAllHinhAnh();
    HinhAnhDTO addHinhAnh(HinhAnhDTO hinhAnhDTO);
    HinhAnhDTO UpdateHinhAnh(HinhAnhDTO hinhAnhDTO);
}
