package com.example.bibishop.service.sanpham;

import com.example.bibishop.dto.DanhMucDTO;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DanhMucService {

  List<DanhMucDTO> getAllDanhMuc();

  Page<DanhMucDTO> getAllDanhMuc(Pageable pageable);

  DanhMucDTO addDanhMuc(DanhMucDTO dto);

  DanhMucDTO upDateDanhMuc(DanhMucDTO dto);

  public boolean existsByTenDanhMuc(String tenDanhMuc);

  boolean deleteDanhMuc(Integer id);
}
