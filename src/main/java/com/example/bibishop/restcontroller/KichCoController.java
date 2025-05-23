package com.example.bibishop.restcontroller;

import com.example.bibishop.dto.DanhMucDTO;
import com.example.bibishop.dto.KichCoDTO;
import com.example.bibishop.entity.KichCoEntity;
import com.example.bibishop.repository.sanpham.KichCoRepository;
import com.example.bibishop.service.sanpham.KichCoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/admin/kichco")
@RequiredArgsConstructor
public class KichCoController {

  private final KichCoService kichCoService;
  private final KichCoRepository kichCoRepository;

  // Trang chính
//  @GetMapping("/getAll")
//  public String viewKichCo(Model model) {
//    model.addAttribute("list", kichCoService.getAllKichCo());
//    model.addAttribute("kichCo", new KichCoDTO());
//    return "admin/kichco/kichco";
//  }

  @GetMapping("/getAll")
  public String viewKichCo(Model model,
      @RequestParam(name = "page", defaultValue = "1") int page,
      @RequestParam(name = "size", defaultValue = "5") int size) {

    Page<KichCoDTO> list = kichCoService.getAllKichCo(PageRequest.of(page - 1, size));
    model.addAttribute("list", list);
    model.addAttribute("kichCo", new KichCoDTO()); // input trống lúc đầu
    model.addAttribute("currentPage", page);
    model.addAttribute("totalPages", list.getTotalPages());
      return "admin/kichco/kichco";
  }

  // Xem chi tiết
  @GetMapping("/view/{id}")
  public String viewKichCoById(@PathVariable Integer id, Model model) {
    Optional<KichCoEntity> kichCo = kichCoRepository.findById(id);
    model.addAttribute("kichCo", kichCo.orElse(new KichCoEntity()));
    model.addAttribute("list", kichCoService.getAllKichCo());
    return "admin/kichco/kichco";
  }

  // Thêm mới
  @PostMapping("/add")
  public String addKichCo(@ModelAttribute("kichCo") KichCoDTO dto, Model model) {
    if (dto.getTenKichCo() == null || dto.getTenKichCo().trim().isEmpty()) {
      model.addAttribute("error", "Tên kích cỡ không được để trống.");
    } else if (kichCoService.isTenKichCoExists(dto.getTenKichCo())) {
      model.addAttribute("error", "Tên kích cỡ đã tồn tại.");
    } else {
      kichCoService.addKichCo(dto);
      return "redirect:/admin/kichco/getAll";
    }
    model.addAttribute("list", kichCoService.getAllKichCo());
    return "admin/kichco/kichco";
  }

  // Cập nhật
  @PostMapping("/update")
  public String updateKichCo(@ModelAttribute("kichCo") KichCoDTO dto, Model model) {
    if (dto.getTenKichCo() == null || dto.getTenKichCo().trim().isEmpty()) {
      model.addAttribute("error", "Tên kích cỡ không được để trống.");
    } else if (kichCoRepository.existsByTenKichCo(dto.getTenKichCo())) {
      model.addAttribute("error", "Tên kích cỡ đã tồn tại.");
    } else {
      kichCoService.updateKichCo(dto);
      return "redirect:/admin/kichco/getAll";
    }
    model.addAttribute("list", kichCoService.getAllKichCo());
    return "admin/kichco/kichco";
  }

//   Xóa mềm
    @GetMapping("/delete/{id}")
    public String deleteKichCo(@PathVariable Long id) {
        kichCoService.deleteKichCo(id);
        return "redirect:/admin/kichco/getAll";
    }

  // Reset form
  @GetMapping("/reset")
  public String resetForm() {
    return "redirect:/admin/kichco/getAll";
  }
}