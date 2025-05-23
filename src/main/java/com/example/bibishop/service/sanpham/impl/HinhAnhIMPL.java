package com.example.bibishop.service.sanpham.impl;

import com.example.bibishop.repository.HinhAnhRepository_1;
import com.example.bibishop.dto.HinhAnhDTO;
import com.example.bibishop.entity.HinhAnhEntity;
import com.example.bibishop.service.HinhAnhService;
import jakarta.persistence.EntityNotFoundException;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HinhAnhIMPL implements HinhAnhService {
private final HinhAnhRepository_1 hinhAnhRepository;
private final ModelMapper modelMapper;
    @Override



    public List<HinhAnhDTO> getAllHinhAnh() {
        List<HinhAnhEntity> hinhAnhEntities = hinhAnhRepository.findAll();

        return hinhAnhEntities.stream()
                .map(entity -> {
                    HinhAnhDTO dto = modelMapper.map(entity, HinhAnhDTO.class);

                    // Chuyển đổi đường dẫn từ nội bộ sang đường dẫn HTTP
                    String baseUrl = "http://localhost:8080/img2/";
                    if (!entity.getDuongDan().startsWith("http")) {
                        String fileName = Paths.get(entity.getDuongDan()).getFileName().toString();
                        dto.setDuongDan(baseUrl + fileName); // Đảm bảo trả về đường dẫn đầy đủ
                    }

                    return dto;
                })
                .collect(Collectors.toList());
    }

//
//    public List<HinhAnhDTO> getAllHinhAnh() {
//        List<HinhAnhEntity>hinhAnhEntities = hinhAnhRepository.findAll();
//        return hinhAnhEntities.stream()
//                .map(entity -> modelMapper.map(entity, HinhAnhDTO.class))
//                .collect(Collectors.toList());
//    }




    @Override
    public HinhAnhDTO addHinhAnh(HinhAnhDTO hinhAnhDTO) {
        HinhAnhEntity hinhAnh = HinhAnhEntity.builder()
                .duongDan(hinhAnhDTO.getDuongDan())
                .build();
        hinhAnh.setTen("Adidas");
        hinhAnh.setCreateDate(LocalDate.now());
        hinhAnh.setUpdateDate(LocalDateTime.now());
        hinhAnh.setTrangThai(1);
        hinhAnhRepository.save(hinhAnh);
        return modelMapper.map(hinhAnh,HinhAnhDTO.class);
    }

    @Override
    public HinhAnhDTO UpdateHinhAnh(HinhAnhDTO hinhAnhDTO) {
        // Tìm kiếm HinhAnhEntity bằng ID
        Optional<HinhAnhEntity> hinhAnhOptional = hinhAnhRepository.findById(hinhAnhDTO.getId());

        if (!hinhAnhOptional.isPresent()) {
            // Xử lý khi không tìm thấy HinhAnhEntity với ID tương ứng
            throw new EntityNotFoundException("Không tìm thấy hình ảnh với ID: " + hinhAnhDTO.getId());
        }

        HinhAnhEntity hinhAnh = hinhAnhOptional.get();

        // Cập nhật thông tin của HinhAnhEntity
//        hinhAnh.setId(hinhAnh.getId());
        hinhAnh.setDuongDan(hinhAnhDTO.getDuongDan());
        hinhAnh.setTen("Adidas");
//        hinhAnh.setCreateDate(LocalDate.now()); // Có thể không cần cập nhật ngày tạo
        hinhAnh.setUpdateDate(LocalDateTime.now());
        hinhAnh.setTrangThai(1);

        // Lưu HinhAnhEntity đã cập nhật vào cơ sở dữ liệu
        hinhAnhRepository.save(hinhAnh);

        // Map từ HinhAnhEntity sang HinhAnhDTO và trả về kết quả
        return modelMapper.map(hinhAnh, HinhAnhDTO.class);
    }

}
