package com.example.bibishop.rest;


import com.example.bibishop.entity.HoaDon;
import com.example.bibishop.repository.HoaDonRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v2") // Thêm version để tránh xung đột
@RequiredArgsConstructor
public class OrderHistoryV2Controller {

    private final HoaDonRepository hoaDonRepository;

    @GetMapping("/orders/{id}")
    @ResponseBody // Đảm bảo rõ ràng
    public Optional<HoaDon> getOrderDetails(@PathVariable Integer id) {
        return hoaDonRepository.findById(id);
    }
}