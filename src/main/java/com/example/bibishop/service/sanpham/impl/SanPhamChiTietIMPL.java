package com.example.bibishop.service.sanpham.impl;

import com.example.bibishop.repository.*;
import com.example.bibishop.dto.SanPhamChiTietCrud;
import com.example.bibishop.dto.SanPhamChiTietDTO;
import com.example.bibishop.dto.SanPhamChiTietFiterDTO;
import com.example.bibishop.dto.SanPhamCtFiterDTO;
import com.example.bibishop.entity.*;
import com.example.bibishop.service.sanpham.SanPhamChiTietService;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
@Builder
public class SanPhamChiTietIMPL implements SanPhamChiTietService {
    private final SanPhamChiTietRepository_1 sanPhamChiTietRepository;
    private final ChatLieuRepository_1 chatLieuRepository;
    private final SanPhamRepository_1 sanPhamRepository;
    private final HinhAnhRepository_1 hinhAnhRepository;
    private final KichCoRepository_1 kichCoRepository;
    private final MauSacRepository_1 mauSacRepository;
    private final DanhMucRepository_1 danhMucRepository;
    private final ModelMapper modelMapper;

    @Override
    public Page<SanPhamChiTietDTO> getAllSanPhamChiTiet(Integer totalPage, Integer totalItem, SanPhamChiTietFiterDTO form) {
        Pageable pageable = PageRequest.of(totalPage, totalItem);

        // Xây dựng Specification từ form (SanPhamChiTietFiterDTO)
        Specification<SanPhamChiTietEntity> specification = SpecificationProduct.buildWhere(form);

        // Kiểm tra nếu không có Specification hoặc Specification là null
        if (specification == null) {
            List<SanPhamChiTietDTO> emptyList = Collections.emptyList();
            return new PageImpl<>(emptyList, pageable, 0);
        }

        // Sử dụng Specification trong phương thức findByProductName
        Page<SanPhamChiTietEntity> entityPage = sanPhamChiTietRepository.findAll(specification, pageable);

        // Convert Page<SanPhamChiTietEntity> sang Page<SanPhamChiTietDTO>
        List<SanPhamChiTietDTO> dtos = entityPage.getContent().stream()
                .map(entity -> modelMapper.map(entity, SanPhamChiTietDTO.class))
                .collect(Collectors.toList());

        return new PageImpl<>(dtos, pageable, entityPage.getTotalElements());
    }

