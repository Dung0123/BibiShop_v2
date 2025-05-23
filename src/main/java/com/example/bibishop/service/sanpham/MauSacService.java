package com.example.bibishop.service.sanpham;

import com.example.bibishop.dto.MauSacDTO;
import java.util.List;
import org.springframework.data.domain.Page;

public interface MauSacService {
    List<MauSacDTO> getAllMauSac();
    MauSacDTO addMauSac(MauSacDTO mauSacDTO);
    MauSacDTO updateMauSac(MauSacDTO mauSacDTO);
    public boolean isTenMauSacExists(String ten);
    public Page<MauSacDTO> getAllMauSac(int page, int size);
}
