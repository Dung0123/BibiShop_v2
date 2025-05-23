package com.example.bibishop.restcontroller;

import com.example.bibishop.dto.DanhMucDTO;
import com.example.bibishop.entity.DanhMucEntity;
import com.example.bibishop.repository.sanpham.DanhMucRepository;
import com.example.bibishop.service.sanpham.DanhMucService;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/api/danhmuc")
@RequiredArgsConstructor
public class DanhMucRestController {

  @Controller
  @RequestMapping("/admin/danhmuc")
  public class DanhMucController {

    private final DanhMucService danhMucService;
    private final DanhMucRepository danhMucRepository;

    public DanhMucController(DanhMucService danhMucService, DanhMucRepository danhMucRepository) {
      this.danhMucService = danhMucService;
      this.danhMucRepository = danhMucRepository;
    }

    // Trang chính
//    @GetMapping("/getAll")
//    public String viewDanhMuc(Model model) {
//      model.addAttribute("list", danhMucService.getAllDanhMuc());
//      model.addAttribute("danhMuc", new DanhMucDTO()); // input trống lúc đầu
//      return "admin/danhmuc/danhmuc";
//    }

    @GetMapping("/getAll")
    public String viewDanhMuc_page(Model model,
        @RequestParam(name = "page", defaultValue = "1") int page,
        @RequestParam(name = "size", defaultValue = "5") int size) {

      Page<DanhMucDTO> list = danhMucService.getAllDanhMuc(PageRequest.of(page - 1, size));
      model.addAttribute("list",list );
      model.addAttribute("danhMuc", new DanhMucDTO()); // input trống lúc đầu
      model.addAttribute("currentPage", page);
      model.addAttribute("totalPages", list.getTotalPages());
      return "admin/danhmuc/danhmuc";
    }

    // Xem (Lấy danh mục theo id và đổ vào form)
    @GetMapping("/view/{id}")
    public String viewDanhMucById(@PathVariable Integer id, Model model) {
      Optional<DanhMucEntity> danhMuc = danhMucRepository.findById(id);
      model.addAttribute("danhMuc", danhMuc);
      model.addAttribute("list", danhMucService.getAllDanhMuc());
      return "admin/danhmuc/danhmuc";
    }

    // Thêm mới
    @PostMapping("/add")
    public String addDanhMuc(@ModelAttribute("danhMuc") DanhMucDTO dto, Model model) {
      if (dto.getTenDanhMuc() == null || dto.getTenDanhMuc().trim().isEmpty()) {
        model.addAttribute("error", "Tên danh mục không được để trống.");
      } else if (danhMucService.existsByTenDanhMuc(dto.getTenDanhMuc())) {
        model.addAttribute("error", "Tên danh mục đã tồn tại.");
      } else {
        danhMucService.addDanhMuc(dto);
        return "redirect:/admin/danhmuc/getAll";
      }
      model.addAttribute("list", danhMucService.getAllDanhMuc());
      return "admin/danhmuc/danhmuc";
    }

    // Cập nhật
    @PostMapping("/update")
    public String updateDanhMuc(@ModelAttribute("danhMuc") DanhMucDTO dto, Model model) {
      if (dto.getTenDanhMuc() == null || dto.getTenDanhMuc().trim().isEmpty()) {
        model.addAttribute("error", "Tên danh mục không được để trống.");
      } else if (danhMucRepository.existsByTenDanhMuc(dto.getTenDanhMuc())) {
        model.addAttribute("error", "Tên danh mục đã tồn tại.");
      } else {
        danhMucService.upDateDanhMuc(dto);
        return "redirect:/admin/danhmuc/getAll";
      }
      model.addAttribute("list", danhMucService.getAllDanhMuc());
      return "admin/danhmuc/danhmuc";
    }

    // Xoá mềm
  @PostMapping("/delete/{id}")
  public String deleteDanhMuc(@PathVariable int id) {
    if (danhMucRepository.findById(id).isPresent()) {
      danhMucService.deleteDanhMuc(id);
    }
    return "redirect:/admin/danhmuc/getAll";
  }

    // Reset
    @GetMapping("/reset")
    public String resetForm() {
      return "redirect:/admin/danhmuc/getAll";
    }
  }
}