    @Override
    public Page<SanPhamChiTietDTO> getAllSanPhamChiTietBYidSP(Integer idSP, Integer totalPage,
                                                              Integer totalItem, SanPhamCtFiterDTO fiterDTO) {
        // Tạo Specification để lọc theo idSP
        Specification<SanPhamChiTietEntity> specByIdSP = (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("sanPham").get("id"), idSP);


        Specification<SanPhamChiTietEntity> specByFilter = SpectificationSpct.buildWhereCT(fiterDTO);

        // Kết hợp các Specifications
        Specification<SanPhamChiTietEntity> combinedSpec = Specification.where(specByIdSP).and(specByFilter);

        Pageable pageable = PageRequest.of(totalPage, totalItem);

        // Truy vấn với combined Specification và Pageable
        Page<SanPhamChiTietEntity> entityPage = sanPhamChiTietRepository.findAll(combinedSpec, pageable);

        // Chuyển đổi từ entity sang DTO
        List<SanPhamChiTietDTO> dtos = entityPage.getContent().stream()
                .map(entity -> modelMapper.map(entity, SanPhamChiTietDTO.class))
                .collect(Collectors.toList());

        return new PageImpl<>(dtos, pageable, entityPage.getTotalElements());
    }

//    @Override
//    public SanPhamChiTietCrud addSanPhamChiTiet(SanPhamChiTietCrud sanPhamChiTietCrud) {
//        Optional<DanhMucEntity> danhMuc =danhMucRepository.findById(sanPhamChiTietCrud.getDanhMuc());
//        Optional<ChatLieuEntity>chatLieu =chatLieuRepository.findById(sanPhamChiTietCrud.getChatLieu());
//
//        Optional<HinhAnhEntity>hinhAnh =hinhAnhRepository.findById(sanPhamChiTietCrud.getHinhAnh());
//        Optional<KichCoEntity>kichCo =kichCoRepository.findById(sanPhamChiTietCrud.getKichCo());
//        Optional<MauSacEntity>mauSac =mauSacRepository.findById(sanPhamChiTietCrud.getMauSac());
//        Optional<SanPhamEntity>sanPham =sanPhamRepository.findById(sanPhamChiTietCrud.getSanPham());
//        SanPhamChiTietEntity sanPhamChiTiet = SanPhamChiTietEntity.builder()
//                .sanPham(sanPham.get())
//                .soLuong(sanPhamChiTietCrud.getSoLuong())
//                .kichCo(kichCo.get())
//                .mauSac(mauSac.get())
//                .chatLieu(chatLieu.get())
//                .danhMuc(danhMuc.get())
//                .hinhAnh(hinhAnh.get())
//                .giaSanPham(sanPhamChiTietCrud.getGiaSanPham())
//                .moTa(sanPhamChiTietCrud.getMoTa())
//                .gioiTinh(sanPhamChiTietCrud.getGioiTinh())
//                .trongLuong(sanPhamChiTietCrud.getTrongLuong())
//                .trangThai(sanPhamChiTietCrud.getTrangThai())
//                .build();
//        sanPhamChiTiet.setCreateDate(LocalDate.now());
//        sanPhamChiTiet.setUpdateDate(LocalDate.now().atStartOfDay());
//        SanPhamChiTietEntity sanPhamChiTietSave = sanPhamChiTietRepository.save(sanPhamChiTiet);
//        return modelMapper.map(sanPhamChiTietSave, SanPhamChiTietCrud.class);
//    }




