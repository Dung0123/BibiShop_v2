package com.example.bibishop.sanpham.restcontroller;

import com.example.bibishop.sanpham.dto.KichCoDTO;
import com.example.bibishop.sanpham.service.KichCoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/api/kichco")
@RequiredArgsConstructor
public class KichCoResController {

    private final KichCoService kichCoService;

    // Trả về view HTML
    @GetMapping("/kich-co")
    public ModelAndView home() {
        return new ModelAndView("admin/adminWeb/Kichco");
    }

    // Lấy danh sách tất cả kích cỡ
    @GetMapping("/getAll")
    public ResponseEntity<?> getAllKichCo() {
        try {
            List<KichCoDTO> kichCoList = kichCoService.getAllKichCo();
            if (kichCoList == null || kichCoList.isEmpty()) {
                return new ResponseEntity<>("Không có dữ liệu kích cỡ.", HttpStatus.NO_CONTENT); // 204
            }
            return new ResponseEntity<>(kichCoList, HttpStatus.OK); // 200
        } catch (Exception e) {
            e.printStackTrace(); // In lỗi ra console để debug
            return new ResponseEntity<>("Lỗi server khi lấy danh sách kích cỡ: " + e.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR); // 500
        }
    }

    // Kiểm tra trùng tên kích cỡ
    @GetMapping("/check-duplicate")
    public ResponseEntity<?> checkDuplicate(@RequestParam String tenKichCo) {
        try {
            boolean exists = kichCoService.isTenKichCoExists(tenKichCo);
            return new ResponseEntity<>(exists, HttpStatus.OK); // 200
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Lỗi server khi kiểm tra trùng tên: " + e.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR); // 500
        }
    }

    // Thêm kích cỡ mới
    @PostMapping("/add")
    public ResponseEntity<?> addKichCo(@RequestBody KichCoDTO dto) {
        try {
            if (dto.getTenKichCo() == null || dto.getTenKichCo().trim().isEmpty() ||
                dto.getDoDai() == null || dto.getDoDai().trim().isEmpty()) {
                return new ResponseEntity<>("Tên và độ dài không được để trống.", HttpStatus.BAD_REQUEST); // 400
            }
            KichCoDTO addedKichCo = kichCoService.addKichCo(dto);
            return new ResponseEntity<>(addedKichCo, HttpStatus.CREATED); // 201
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Lỗi server khi thêm kích cỡ: " + e.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR); // 500
        }
    }

    // Cập nhật kích cỡ
    @PutMapping("/update")
    public ResponseEntity<?> updateKichCo(@RequestBody KichCoDTO dto) {
        try {
            if (dto.getId() == null) {
                return new ResponseEntity<>("ID kích cỡ không được để trống.", HttpStatus.BAD_REQUEST); // 400
            }
            if (dto.getTenKichCo() == null || dto.getTenKichCo().trim().isEmpty() ||
                dto.getDoDai() == null || dto.getDoDai().trim().isEmpty()) {
                return new ResponseEntity<>("Tên và độ dài không được để trống.", HttpStatus.BAD_REQUEST); // 400
            }
            KichCoDTO updatedKichCo = kichCoService.updateKichCo(dto);
            return new ResponseEntity<>(updatedKichCo, HttpStatus.OK); // 200
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Lỗi server khi cập nhật kích cỡ: " + e.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR); // 500
        }
    }
}