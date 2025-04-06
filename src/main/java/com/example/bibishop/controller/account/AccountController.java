package com.example.bibishop.controller.account;


import com.example.bibishop.dto.admin.CreateKhachHangRequest;
import com.example.bibishop.entity.KhachHang;
import com.example.bibishop.service.KhachHangService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/client/khachhang")
@RequiredArgsConstructor
public class AccountController {

    private final KhachHangService khachHangService;

    @GetMapping("info")
    public String listEmployees(Model model) {
        KhachHang khachHang = khachHangService.getAllCustomers().get(0);
        model.addAttribute("khachhang", khachHang);
        return "shop/account";
    }
    @PostMapping("register")
    public ResponseEntity<String> register(Model model, @RequestBody CreateKhachHangRequest request) {
        khachHangService.register(request);
        return ResponseEntity.ok("Đăng ký thành công");
    }
    @PutMapping("update-profile")
    public ResponseEntity<String> updateProfile(Model model, @RequestBody CreateKhachHangRequest request) {
        khachHangService.updateProfile(request);
        return ResponseEntity.ok("Cập nhật thành công");
    }
}