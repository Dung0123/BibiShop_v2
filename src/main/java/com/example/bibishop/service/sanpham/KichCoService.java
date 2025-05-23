package com.example.bibishop.service.sanpham;

import com.example.bibishop.dto.DanhMucDTO;
import com.example.bibishop.dto.KichCoDTO;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface KichCoService {

  Page<KichCoDTO> getAllKichCo(Pageable pageable);

  List<KichCoDTO> getAllKichCo();

  KichCoDTO addKichCo(KichCoDTO kichCoDTO);

  KichCoDTO updateKichCo(KichCoDTO kichCoDTO);

  public boolean isTenKichCoExists(String tenKichCo);

  boolean deleteKichCo(Long id);
}
