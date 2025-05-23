package com.example.bibishop.service;

import com.example.bibishop.entity.DanhMucEntity;
import com.example.bibishop.repository.DanhMucRepository_1;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DanhMucService {

  @Autowired
  DanhMucRepository_1 danhMucRepository;

  public List<DanhMucEntity> findAll() {
    return danhMucRepository.findAll();
  }

}
