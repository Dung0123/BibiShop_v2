package com.example.bibishop.restcontroller;

import com.example.bibishop.dto.ChatLieuDTO;
import com.example.bibishop.entity.ChatLieuEntity;
import com.example.bibishop.repository.sanpham.ChatLieuRepository;
import com.example.bibishop.service.sanpham.ChatLieuService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/admin/chatlieu")
@RequiredArgsConstructor
public class ChatLieuController {

    private final ChatLieuService chatLieuService;
    private final ChatLieuRepository chatLieuRepository;

    // Trang chính
    @GetMapping("/getAll")
    public String viewChatLieu(Model model) {
        model.addAttribute("list", chatLieuService.getAllChatLieu());
        model.addAttribute("chatLieu", new ChatLieuDTO());
        return "admin/chatlieu/chatlieu";
    }

    // Xem chi tiết
    @GetMapping("/view/{id}")
    public String viewChatLieuById(@PathVariable Integer id, Model model) {
        Optional<ChatLieuEntity> chatLieu = chatLieuRepository.findById(id);
        model.addAttribute("chatLieu", chatLieu.orElse(new ChatLieuEntity()));
        model.addAttribute("list", chatLieuService.getAllChatLieu());
        return "admin/chatlieu/chatlieu";
    }

    // Thêm mới
    @PostMapping("/add")
    public String addChatLieu(@ModelAttribute("chatLieu") ChatLieuDTO dto, Model model) {
        if (dto.getTen() == null || dto.getTen().trim().isEmpty()) {
            model.addAttribute("error", "Tên chất liệu không được để trống.");
        } else if (chatLieuService.isTenChatLieuExists(dto.getTen())) {
            model.addAttribute("error", "Tên chất liệu đã tồn tại.");
        } else {
            chatLieuService.addChatLieu(dto);
            return "redirect:/admin/chatlieu/getAll";
        }
        model.addAttribute("list", chatLieuService.getAllChatLieu());
        return "admin/chatlieu/chatlieu";
    }

    // Cập nhật
    @PostMapping("/update")
    public String updateChatLieu(@ModelAttribute("chatLieu") ChatLieuDTO dto, Model model) {
        if (dto.getTen() == null || dto.getTen().trim().isEmpty()) {
            model.addAttribute("error", "Tên chất liệu không được để trống.");
        } else if (chatLieuRepository.existsByTen(dto.getTen())) {
            model.addAttribute("error", "Tên chất liệu đã tồn tại.");
        } else {
            chatLieuService.updateChatLieu(dto);
            return "redirect:/admin/chatlieu/getAll";
        }
        model.addAttribute("list", chatLieuService.getAllChatLieu());
        return "admin/chatlieu/chatlieu";
    }

    // Xóa mềm
//    @GetMapping("/delete/{id}")
//    public String deleteChatLieu(@PathVariable Long id) {
//        chatLieuService.deleteChatLieu(id);
//        return "redirect:/admin/chatlieu/getAll";
//    }

    // Reset form
    @GetMapping("/reset")
    public String resetForm() {
        return "redirect:/admin/chatlieu/getAll";
    }
}