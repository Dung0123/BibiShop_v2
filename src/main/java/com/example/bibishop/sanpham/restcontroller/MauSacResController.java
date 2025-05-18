package com.example.bibishop.sanpham.restcontroller;

import com.example.bibishop.sanpham.dto.DanhMucDTO;
import com.example.bibishop.sanpham.dto.MauSacDTO;
import com.example.bibishop.sanpham.service.DanhMucService;
import com.example.bibishop.sanpham.service.MauSacService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/api/mausac")
@RequiredArgsConstructor
public class MauSacResController {
    private final MauSacService mauSacService;


    // API lấy danh sách màu sắc có phân trang
    @GetMapping("/getAllPaged")
    public ResponseEntity<Page<MauSacDTO>> getAllMauSac(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size) { // 3 item mỗi trang
        return ResponseEntity.ok(mauSacService.getAllMauSac(page, size));
    }
    // ✅ API kiểm tra trùng tên màu sắc
    @GetMapping("/check-duplicate")
    public ResponseEntity<Boolean> checkDuplicate(@RequestParam String ten) {
        boolean exists = mauSacService.isTenMauSacExists(ten);
        return ResponseEntity.ok(exists);
    }
    @GetMapping("/mau-sac")
    public ModelAndView home() {
        return new ModelAndView("admin/adminWeb/Mausac");
    }
    @GetMapping("/getAll")
    public List<MauSacDTO> getAllMauSac() {
        return mauSacService.getAllMauSac();
    }
    @PostMapping("/add")
    public MauSacDTO addDanhMuc(@RequestBody MauSacDTO dto){
        return mauSacService.addMauSac(dto);
    }
    @PutMapping("/update")
    public MauSacDTO updateDanhMuc(@RequestBody MauSacDTO dto){
        return mauSacService.updateMauSac(dto);
    }
}
