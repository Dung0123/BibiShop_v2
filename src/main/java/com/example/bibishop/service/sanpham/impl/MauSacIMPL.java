package com.example.bibishop.service.sanpham.impl;

import com.example.bibishop.repository.sanpham.MauSacRepository;
import com.example.bibishop.dto.MauSacDTO;
import com.example.bibishop.entity.MauSacEntity;
import com.example.bibishop.service.sanpham.MauSacService;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MauSacIMPL implements MauSacService {

    private final MauSacRepository mauSacRepository;
    private final ModelMapper modelMapper;
    @Override
    // Lấy danh sách màu sắc có phân trang
    public Page<MauSacDTO> getAllMauSac(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<MauSacEntity> mauSacEntities = mauSacRepository.findAll(pageable);

        // Chuyển đổi sang DTO
        return mauSacEntities.map(entity -> modelMapper.map(entity, MauSacDTO.class));
    }

    @Override
    public boolean isTenMauSacExists(String ten) {
        return mauSacRepository.existsByTen(ten);
    }

    @Override
    public List<MauSacDTO> getAllMauSac() {
        List<MauSacEntity>mauSacEntities = mauSacRepository.findAll();
        return mauSacEntities.stream()
                .map(entity -> modelMapper.map(entity, MauSacDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public MauSacDTO addMauSac(MauSacDTO mauSacDTO) {
        MauSacEntity mauSac = MauSacEntity.builder()
                .ten(mauSacDTO.getTen())
                .trangThai(mauSacDTO.getTrangThai()!= null ? Integer.parseInt(
                    mauSacDTO.getTrangThai()) : 0)
                .build();
        mauSac.setCreateDate(LocalDate.now());
        mauSac.setUpdateDate(LocalDateTime.now());
        mauSacRepository.save(mauSac);

        return modelMapper.map(mauSac,MauSacDTO.class);
    }

    @Override
    public MauSacDTO updateMauSac(MauSacDTO mauSacDTO) {
        Optional<MauSacEntity> mauSac = mauSacRepository.findById(mauSacDTO.getId());
        MauSacEntity mauSac1 = new MauSacEntity();
        mauSac1.setId(mauSac.get().getId());
        mauSac1.setTen(mauSacDTO.getTen());
        mauSac1.setTrangThai(Integer.parseInt(mauSacDTO.getTrangThai()));
        mauSac1.setCreateDate(LocalDate.now());
        mauSac1.setUpdateDate(LocalDateTime.now());
        mauSacRepository.save(mauSac1);
        return modelMapper.map(mauSac1,MauSacDTO.class);
    }
}