    @Override
    public SanPhamChiTietCrud addSanPhamChiTiet(SanPhamChiTietCrud dto) {

        MultipartFile file = dto.getHinhAnhfile();
        if (file == null || file.isEmpty()) {
            throw new RuntimeException("Ảnh sản phẩm là bắt buộc");
        }

        // Đặt tên file duy nhất
        String originalFilename = file.getOriginalFilename();
        String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
        String uniqueFilename = UUID.randomUUID().toString() + extension;

        // Đường dẫn vật lý để lưu ảnh
        Path uploadPath = Paths.get("src/main/resources/static/uploads/");
        Path filePath = uploadPath.resolve(uniqueFilename);
        try {
            Files.createDirectories(uploadPath); // Tạo thư mục nếu chưa tồn tại
            file.transferTo(filePath.toFile()); // Lưu file
        } catch (IOException e) {
            throw new RuntimeException("Không thể lưu ảnh", e);
        }

        // Tạo bản ghi ảnh
        HinhAnhEntity hinhAnh = HinhAnhEntity.builder()
                .ten(originalFilename)
                .duongDan("/uploads/" + uniqueFilename)
                .trangThai(1)
                .build();
        hinhAnh = hinhAnhRepository.save(hinhAnh);

        // Lấy các id từ DTO
        Integer danhMucId = dto.getDanhMucId();
        Integer chatLieuId = dto.getChatLieuId();
        Integer hinhAnhId = dto.getHinhAnhId();
        Integer kichCoId = dto.getKichCoId();
        Integer mauSacId = dto.getMauSacId();
        Integer sanPhamId = dto.getSanPhamId();

        // Debug: in ra các giá trị id
        System.out.println("DanhMucId: " + danhMucId);
        System.out.println("ChatLieuId: " + chatLieuId);
        System.out.println("HinhAnhId: " + hinhAnhId);
        System.out.println("KichCoId: " + kichCoId);
        System.out.println("MauSacId: " + mauSacId);
        System.out.println("SanPhamId: " + sanPhamId);

        // Kiểm tra xem sản phẩm chi tiết với các tiêu chí giống nhau đã tồn tại chưa
        Optional<SanPhamChiTietEntity> optExisting = sanPhamChiTietRepository.findExistingProductDetail(
                dto.getGiaSanPham(),
                sanPhamId,
                dto.getGioiTinh(),
                dto.getTrongLuong(),
                danhMucId,
                hinhAnhId,
                kichCoId,
                mauSacId,
                chatLieuId
        );

        SanPhamChiTietEntity entity;
        if (optExisting.isPresent()) {
            // Nếu tồn tại, cộng dồn số lượng
            entity = optExisting.get();
            int updatedQuantity = entity.getSoLuong() + dto.getSoLuong();
            entity.setSoLuong(updatedQuantity);
            entity.setUpdateDate(LocalDate.now().atStartOfDay());
        } else {
            // Nếu chưa tồn tại, lấy các entity liên quan từ DB
            DanhMucEntity danhMuc = danhMucRepository.findById(danhMucId)
                    .orElseThrow(() -> new RuntimeException("Không tìm thấy Danh Mục với ID: " + danhMucId));
            ChatLieuEntity chatLieu = chatLieuRepository.findById(chatLieuId)
                    .orElseThrow(() -> new RuntimeException("Không tìm thấy Chat Lieu với ID: " + chatLieuId));
            KichCoEntity kichCo = kichCoRepository.findById(kichCoId)
                    .orElseThrow(() -> new RuntimeException("Không tìm thấy Kích Cỡ với ID: " + kichCoId));
            MauSacEntity mauSac = mauSacRepository.findById(mauSacId)
                    .orElseThrow(() -> new RuntimeException("Không tìm thấy Màu Sắc với ID: " + mauSacId));
            SanPhamEntity sanPham = sanPhamRepository.findById(sanPhamId)
                    .orElseThrow(() -> new RuntimeException("Không tìm thấy Sản Phẩm với ID: " + sanPhamId));

            // Xây dựng entity mới
            entity = SanPhamChiTietEntity.builder()
                    .sanPham(sanPham)
                    .soLuong(dto.getSoLuong())
                    .kichCo(kichCo)
                    .mauSac(mauSac)
                    .chatLieu(chatLieu)
                    .danhMuc(danhMuc)
                    .hinhAnh(hinhAnh)
                    .giaSanPham(dto.getGiaSanPham())
                    .moTa(dto.getMoTa())
                    .gioiTinh(String.valueOf(dto.getGioiTinh()))
                    .trongLuong(dto.getTrongLuong())
                    .trangThai(dto.getTrangThai())
                    .build();
            entity.setCreateDate(LocalDate.now());
            entity.setUpdateDate(LocalDate.now().atStartOfDay());
        }

        // Lưu entity (cập nhật hoặc mới)
        SanPhamChiTietEntity savedEntity = sanPhamChiTietRepository.save(entity);
        // Map entity đã lưu sang DTO trả về
        return modelMapper.map(savedEntity, SanPhamChiTietCrud.class);
    }

