package com.example.bibishop.sanpham.service.impl;

import com.example.bibishop.sanpham.repository.SanPhamRepository;
import com.example.bibishop.sanpham.dto.SanPhamDTO;
import com.example.bibishop.sanpham.dto.SanPhamFiterDTO;
import com.example.bibishop.entity.SanPhamEntity;
import com.example.bibishop.sanpham.service.SanPhamService;
import jakarta.persistence.EntityNotFoundException;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Builder
public class SanPhamIMPL implements SanPhamService {
    private final SanPhamRepository sanPhamRepository;
    private final ModelMapper modelMapper;

    @Override
    public Page<SanPhamDTO> getAllSanPham(Integer totalPage, Integer totalItem, SanPhamFiterDTO form) {
        Pageable pageable = PageRequest.of(totalPage, totalItem);

        // Xây dựng Specification từ form (SanPhamChiTietFiterDTO)
        Specification<SanPhamEntity> specification = SpectificationSanPham.buildWhere(form);

        // Kiểm tra nếu không có Specification hoặc Specification là null
        if (specification == null) {
            List<SanPhamDTO> emptyList = Collections.emptyList();
            return new PageImpl<>(emptyList, pageable, 0);
        }


        Page<SanPhamEntity> entityPage = sanPhamRepository.findAll(specification,pageable);

        // Convert Page<SanPhamChiTietEntity> sang Page<SanPhamChiTietDTO>
        List<SanPhamDTO> dtos = entityPage.getContent().stream()
                .map(entity -> modelMapper.map(entity, SanPhamDTO.class))
                .collect(Collectors.toList());

        return new PageImpl<>(dtos, pageable, entityPage.getTotalElements());
    }

    public class DuplicateProductException extends RuntimeException {
        public DuplicateProductException(String message) {
            super(message);
        }
    }

    // ✅ Phương thức mới để lấy tất cả sản phẩm (không phân trang)
    @Override
    public List<SanPhamDTO> getAllSanPham() {
        List<SanPhamEntity> entities = sanPhamRepository.findAll(); // Lấy toàn bộ sản phẩm từ DB
        return entities.stream()
                .map(entity -> modelMapper.map(entity, SanPhamDTO.class)) // Chuyển sang DTO
                .collect(Collectors.toList());
    }

@Override
    public boolean checkProductName(String name) {
        // Truy vấn cơ sở dữ liệu để kiểm tra tên sản phẩm
        return sanPhamRepository.existsByTenSanPham(name);
    }



    @Override
    public SanPhamDTO addSanPham(SanPhamDTO sanPhamDTO) {

           // Kiểm tra trùng tên sản phẩm
//           if (sanPhamRepository.findByTenSanPham(sanPhamDTO.getTenSanPham()).isPresent()) {
//               throw new DuplicateProductException("Sản phẩm với tên '" + sanPhamDTO.getTenSanPham() + "' đã tồn tại.");
//           }
           SanPhamEntity sanPhamEntity = SanPhamEntity.builder()
                   .tenSanPham(sanPhamDTO.getTenSanPham())
                   .trangThai(sanPhamDTO.getTrangThai()).build();
           sanPhamEntity.setCreateDate(LocalDate.now());
           sanPhamEntity.setUpdateDate(LocalDateTime.now());
           sanPhamRepository.save(sanPhamEntity);
           return modelMapper.map(sanPhamEntity, SanPhamDTO.class);



    }



    @Override
    public SanPhamDTO upDateSanPham(SanPhamDTO sanPhamDTO) {
        Optional<SanPhamEntity> optionalSanPham = sanPhamRepository.findById(sanPhamDTO.getId());
        if (optionalSanPham.isPresent()) {
            SanPhamEntity sanPhamEntity = optionalSanPham.get();
            sanPhamEntity.setTenSanPham(sanPhamDTO.getTenSanPham());
            sanPhamEntity.setTrangThai(sanPhamDTO.getTrangThai());
            sanPhamRepository.save(sanPhamEntity);

            return modelMapper.map(sanPhamEntity, SanPhamDTO.class);
        } else {
            throw new EntityNotFoundException("Không tìm thấy sản phẩm với id: " + sanPhamDTO.getId());
        }
    }

}
