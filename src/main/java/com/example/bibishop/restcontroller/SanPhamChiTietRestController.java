package com.example.bibishop.restcontroller;

import com.example.bibishop.common.Appcontants;
import com.example.bibishop.dto.SanPhamChiTietCrud;
import com.example.bibishop.dto.SanPhamChiTietDTO;
import com.example.bibishop.dto.SanPhamChiTietFiterDTO;
import com.example.bibishop.dto.SanPhamCtFiterDTO;
import com.example.bibishop.entity.SanPhamChiTietEntity;
import com.example.bibishop.service.sanpham.SanPhamChiTietService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/SPCT")
public class SanPhamChiTietRestController {

    private final SanPhamChiTietService sanPhamChiTietService;

    @GetMapping("/idSP")
    public Page<SanPhamChiTietDTO> getAllProductsByidSP(@RequestParam(value = "pageNo", defaultValue = Appcontants.DEFAULT_PAGE_NUMBER, required = false) int pageNo, @RequestParam(value = "pageSize", defaultValue = Appcontants.DEFAULT_TOTAL_NUMBER, required = false) int pageSize, @Valid Integer idSP, @Valid SanPhamCtFiterDTO filterForm) {

        return sanPhamChiTietService.getAllSanPhamChiTietBYidSP(idSP, pageNo, pageSize, filterForm);
    }
//
//    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
//    public ResponseEntity<?> addSanPhamChiTiet(@RequestBody SanPhamChiTietCrud sanPhamChiTietCrud) {
//
//        SanPhamChiTietCrud sanPhamChiTietCrudSave = sanPhamChiTietService.addSanPhamChiTiet(sanPhamChiTietCrud);
//        return ResponseEntity.ok(sanPhamChiTietCrudSave);
//
//    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> addSanPhamChiTiet(
            @RequestParam("sanPhamId") Integer sanPhamId,
            @RequestParam("kichCoId") Integer kichCoId,
            @RequestParam("danhMucId") Integer danhMucId,
            @RequestParam("mauSacId") Integer mauSacId,
            @RequestParam("chatLieuId") Integer chatLieuId,
            @RequestParam("trangThai") Integer trangThai,
            @RequestParam("giaSanPham") BigDecimal giaSanPham,
            @RequestParam("soLuong") Integer soLuong,
            @RequestParam("trongLuong") String trongLuong,
            @RequestParam("moTa") String moTa,
            @RequestParam("hinhAnhFile") MultipartFile hinhAnhFile
    ) {
        // Tạo object SanPhamChiTietCrud từ các param
        SanPhamChiTietCrud sanPhamChiTietCrud = new SanPhamChiTietCrud();
        sanPhamChiTietCrud.setSanPhamId(sanPhamId);
        sanPhamChiTietCrud.setKichCoId(kichCoId);
        sanPhamChiTietCrud.setDanhMucId(danhMucId);
        sanPhamChiTietCrud.setMauSacId(mauSacId);
        sanPhamChiTietCrud.setChatLieuId(chatLieuId);
        sanPhamChiTietCrud.setTrangThai(trangThai);
        sanPhamChiTietCrud.setGiaSanPham(giaSanPham);
        sanPhamChiTietCrud.setSoLuong(soLuong);
        sanPhamChiTietCrud.setTrongLuong(trongLuong);
        sanPhamChiTietCrud.setMoTa(moTa);
        sanPhamChiTietCrud.setHinhAnhfile(hinhAnhFile); // bạn cần thêm thuộc tính MultipartFile trong DTO hoặc xử lý riêng

        SanPhamChiTietCrud sanPhamChiTietCrudSave = sanPhamChiTietService.addSanPhamChiTiet(sanPhamChiTietCrud);
        return ResponseEntity.ok(sanPhamChiTietCrudSave);
    }


    @PutMapping()
    public ResponseEntity<?> UpdateSanPhamChiTiet(@RequestBody SanPhamChiTietCrud sanPhamChiTietCrud) {

        SanPhamChiTietCrud sanPhamChiTietCrudSave = sanPhamChiTietService.upDateSanPhamChiTiet(sanPhamChiTietCrud);
        return ResponseEntity.ok(sanPhamChiTietCrudSave);

    }

    @GetMapping()
    public Page<SanPhamChiTietDTO> getAllProducts(@RequestParam(value = "pageNo", defaultValue = Appcontants.DEFAULT_PAGE_NUMBER, required = false) int pageNo, @RequestParam(value = "pageSize", defaultValue = Appcontants.DEFAULT_TOTAL_NUMBER, required = false) int pageSize, @Valid SanPhamChiTietFiterDTO filterForm) {

        return sanPhamChiTietService.getAllSanPhamChiTiet(pageNo, pageSize, filterForm);
    }

    @GetMapping("/Detail/{id}")
    public SanPhamChiTietDTO getById(@PathVariable Integer id) {

        return sanPhamChiTietService.findById(id);
    }

    @GetMapping("/top4")
    public List<SanPhamChiTietDTO> getForSPCT() {
        Pageable topFour = PageRequest.of(0, 4);
        return sanPhamChiTietService.GetForSP(topFour);
    }

    @GetMapping("/by-idsp/{idSP}")
    public ResponseEntity<List<SanPhamChiTietDTO>> getSanPhamChiTietByIdSP(@PathVariable Integer idSP) {
        // Gọi phương thức từ service để lấy dữ liệu
        List<SanPhamChiTietDTO> dtos = sanPhamChiTietService.AllSanPhamChiTietByidSP(idSP);

        // Trả về danh sách DTO dưới dạng JSON
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/check")
    public ResponseEntity<?> checkSanPhamCT(@RequestParam("giaSanPham") BigDecimal giaSanPham, @RequestParam("sanPhamId") Integer sanPhamId, @RequestParam("gioiTinh") String gioiTinh, @RequestParam("trongLuong") String trongLuong, @RequestParam("danhMucId") Integer danhMucId, @RequestParam("hinhAnhId") Integer hinhAnhId, @RequestParam("kichCoId") Integer kichCoId, @RequestParam("mauSacId") Integer mauSacId, @RequestParam("chatLieuId") Integer chatLieuId) {

        Optional<SanPhamChiTietEntity> existingDetail = sanPhamChiTietService.checkExistingSanPhamCT(giaSanPham, sanPhamId, gioiTinh, trongLuong, danhMucId, hinhAnhId, kichCoId, mauSacId, chatLieuId);

        if (existingDetail.isPresent()) {
            Map<String, Object> result = new HashMap<>();
            result.put("exists", true);
            result.put("existingDetail", existingDetail.get());
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.ok(Collections.singletonMap("exists", false));
        }
    }

}