    public class ResourceNotFoundException extends RuntimeException {
        public ResourceNotFoundException(String message) {
            super(message);
        }
    }
    @Override
    public SanPhamChiTietCrud upDateSanPhamChiTiet(SanPhamChiTietCrud sanPhamChiTietCrud) {
        // Kiểm tra ID sản phẩm chi tiết
        if (sanPhamChiTietCrud.getId() == null) {
            throw new IllegalArgumentException("ID của sản phẩm chi tiết không được để trống.");
        }

        // Lấy entity sản phẩm chi tiết hiện có từ DB
        SanPhamChiTietEntity existingEntity = sanPhamChiTietRepository.findById(sanPhamChiTietCrud.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy sản phẩm chi tiết với id: " + sanPhamChiTietCrud.getId()));

        // Cập nhật các entity liên quan nếu có dữ liệu mới
        if (sanPhamChiTietCrud.getDanhMucId() != null && !sanPhamChiTietCrud.getDanhMucId().toString().trim().isEmpty()) {
            DanhMucEntity danhMucEntity = danhMucRepository.findById(sanPhamChiTietCrud.getDanhMucId())
                    .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy Danh Mục với id: " + sanPhamChiTietCrud.getDanhMucId()));
            existingEntity.setDanhMuc(danhMucEntity);
        }

        if (sanPhamChiTietCrud.getChatLieuId() != null && !sanPhamChiTietCrud.getChatLieuId().toString().trim().isEmpty()) {
            ChatLieuEntity chatLieuEntity = chatLieuRepository.findById(sanPhamChiTietCrud.getChatLieuId())
                    .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy Chất Liệu với id: " + sanPhamChiTietCrud.getChatLieuId()));
            existingEntity.setChatLieu(chatLieuEntity);
        }

        if (sanPhamChiTietCrud.getHinhAnhId() != null && !sanPhamChiTietCrud.getHinhAnhId().toString().trim().isEmpty()) {
            HinhAnhEntity hinhAnhEntity = hinhAnhRepository.findById(sanPhamChiTietCrud.getHinhAnhId())
                    .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy Hình Ảnh với id: " + sanPhamChiTietCrud.getBaoHanhId()));
            existingEntity.setHinhAnh(hinhAnhEntity);
        }

        if (sanPhamChiTietCrud.getKichCoId() != null && !sanPhamChiTietCrud.getKichCoId().toString().trim().isEmpty()) {
            KichCoEntity kichCoEntity = kichCoRepository.findById(sanPhamChiTietCrud.getKichCoId())
                    .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy Kích Cỡ với id: " + sanPhamChiTietCrud.getKichCoId()));
            existingEntity.setKichCo(kichCoEntity);
        }

        if (sanPhamChiTietCrud.getMauSacId() != null && !sanPhamChiTietCrud.getMauSacId().toString().trim().isEmpty()) {
            MauSacEntity mauSacEntity = mauSacRepository.findById(sanPhamChiTietCrud.getMauSacId())
                    .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy Màu Sắc với id: " + sanPhamChiTietCrud.getMauSacId()));
            existingEntity.setMauSac(mauSacEntity);
        }

        if (sanPhamChiTietCrud.getSanPhamId() != null && !sanPhamChiTietCrud.getSanPhamId().toString().trim().isEmpty()) {
            SanPhamEntity sanPhamEntity = sanPhamRepository.findById(sanPhamChiTietCrud.getSanPhamId())
                    .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy Sản Phẩm với id: " + sanPhamChiTietCrud.getSanPhamId()));
            existingEntity.setSanPham(sanPhamEntity);
        }

        // Cập nhật các thuộc tính khác
        existingEntity.setSoLuong(sanPhamChiTietCrud.getSoLuong());
        existingEntity.setGiaSanPham(sanPhamChiTietCrud.getGiaSanPham());
        existingEntity.setMoTa(sanPhamChiTietCrud.getMoTa());
        existingEntity.setGioiTinh(String.valueOf(sanPhamChiTietCrud.getGioiTinh()));
        existingEntity.setTrongLuong(sanPhamChiTietCrud.getTrongLuong());
        existingEntity.setTrangThai(sanPhamChiTietCrud.getTrangThai());

        // Cập nhật ngày cập nhật
        existingEntity.setUpdateDate(LocalDate.now().atStartOfDay());

        // Lưu lại và trả về DTO
        SanPhamChiTietEntity savedEntity = sanPhamChiTietRepository.save(existingEntity);
        return modelMapper.map(savedEntity, SanPhamChiTietCrud.class);
    }




//    @Override
//    public SanPhamChiTietCrud upDateSanPhamChiTiet(SanPhamChiTietCrud sanPhamChiTietCrud) {
//        Optional<DanhMucEntity> danhMuc =danhMucRepository.findById(sanPhamChiTietCrud.getDanhMuc());
//        Optional<ChatLieuEntity>chatLieu =chatLieuRepository.findById(sanPhamChiTietCrud.getChatLieu());
//        Optional<HinhAnhEntity>hinhAnh =hinhAnhRepository.findById(sanPhamChiTietCrud.getHinhAnh());
//        Optional<KichCoEntity>kichCo =kichCoRepository.findById(sanPhamChiTietCrud.getKichCo());
//        Optional<MauSacEntity>mauSac =mauSacRepository.findById(sanPhamChiTietCrud.getMauSac());
//        Optional<SanPhamEntity>sanPham =sanPhamRepository.findById(sanPhamChiTietCrud.getSanPham());
//        SanPhamChiTietEntity sanPhamChiTiet = SanPhamChiTietEntity.builder()
//                .sanPham(sanPham.get())
//                .soLuong(sanPhamChiTietCrud.getSoLuong())
//                .kichCo(kichCo.get())
//                .mauSac(mauSac.get())
//                .chatLieu(chatLieu.get())
//                .danhMuc(danhMuc.get())
//                .hinhAnh(hinhAnh.get())
//                .giaSanPham(sanPhamChiTietCrud.getGiaSanPham())
//                .moTa(sanPhamChiTietCrud.getMoTa())
//                .gioiTinh(sanPhamChiTietCrud.getGioiTinh())
//                .trongLuong(sanPhamChiTietCrud.getTrongLuong())
//                .trangThai(sanPhamChiTietCrud.getTrangThai())
//                .build();
//        sanPhamChiTiet.setCreateDate(LocalDate.now());
//        sanPhamChiTiet.setUpdateDate(LocalDate.now().atStartOfDay());
//        sanPhamChiTiet.setId(sanPhamChiTietCrud.getId());
//        SanPhamChiTietEntity sanPhamChiTietSave = sanPhamChiTietRepository.save(sanPhamChiTiet);
//        return modelMapper.map(sanPhamChiTietSave, SanPhamChiTietCrud.class);
//    }
//

    @Override
    public SanPhamChiTietDTO findById(Integer id) {
        SanPhamChiTietEntity sanPhamChiTietEntity = sanPhamChiTietRepository.findById(id).orElseThrow();

        return modelMapper.map(sanPhamChiTietEntity, SanPhamChiTietDTO.class);
    }

    @Override
    public List<SanPhamChiTietDTO> GetForSP(Pageable pageable) {
//        List<SanPhamChiTietEntity> entities = sanPhamChiTietRepository.findTop4SanPhamChiTiet(pageable);
//        return entities.stream()
//                .map(entity -> modelMapper.map(entity, SanPhamChiTietDTO.class))
//                .collect(Collectors.toList());
        return null;
    }

    @Override
    public List<SanPhamChiTietDTO> AllSanPhamChiTietByidSP(Integer idSP) {
        List<SanPhamChiTietEntity> entities = sanPhamChiTietRepository.findByIdSP(idSP);
        return entities.stream()
                .map(entity -> modelMapper.map(entity, SanPhamChiTietDTO.class))
                .collect(Collectors.toList());
    }




    @Override
    public Optional<SanPhamChiTietEntity> checkExistingSanPhamCT(BigDecimal giaSanPham, Integer sanPhamId, String gioiTinh, String trongLuong, Integer danhMucId, Integer hinhAnhId, Integer kichCoId, Integer mauSacId, Integer chatLieuId) {
        return sanPhamChiTietRepository.findExistingProductDetail(
                giaSanPham,
                sanPhamId,
            Integer.parseInt(gioiTinh),
                trongLuong,
                danhMucId,
                hinhAnhId,
                kichCoId,
                mauSacId,
                chatLieuId
        );
    }
}
