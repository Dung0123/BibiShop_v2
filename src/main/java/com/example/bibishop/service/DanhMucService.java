package com.example.bibishop.service;

import com.example.bibishop.entity.DanhMucEntity;
import com.example.bibishop.repository.DanhMucRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DanhMucService {

  @Autowired
  DanhMucRepository danhMucRepository;

  public List<DanhMucEntity> findAll() {
    return danhMucRepository.findAll();
  }

}
