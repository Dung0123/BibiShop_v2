package com.example.bibishop.service.sanpham.impl;

import com.example.bibishop.repository.sanpham.DanhMucRepository;
import com.example.bibishop.dto.DanhMucDTO;
import com.example.bibishop.entity.DanhMucEntity;
import com.example.bibishop.service.sanpham.DanhMucService;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DanhMucIMPL implements DanhMucService {
    private final DanhMucRepository danhMucRepository;
    private final ModelMapper modelMapper;

    public boolean existsByTenDanhMuc(String tenDanhMuc) {
        return danhMucRepository.existsByTenDanhMuc(tenDanhMuc);
    }

    @Override
    public boolean deleteDanhMuc(Integer id) {
        Optional<DanhMucEntity> danhMucDTO = danhMucRepository.findById(id);
        if(danhMucDTO.get().getTrangThai()==0)
        {
            danhMucDTO.get().setTrangThai(1);
            danhMucRepository.save(danhMucDTO.get());
        }else{
            danhMucDTO.get().setTrangThai(0);
            danhMucRepository.save(danhMucDTO.get());
        }
        return true;
    }

    @Override
    public List<DanhMucDTO> getAllDanhMuc() {
        List<DanhMucEntity>danhMucEntities = danhMucRepository.findAll();
        return danhMucEntities.stream()
                .map(entity -> modelMapper.map(entity, DanhMucDTO.class))
                .collect(Collectors.toList());
    }
    @Override
    public Page<DanhMucDTO> getAllDanhMuc(Pageable pageable) {
        Page<DanhMucEntity> danhMucEntities = danhMucRepository.findAll(pageable);
        return danhMucEntities.map(entity -> modelMapper.map(entity, DanhMucDTO.class));
    }
    @Override
    public DanhMucDTO addDanhMuc(DanhMucDTO dto) {
        DanhMucEntity danhMuc = DanhMucEntity.builder()
                .tenDanhMuc(dto.getTenDanhMuc())
                .build();
        danhMuc.setCreateDate(LocalDate.now());
        danhMuc.setUpdateDate(LocalDateTime.now());
        danhMuc.setTrangThai(0);
        danhMucRepository.save(danhMuc);

        return modelMapper.map(danhMuc,DanhMucDTO.class);
    }

    @Override
    public DanhMucDTO upDateDanhMuc(DanhMucDTO dto) {
        Optional<DanhMucEntity> danhMuc = danhMucRepository.findById(dto.getId());
        DanhMucEntity danhMuc1 = new DanhMucEntity();
        danhMuc1.setId(danhMuc.get().getId());
        danhMuc1.setTenDanhMuc(dto.getTenDanhMuc());
        danhMuc1.setCreateDate(LocalDate.now());
        danhMuc1.setUpdateDate(LocalDateTime.now());
        danhMucRepository.save(danhMuc1);
        return modelMapper.map(danhMuc1,DanhMucDTO.class);
    }

}
