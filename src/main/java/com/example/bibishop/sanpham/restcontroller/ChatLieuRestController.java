package com.example.bibishop.sanpham.restcontroller;

import com.example.bibishop.sanpham.dto.ChatLieuDTO;
import com.example.bibishop.sanpham.dto.KichCoDTO;
import com.example.bibishop.sanpham.dto.MauSacDTO;
import com.example.bibishop.sanpham.service.ChatLieuService;
import com.example.bibishop.sanpham.service.KichCoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/chatlieu")
@RequiredArgsConstructor
public class ChatLieuRestController {
    private final ChatLieuService chatLieuService;
    // ✅ API kiểm tra trùng tên chất liệu
    @GetMapping("/check-duplicate")
    public ResponseEntity<Boolean> checkDuplicate(@RequestParam String ten) {
        boolean exists = chatLieuService.isTenChatLieuExists(ten);
        return ResponseEntity.ok(exists);
    }
    @GetMapping("/chat-lieu")
    public ModelAndView home() {
        return new ModelAndView("admin/adminWeb/Chatlieu");
    }
    @GetMapping("/getAll")
    public List<ChatLieuDTO> getAllMauSac() {
        return chatLieuService.getAllChatLieu();
    }
    @PostMapping("/add")
    public ChatLieuDTO addChatLieu(@RequestBody ChatLieuDTO dto){
        return chatLieuService.addChatLieu(dto);
    }
    @PutMapping("/update")
    public ChatLieuDTO updateChatLieu(@RequestBody ChatLieuDTO dto){
        return chatLieuService.updateChatLieu(dto);
    }


}
