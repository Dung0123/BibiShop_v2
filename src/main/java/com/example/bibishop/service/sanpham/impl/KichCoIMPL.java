package com.example.bibishop.service.sanpham.impl;

import com.example.bibishop.entity.DanhMucEntity;
import com.example.bibishop.repository.sanpham.KichCoRepository;
import com.example.bibishop.dto.KichCoDTO;
import com.example.bibishop.entity.KichCoEntity;
import com.example.bibishop.service.sanpham.KichCoService;
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
public class KichCoIMPL implements KichCoService {

  private final KichCoRepository kichCoRepository;
  private final ModelMapper modelMapper;

  @Override
  public Page<KichCoDTO> getAllKichCo(Pageable pageable) {
    Page<KichCoEntity> kichCoEntities = kichCoRepository.findAll(pageable);
    return kichCoEntities.map(entity -> modelMapper.map(entity, KichCoDTO.class));
  }

  @Override
  public List<KichCoDTO> getAllKichCo() {
    List<KichCoEntity> kichCoEntities = kichCoRepository.findAll();
    return kichCoEntities.stream()
        .map(entity -> modelMapper.map(entity, KichCoDTO.class))
        .collect(Collectors.toList());
  }


  @Override
  public KichCoDTO addKichCo(KichCoDTO kichCoDTO) {
    System.out.println("Dữ liệu nhận được: " + kichCoDTO);

//    if (kichCoDTO.getDoDai() == null || kichCoDTO.getDoDai().trim().isEmpty()) {
//      throw new RuntimeException("Độ dài không được để trống");
//    }

    KichCoEntity kichCo = KichCoEntity.builder()
        .tenKichCo(kichCoDTO.getTenKichCo())
        .trangThai(kichCoDTO.getTrangThai() != null ? kichCoDTO.getTrangThai() : 0)
        .doDai("null")
        .build();

    kichCo.setCreateDate(LocalDate.now());
    kichCo.setUpdateDate(LocalDateTime.now());
    kichCoRepository.save(kichCo);

    return modelMapper.map(kichCo, KichCoDTO.class);
  }

//    @Override
//    public KichCoDTO addKichCo(KichCoDTO kichCoDTO) {
//        KichCoEntity kichCo = KichCoEntity.builder()
//                .tenKichCo(kichCoDTO.getTenKichCo()) .trangThai(kichCoDTO.getTrangThai() != null ? kichCoDTO.getTrangThai() : 1)
//                .doDai(kichCoDTO.getDoDai())
//                .build();
//        kichCo.setCreateDate(LocalDate.now());
//        kichCo.setUpdateDate(LocalDateTime.now());
//        kichCoRepository.save(kichCo);
//
//        return modelMapper.map(kichCo,KichCoDTO.class);
//
//    }

  //    @Override
//    public KichCoDTO updateKichCo(KichCoDTO kichCoDTO) {
//        Optional<KichCoEntity> kichCo = kichCoRepository.findById(kichCoDTO.getId());
//        KichCoEntity kichCo1 = new KichCoEntity();
//        kichCo1.setId(kichCo.get().getId());
//        kichCo1.setTenKichCo(kichCoDTO.getTenKichCo());
//        kichCo1.setDoDai(kichCoDTO.getDoDai());
//        kichCo1.setCreateDate(LocalDate.now());
//        kichCo1.setUpdateDate(LocalDateTime.now());
//        kichCoRepository.save(kichCo1);
//        return modelMapper.map(kichCo1,KichCoDTO.class);
//    }
  @Override
// ✅ Kiểm tra trùng tên kích cỡ
  public boolean isTenKichCoExists(String tenKichCo) {
    return kichCoRepository.existsByTenKichCo(tenKichCo);
  }

  @Override
  public boolean deleteKichCo(Long id) {
//    Optional<KichCoDTO> kichCoDTO = kichCoRepository.findById(id);
//    if(kichCoDTO.get().getTrangThai()==0)
//    {
//      kichCoDTO.get().setTrangThai(1);
//      kichCoRepository.save(kichCoDTO.get());
//    }else{
//      kichCoDTO.get().setTrangThai(0);
//      kichCoRepository.save(kichCoDTO.get());
//    }
    return true;
  }

  @Override
  public KichCoDTO updateKichCo(KichCoDTO kichCoDTO) {
    Optional<KichCoEntity> optionalKichCo = kichCoRepository.findById(kichCoDTO.getId());

    if (optionalKichCo.isEmpty()) {
      throw new RuntimeException("Không tìm thấy kích cỡ với ID: " + kichCoDTO.getId());
    }

    KichCoEntity kichCo = optionalKichCo.get();
    kichCo.setTenKichCo(kichCoDTO.getTenKichCo());
    kichCo.setDoDai("null");
    // ✅ Chỉ cập nhật doDai nếu không rỗng
//    if (kichCoDTO.getDoDai() != null && !kichCoDTO.getDoDai().trim().isEmpty()) {
//      kichCo.setDoDai(kichCoDTO.getDoDai());
//    }

    // ✅ Chỉ cập nhật trạng thái nếu hợp lệ (0 hoặc 1)
    if (kichCoDTO.getTrangThai() == 0 || kichCoDTO.getTrangThai() == 1) {
      kichCo.setTrangThai(kichCoDTO.getTrangThai());
    }

    kichCo.setUpdateDate(LocalDateTime.now());

    kichCoRepository.save(kichCo);
    return modelMapper.map(kichCo, KichCoDTO.class);
  }

}
