package com.example.bibishop.sanpham.restcontroller;

import com.example.bibishop.dto.DanhMucDTO;
import com.example.bibishop.sanpham.service.DanhMucService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/api/danhmuc")
@RequiredArgsConstructor
public class DanhMucRestController {


    private final DanhMucService danhMucService;

    // API kiểm tra trùng tên danh mục
    @GetMapping("/check-duplicate")
    public ResponseEntity<Boolean> checkDuplicate(@RequestParam String tenDanhMuc) {
        boolean exists = danhMucService.existsByTenDanhMuc(tenDanhMuc);
        return ResponseEntity.ok(exists);
    }
    @GetMapping("/danh-muc")
    public ModelAndView home() {
        return new ModelAndView("admin/adminWeb/Danhmuc");
    }
    @GetMapping("/getAll")
    public List<DanhMucDTO> getAllDanhMuc() {
        return danhMucService.getAllDanhMuc();
    }

    @PostMapping("/add")
   public DanhMucDTO addDanhMuc(@RequestBody DanhMucDTO dto){
        return danhMucService.addDanhMuc(dto);
    }
    @PutMapping("/update")
    public DanhMucDTO updateDanhMuc(@RequestBody DanhMucDTO dto){
        return danhMucService.upDateDanhMuc(dto);
    }
}
